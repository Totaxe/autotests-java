package com.example.selenium.services;

import java.io.IOException;
import java.sql.SQLException;

import com.example.selenium.retrofit.LinkService;
import com.example.selenium.retrofit.RetrofitService;
import com.example.selenium.retrofit.entities.Link;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Retrofit;

@UtilityClass
@Slf4j
public class TestDataService {

    private Retrofit retrofit = RetrofitService.get();

    public void loadInitialData() throws SQLException, IOException {
        log.info("Load initial data to database");
        DatabaseService.runScript("src/test/resources/data.sql");
    }

    public Link createLink(Link link) throws IOException {
        log.info("Create link using API: {}", link);
        LinkService api = retrofit.create(LinkService.class);
        return api.createLink(link).execute().body();
    }
}
