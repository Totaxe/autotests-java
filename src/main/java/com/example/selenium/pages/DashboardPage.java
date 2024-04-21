package com.example.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver, "/dashboard");
    }

    @FindBy(id = "url")
    private WebElement urlInput;
    
    @FindBy(css = "#url ~ button")
    private WebElement quickAddLinkButton;

    public DashboardPage typeUrl(String url) {
        log.info("Type url: {}", url);
        urlInput.sendKeys(url);
        return this;
    }

    public void clickQuickAddLinkButton() {
        log.info("Click Quick Add Link button");
        quickAddLinkButton.click();
    }
}
