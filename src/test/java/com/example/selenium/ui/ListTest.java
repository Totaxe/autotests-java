package com.example.selenium.ui;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.selenium.pages.AddListPage;
import com.example.selenium.pages.ListPage;
import com.example.selenium.pages.SiteHeader;

public class ListTest extends BaseUiTest {

    private SiteHeader siteHeader;
    private AddListPage addListPage;
    private ListPage listPage;

    @BeforeEach
    public void initPages() throws Exception {
        siteHeader = newPage(SiteHeader.class);
        addListPage = newPage(AddListPage.class);
        listPage = newPage(ListPage.class);
    }

    @Test
    public void createList() {
        siteHeader.clickAddListButton();
        assertThat(addListPage).is(loaded);
        addListPage.typeName("Guides")
                .clickAddListButton();
        assertThat(listPage).is(loaded);
        assertThat(listPage.successAlertDisplayed()).isTrue();
    }
}
