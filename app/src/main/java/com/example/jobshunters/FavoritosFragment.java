package com.example.jobshunters;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jobshunters.Model.Offer;
import com.example.jobshunters.adapter.OfferAdapter;
import com.example.jobshunters.network.ApiClient;
import com.example.jobshunters.network.ApiJobs;
import com.example.jobshunters.network.OfferResponse;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class FavoritosFragment extends Fragment {

    private RecyclerView recyclerView;
    private OfferAdapter offerAdapter;
    private List<Offer> allOffers;


    public FavoritosFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);

        recyclerView = view.findViewById(R.id.rv_mostrarFavorito);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        getAllOffersFromApi(); // Obtener todas las ofertas de la API

        return view;
    }

    private void getAllOffersFromApi() {
        Retrofit retrofit = ApiClient.getClient();
        ApiJobs apiJobs = retrofit.create(ApiJobs.class);
        Call<OfferResponse> call = apiJobs.getOffer();

        call.enqueue(new Callback<OfferResponse>() {
            @Override
            public void onResponse(Call<OfferResponse> call, Response<OfferResponse> response) {
                if (response.isSuccessful()) {
                    OfferResponse offerResponse = response.body();
                    if (offerResponse != null) {
                        allOffers = offerResponse.getOffers();
                        List<Offer> favoritedOffers = getFavoritedOffers(allOffers);
                        displayFavoritedOffers(favoritedOffers);
                    }
                }
            }

            @Override
            public void onFailure(Call<OfferResponse> call, Throwable t) {
                // Manejar la falla al obtener las ofertas
            }
        });
    }
    private List<Offer> getFavoritedOffers(List<Offer> allOffers) {
        Set<String> favoritedOfferIds = getFavoritedOfferIds();

        List<Offer> favoritedOffers = new ArrayList<>();

        for (Offer offer : allOffers) {
            if (favoritedOfferIds.contains(String.valueOf(offer.getId()))) {
                favoritedOffers.add(offer);
            }
        }
        return favoritedOffers;
    }

    private Set<String> getFavoritedOfferIds() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("Favoritos", Context.MODE_PRIVATE);
        return sharedPreferences.getStringSet("favorited_ids", new HashSet<>());
    }

    private void displayFavoritedOffers(List<Offer> favoritedOffers) {
        offerAdapter = new OfferAdapter(favoritedOffers, requireContext());
        recyclerView.setAdapter(offerAdapter);
    }
}