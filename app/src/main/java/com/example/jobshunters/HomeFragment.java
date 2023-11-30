package com.example.jobshunters;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jobshunters.Model.Offer;
import com.example.jobshunters.adapter.OfferAdapter;
import com.example.jobshunters.network.ApiClient;
import com.example.jobshunters.network.ApiJobs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private List<Offer> offers;
    private RecyclerView recyclerView;
    private OfferAdapter offerAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerBox);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        showOffer();
        return view;
    }

    public void showOffer(){
        Call<List<Offer>> call = ApiClient.getClient().create(ApiJobs.class).getOffer();
        call.enqueue(new Callback<List<Offer>>() {
            @Override
            public void onResponse(Call<List<Offer>> call, Response<List<Offer>> response) {
                if(response.isSuccessful()){
                    offers = response.body();
                    offerAdapter = new OfferAdapter(offers, requireContext());
                    recyclerView.setAdapter(offerAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Offer>> call, Throwable t) {
                if (getContext() != null) {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
