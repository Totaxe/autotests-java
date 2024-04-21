package com.example.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddLinkPage extends BasePage {

    public AddLinkPage(WebDriver driver) {
        super(driver, "/links/create");
    }

    @FindBy(id = "url")
    private WebElement urlInput;

    @FindBy(id = "title")
    private WebElement titleInput;
    
    @FindBy(id = "description")
    private WebElement descriptionInput;
    
    @FindBy(css = "button[type=submit]")
    private WebElement addLinkButton;

    public AddLinkPage typeUrl(String url) {
        log.info("Type url: {}", url);
        urlInput.sendKeys(url);
        return this;
    }

    public AddLinkPage typeTitle(String title) {
        log.info("Type title: {}", title);
        titleInput.sendKeys(title);
        return this;
    }

    public AddLinkPage typeDescription(String description) {
        log.info("Type description: {}", description);
        descriptionInput.sendKeys(description);
        return this;
    }

    public void clickAddLinkButton() {
        log.info("Click Add Link button");
        addLinkButton.click();
    }
}
