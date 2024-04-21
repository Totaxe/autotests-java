package com.example.selenium.retrofit;

import com.example.selenium.retrofit.entities.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ListService {
    
    @POST("lists")
    Call<List> createList(@Body List list);

    @GET("lists/{list_id}")
    Call<List> getListDetails(@Path("list_id") int listId);
}
