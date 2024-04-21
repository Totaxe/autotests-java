package com.example.selenium.services;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.example.selenium.TestConfig;
import com.example.selenium.util.StringUtils;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class WebDriverService {

    private TestConfig config = TestConfig.get();

    @Getter
    private WebDriver driver;

    public void createDriver() throws MalformedURLException {
        driver = config.hubUrl().isEmpty() ? getLocalDriver() : getRemoteDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    public <T> T newPage(Class<T> pageClass) throws Exception {
        Constructor<T> constructor = pageClass.getConstructor(WebDriver.class);
        return constructor.newInstance(driver);
    }

    public void goTo(String url) {
        driver.get(url);
    }

    public String getSessionId() {
        return ((RemoteWebDriver) driver).getSessionId().toString();
    }

    public String getCookies() {
        return driver.manage().getCookies().stream()
                .map(cookie -> {
                    try {
                        return cookie.getName() + "=" + StringUtils.decode(cookie.getValue());
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.joining(";"));
    }

    public File takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }

    private RemoteWebDriver getRemoteDriver() throws MalformedURLException {
        String browser = config.browser();
        String hubUrl = config.hubUrl();
        log.info("Initialize RemoteWebDriver with browser {} and hub url {}", browser, hubUrl);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true));
        switch (browser) {
            case "firefox":
                capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, getFirefoxOptions());
                break;
            case "chrome":
                capabilities.setCapability(ChromeOptions.CAPABILITY, getChromeOptions());
                break;
            default:
                throw new IllegalArgumentException(
                        String.format("Incorrect browser: %s. Must be chrome or firefox.", browser));

        }
        RemoteWebDriver remoteDriver = new RemoteWebDriver(new URL(hubUrl), capabilities);
        remoteDriver.setFileDetector(new LocalFileDetector());
        return remoteDriver;
    }

    private WebDriver getLocalDriver() {
        String browser = config.browser();
        log.info("Initialize local WebDriver with browser {}", browser);
        WebDriverManager driverManager;
        switch (browser) {
            case "firefox":
                driverManager = WebDriverManager.firefoxdriver();
                break;
            case "chrome":
                driverManager = WebDriverManager.chromedriver();
                break;
            default:
                throw new IllegalArgumentException(
                        String.format("Incorrect browser: %s. Must be chrome or firefox.", browser));
        }
        return driverManager.create();
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs",
                Map.<String, Object>of("profile.default_content_settings.popups", 0,
                        "download.prompt_for_download", false,
                        "download.directory_upgrade", true,
                        "safebrowsing.enabled", false,
                        "plugins.always_open_pdf_externally", true,
                        "plugins.plugins_disabled", Arrays.asList("Chrome PDF Viewer")));
        return options;
    }

    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("moz:firefoxOptions", Map.<String, Object>of("prefs",
                Map.<String, Object>of("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream")));
        return firefoxOptions;
    }
}
