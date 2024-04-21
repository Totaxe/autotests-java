package com.example.selenium.pages;

import java.nio.file.Path;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImportPage extends BasePage {
    
    public ImportPage(WebDriver driver) {
        super(driver, "/import");
    }

    @FindBy(id = "import-file")
    private WebElement fileSelector;

    @FindBy(className = "import-submit-default")
    private WebElement importButton;

    @FindBy(className = "alert-success")
    private WebElement successAlert;

    public void importLinks() {
        log.info("Import links from HTML file");
        String filePath = Path.of("src/test/resources/bookmarks.html").toAbsolutePath().toString();
        fileSelector.sendKeys(filePath);
        importButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(successAlert));
    }

    public String getSuccessAlert() {
        return successAlert.getText();
    }
}
