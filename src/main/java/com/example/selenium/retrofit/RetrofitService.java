package com.example.selenium.retrofit;

import java.io.IOException;

import com.example.selenium.TestConfig;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.experimental.UtilityClass;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@UtilityClass
public class RetrofitService {

    private TestConfig config = TestConfig.get();

    private Retrofit retrofit = create();

    public Retrofit get() {
        return retrofit;
    }

    private Retrofit create() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        // add headers
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Authorization", "Bearer " + config.apiToken())
                        .addHeader("Accept", "application/json")
                        .build();
                return chain.proceed(request);
            }
        });

        // https://futurestud.io/tutorials/retrofit-2-log-requests-and-responses
        // add logging
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(Level.BODY);
        httpClient.addInterceptor(logging);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        return new Retrofit.Builder()
                .baseUrl(config.apiUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
    }
}
