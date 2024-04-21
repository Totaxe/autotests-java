package com.example.selenium.retrofit;

import com.example.selenium.retrofit.entities.GetLinksResponse;
import com.example.selenium.retrofit.entities.Link;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LinkService {
    
    @POST("links")
    Call<Link> createLink(@Body Link link);

    @GET("links")
    Call<GetLinksResponse> getLinks();

    @GET("links/{link_id}")
    Call<Link> getLinkDetails(@Path("link_id") int linkId);
}
