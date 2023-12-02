package com.example.jobshunters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jobshunters.Model.Offer;
import com.example.jobshunters.adapter.OfferAdapter;
import com.example.jobshunters.network.ApiClient;
import com.example.jobshunters.network.ApiJobs;
import com.example.jobshunters.network.OfferResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    private List<Offer> offers;
    private RecyclerView recyclerView;
    private OfferAdapter offerAdapter;

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
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));

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
                    List<Offer> offers = offerResponse.getOffers();
                    if (getContext() != null) {
                        offerAdapter = new OfferAdapter(offers, requireContext());
                        recyclerView.setAdapter(offerAdapter);
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
}
