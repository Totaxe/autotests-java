package com.example.selenium.ui;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import com.example.selenium.BaseTest;
import com.example.selenium.pages.BasePage;
import com.example.selenium.pages.DashboardPage;
import com.example.selenium.pages.LoginPage;
import com.example.selenium.services.WebDriverService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseUiTest extends BaseTest {

    protected Condition<BasePage> loaded = new Condition<>(BasePage::loaded, "Page is loaded");

    @BeforeEach
    protected void setUp() throws Exception {
        WebDriverService.createDriver();
        logIn();
    }

    @AfterEach
    protected void tearDown(TestInfo info) throws IOException {
        takeScreenshot(info.getTestMethod().get().getName());
        WebDriverService.closeDriver();
    }

    protected <T> T newPage(Class<T> pageClass) throws Exception {
        return WebDriverService.newPage(pageClass);
    }

    protected void logIn() throws Exception {
        LoginPage loginPage = newPage(LoginPage.class);
        log.info("Open url: {}", config.appUrl());
        WebDriverService.goTo(config.appUrl());
        assertThat(loginPage).is(loaded);
        loginPage.logIn(config.appUser(), config.appPassword());
        DashboardPage dashboardPage = newPage(DashboardPage.class);
        assertThat(dashboardPage).is(loaded);
    }

    protected void takeScreenshot(String filename) throws IOException {
        File src = WebDriverService.takeScreenshot();
        String path = "build/screenshots/" + filename + ".png";
        FileUtils.copyFile(src, new File(path));
        // Attach file to Jenkins test report through JUnit Attachments plugin
        System.out.format("[[ATTACHMENT|%s]]", path);
    }
}
