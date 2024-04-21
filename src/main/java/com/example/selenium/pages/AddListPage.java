package com.example.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddListPage extends BasePage {

    public AddListPage(WebDriver driver) {
        super(driver, "/lists/create");
    }

    @FindBy(id = "name")
    private WebElement nameInput;
    
    @FindBy(css = "button[type=submit]")
    private WebElement addListButton;

    public AddListPage typeName(String name) {
        log.info("Type name: {}", name);
        nameInput.sendKeys(name);
        return this;
    }

    public void clickAddListButton() {
        log.info("Click Add List button");
        addListButton.click();
    }
}
