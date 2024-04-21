package com.example.selenium.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.example.selenium.TestConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BasePage {

    private TestConfig config = TestConfig.get();

    protected String url;
    protected WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected BasePage(WebDriver driver, String url) {
        this(driver);
        this.url = url;
    }

    public void goTo(String id) {
        if (url != null) {
            String fullUrl = config.appUrl() + url + id;
            log.info("Go to {}", fullUrl);
            driver.get(fullUrl);
        }
    }

    public void goTo() {
        goTo("");
    }

    public boolean loaded() {
        log.info("Check that {} is loaded", this.getClass().getSimpleName());
        return url == null || driver.getCurrentUrl().contains(url);
    }
}
