package com.example.jobshunters.network;

import com.example.jobshunters.Model.Offer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiJobs {
    @GET("api/v1/offers")
    Call<List<Offer>> getOffer();
}
