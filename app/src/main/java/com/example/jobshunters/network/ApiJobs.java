package com.example.jobshunters.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiJobs {
    @GET("api/v1/offers") //Endpoint para obtener toda la lista
    Call<OfferResponse> getOffer();

    @GET("api/v1/offers/{id}") // Endpoint para obtener detalles de una oferta específica por su ID
    Call<OfferDetailsResponse> getOfferDetails(@Path("id") int offerId); // Obtener detalles de una oferta por su ID

    @GET("api/v1/resources")
    Call<ResourcesResponse> getResource();

    @GET("api/v1/resources/{id}") // Endpoint para obtener resource específica por su ID
    Call<ResourcesDetailResponse> getResourceDetails(@Path("id") int resourceId); // Obtener detalles de una resource por su ID
}
