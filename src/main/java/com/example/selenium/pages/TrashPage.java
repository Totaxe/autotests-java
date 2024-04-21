package com.example.selenium.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TrashPage extends BasePage {

    public TrashPage(WebDriver driver) {
        super(driver, "/trash");
    }

    @FindBy(css = ".card:nth-of-type(2) tbody td:first-child")
    private List<WebElement> trashedLinkUrls;

    public List<String> getTrashedLinkUrls() {
        return trashedLinkUrls.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
