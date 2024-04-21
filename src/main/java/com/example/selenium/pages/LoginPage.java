package com.example.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver, "/login");
    }

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;
    
    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    public void logIn(String email, String password) {
        log.info("Log in with username {} and password {}", email, password);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        submitButton.click();
    }

    @Override
    public boolean loaded() {
        return super.loaded() && passwordInput.isDisplayed();
    }
}
