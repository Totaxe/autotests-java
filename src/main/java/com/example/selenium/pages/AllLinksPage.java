package com.example.selenium.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AllLinksPage extends BasePage {
    
    public AllLinksPage(WebDriver driver) {
        super(driver, "/links");
    }

    @FindBy(css = "button[title='Delete Link']")
    private List<WebElement> deleteLinkButtons;

    public void clickDeleteLinkButton(int position) {
        log.info("Click Delete Link button");
        deleteLinkButtons.get(position - 1).click();
    }

    public int getNumOfLinks() {
        return deleteLinkButtons.size();
    }
}
