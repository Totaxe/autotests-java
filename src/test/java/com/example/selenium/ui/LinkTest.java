package com.example.selenium.ui;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.selenium.pages.AddLinkPage;
import com.example.selenium.pages.AllLinksPage;
import com.example.selenium.pages.DashboardPage;
import com.example.selenium.pages.LinkPage;
import com.example.selenium.pages.SiteHeader;
import com.example.selenium.pages.TrashPage;
import com.example.selenium.retrofit.entities.Link;
import com.example.selenium.services.TestDataService;

public class LinkTest extends BaseUiTest {

    private SiteHeader siteHeader;
    private DashboardPage dashboardPage;
    private LinkPage linkPage;
    private AddLinkPage addLinkPage;
    private AllLinksPage allLinksPage;
    private TrashPage trashPage;

    @BeforeEach
    public void initPages() throws Exception {
        siteHeader = newPage(SiteHeader.class);
        dashboardPage = newPage(DashboardPage.class);
        linkPage = newPage(LinkPage.class);
        addLinkPage = newPage(AddLinkPage.class);
        allLinksPage = newPage(AllLinksPage.class);
        trashPage = newPage(TrashPage.class);
    }

    @Test
    public void quickAddLink() {
        dashboardPage.typeUrl("https://google.com")
                .clickQuickAddLinkButton();
        assertThat(linkPage).is(loaded);
        assertThat(linkPage.successAlertDisplayed()).isTrue();
        assertThat(linkPage.getLinkUrl()).isEqualTo("https://google.com/");
    }

    @Test
    public void warnWhenLinkIncorrect() {
        dashboardPage.typeUrl("https://google.com1")
                .clickQuickAddLinkButton();
        assertThat(linkPage).is(loaded);
        assertThat(linkPage.getWarningAlert()).contains("error occurred when trying to request the URL");
    }

    @Test
    public void addLink() {
        siteHeader.clickAddLinkButton();
        assertThat(addLinkPage).is(loaded);
        addLinkPage.typeUrl("https://youtube.com")
                .typeTitle("Youtube")
                .typeDescription("Video hosting")
                .clickAddLinkButton();
        assertThat(linkPage).is(loaded);
        assertThat(linkPage.successAlertDisplayed()).isTrue();
        assertThat(linkPage.getLinkUrl()).isEqualTo("https://youtube.com/");
    }

    @Test
    public void deleteLink() throws IOException {
        TestDataService.createLink(new Link()
                .url("https://youtube.com")
                .title("Youtube")
                .description("Video hosting"));
        siteHeader.clickAllLinksButton();
        assertThat(allLinksPage).is(loaded);
        assertThat(allLinksPage.getNumOfLinks()).isEqualTo(1);
        allLinksPage.clickDeleteLinkButton(1);
        assertThat(allLinksPage.getNumOfLinks()).isZero();
        siteHeader.clickTrashButton();
        assertThat(trashPage).is(loaded);
        assertThat(trashPage.getTrashedLinkUrls()).hasSize(1)
                .first().isEqualTo("https://youtube.com");
    }

    @Test
    public void showLink() throws IOException {
        TestDataService.createLink(new Link()
                .url("https://youtube.com")
                .title("Youtube")
                .description("Video hosting"));
        linkPage.goTo("1");
        assertThat(linkPage.getLinkUrl()).isEqualTo("https://youtube.com/");
    }
}
