package com.example.selenium.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.example.selenium.retrofit.ListService;
import com.example.selenium.retrofit.entities.List;

import retrofit2.Response;

public class ListApiTest extends BaseApiTest {

    private ListService api = retrofit.create(ListService.class);

    @Test
    public void createList() throws IOException {
        Response<List> createListResponse = api.createList(new List()
                .name("Guides")
                .description("Various guides"))
                .execute();
        assertThat(createListResponse.code()).isEqualTo(200);
        assertThat(createListResponse.body().name()).isEqualTo("Guides");

        Response<List> listDetailsResponse = api.getListDetails(createListResponse.body().id()).execute();
        assertThat(listDetailsResponse.code()).isEqualTo(200);
        assertThat(listDetailsResponse.body().name()).isEqualTo("Guides");
    }
}
