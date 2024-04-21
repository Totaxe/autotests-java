package com.example.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ListPage extends BasePage {

    public ListPage(WebDriver driver) {
        super(driver, "/lists/");
    }

    @FindBy(className = "alert-success")
    private WebElement successAlert;

    public boolean successAlertDisplayed() {
        return successAlert.isDisplayed();
    }
}
