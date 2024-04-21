package com.example.selenium.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.selenium.retrofit.LinkService;
import com.example.selenium.retrofit.entities.GetLinksResponse;
import com.example.selenium.retrofit.entities.Link;
import com.example.selenium.services.TestDataService;
import retrofit2.Response;

public class LinkApiTest extends BaseApiTest {

    private LinkService api = retrofit.create(LinkService.class);

    @Test
    public void createLink() throws IOException {
        Response<Link> createLinkResponse = api.createLink(new Link()
                .url("https://www.wikipedia.org")
                .description("The Free Encyclopedia")
                .title("Wikipedia"))
                .execute();
        assertThat(createLinkResponse.code()).isEqualTo(200);
        assertThat(createLinkResponse.body().url()).isEqualTo("https://www.wikipedia.org");

        Response<Link> linkDetailsResponse = api.getLinkDetails(createLinkResponse.body().id()).execute();
        assertThat(linkDetailsResponse.code()).isEqualTo(200);
        assertThat(linkDetailsResponse.body().url()).isEqualTo("https://www.wikipedia.org");
    }

    @Test
    public void getLinks() throws IOException {
        TestDataService.createLink(new Link()
                .url("https://amazon.com")
                .title("Amazon")
                .description("Free shipping on millions of items"));
        TestDataService.createLink(new Link()
                .url("https://youtube.com")
                .title("Youtube")
                .description("Video hosting"));
        GetLinksResponse response = api.getLinks().execute().body();
        List<Link> links = response.getData();
        assertThat(links).hasSize(2);
    }
}
