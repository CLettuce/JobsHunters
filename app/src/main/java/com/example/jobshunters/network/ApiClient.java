package com.example.jobshunters.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;
    public static Retrofit getClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://172.17.1.43:4001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
