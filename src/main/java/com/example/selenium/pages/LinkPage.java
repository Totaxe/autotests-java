package com.example.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LinkPage extends BasePage {

    public LinkPage(WebDriver driver) {
        super(driver, "/links/");
    }

    @FindBy(css = ".alert-success")
    private WebElement successAlert;

    @FindBy(css = ".alert-warning")
    private WebElement warningAlert;

    @FindBy(xpath = "(//main//a)[1]")
    private WebElement linkNameField;

    public boolean successAlertDisplayed() {
        return successAlert.isDisplayed();
    }

    public String getWarningAlert() {
        return warningAlert.getText();
    }

    public String getLinkUrl() {
        return linkNameField.getAttribute("href");
    }
}
