package com.example.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SiteHeader extends BasePage {

    public SiteHeader(WebDriver driver) {
        super(driver);
    }

    @FindBy(partialLinkText = "Add Link")
    private WebElement addLinkButton;

    @FindBy(linkText = "All Links")
    private WebElement allLinksButton;

    @FindBy(linkText = "Lists")
    private WebElement listsButton;
    
    @FindBy(linkText = "Add List")
    private WebElement addListButton;

    @FindBy(css = "a[title='Trash']")
    private WebElement trashButton;

    @FindBy(xpath = "(//ul[contains(@class, 'navbar-nav')])[2]/li[3]")
    private WebElement profileButton;
    
    @FindBy(linkText = "Import")
    private WebElement importButton;
    
    @FindBy(linkText = "Export")
    private WebElement exportButton;

    public void clickAddLinkButton() {
        log.info("Click Add Link button");
        addLinkButton.click();
    }

    public void clickAllLinksButton() {
        log.info("Click All Links button");
        allLinksButton.click();
    }

    public void clickTrashButton() {
        log.info("Click Trash button");
        trashButton.click();
    }

    public void clickAddListButton() {
        log.info("Click Add List button");
        listsButton.click();
        addListButton.click();
    }

    public void clickExportButton() {
        log.info("Click Export button");
        profileButton.click();
        exportButton.click();
    }

    public void clickImportButton() {
        log.info("Click Import button");
        profileButton.click();
        importButton.click();
    }
}
