package com.example.selenium.api;

import com.example.selenium.BaseTest;
import com.example.selenium.retrofit.RetrofitService;
import retrofit2.Retrofit;

public class BaseApiTest extends BaseTest {
    
    protected Retrofit retrofit = RetrofitService.get();
}
