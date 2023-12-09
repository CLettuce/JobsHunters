package com.example.jobshunters;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jobshunters.Model.Offer;
import com.example.jobshunters.adapter.OfferAdapter;
import com.example.jobshunters.network.ApiClient;
import com.example.jobshunters.network.ApiJobs;
import com.example.jobshunters.network.OfferResponse;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {


    private List<Offer> offers;
    private List<Offer> filteredOffers;
    private RecyclerView recyclerView;
    private OfferAdapter offerAdapter;
    private SearchView searchView;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.rv_mostarApi);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 1));

        searchView = view.findViewById(R.id.searchBox);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

        showOffers();
        return view;
    }

    public void showOffers(){
        ApiJobs apiService = ApiClient.getClient().create(ApiJobs.class);
        Call<OfferResponse> call = apiService.getOffer();
        call.enqueue(new Callback<OfferResponse>() {
            @Override
            public void onResponse(Call<OfferResponse> call, Response<OfferResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    OfferResponse offerResponse = response.body();
                    offers = offerResponse.getOffers();
                    if (getContext() != null) {
                        if(offerAdapter == null){
                            offerAdapter = new OfferAdapter(offers, requireContext());
                            recyclerView.setAdapter(offerAdapter);
                        } else {
                            offerAdapter.filterList(offers);
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<OfferResponse> call, Throwable t) {
                if (getContext() != null) {
                    Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (offerAdapter != null) {
            offerAdapter.setOnItemClickListener(new OfferAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    // Obtener el ID de la oferta o cualquier dato relevante para abrir la nueva actividad
                    Offer selectedOffer = offers.get(position);
                    int offerId = selectedOffer.getId();

                    // Crear un Intent para abrir la actividad_offer_deail.xml
                    Intent intent = new Intent(requireContext(), offer_detail.class);
                    intent.putExtra("offer_id", offerId);
                    startActivity(intent);
                }
            });
        }

    }

    private void filter(String text) {
        filteredOffers = new ArrayList<>();

        if (text.isEmpty()) {
            filteredOffers.addAll(offers);
        } else {
            for (Offer offer : offers) {
                if (offer.getTitle().toLowerCase().contains(text.toLowerCase()) ||
                        offer.getCompany().toLowerCase().contains(text.toLowerCase()) ||
                        offer.getRole().toLowerCase().contains(text.toLowerCase())) {
                    filteredOffers.add(offer);
                }
            }
        }

        if (offerAdapter != null) {
            offerAdapter.filterList(filteredOffers);
        }
    }

}
