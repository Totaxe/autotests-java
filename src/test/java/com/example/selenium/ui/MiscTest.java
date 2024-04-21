package com.example.selenium.ui;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.selenium.pages.ExportPage;
import com.example.selenium.pages.ImportPage;
import com.example.selenium.pages.SiteHeader;
import com.example.selenium.retrofit.entities.Link;
import com.example.selenium.services.TestDataService;
import com.opencsv.exceptions.CsvValidationException;

public class MiscTest extends BaseUiTest {

    private SiteHeader siteHeader;
    private ExportPage exportPage;
    private ImportPage importPage;

    @BeforeEach
    private void initPages() throws Exception {
        siteHeader = newPage(SiteHeader.class);
        exportPage = newPage(ExportPage.class);
        importPage = newPage(ImportPage.class);
    }

    @Test
    public void exportLinksToCsv() throws IOException, CsvValidationException, InterruptedException {
        TestDataService.createLink(new Link()
                .url("https://amazon.com")
                .title("Amazon")
                .description("Free shipping on millions of items"));
        TestDataService.createLink(new Link()
                .url("https://youtube.com")
                .title("Youtube")
                .description("Video hosting"));
        
        siteHeader.clickExportButton();
        assertThat(exportPage).is(loaded);
        List<List<String>> records = exportPage.exportToCsv();
        assertThat(records).hasSize(3); // two data rows and one header row
        assertThat(records.get(1)).element(2).isEqualTo("https://amazon.com");
        assertThat(records.get(2)).element(2).isEqualTo("https://youtube.com");
    }

    @Test
    public void importLinks() {
        siteHeader.clickImportButton();
        assertThat(importPage).is(loaded);
        importPage.importLinks();
        assertThat(importPage.getSuccessAlert()).isEqualTo("2 links imported successfully, 0 skipped.");
    }
}
