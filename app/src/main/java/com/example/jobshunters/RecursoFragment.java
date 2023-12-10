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

import com.example.jobshunters.Model.Resources;
import com.example.jobshunters.adapter.ResourcesAdapter;
import com.example.jobshunters.network.ApiClient;
import com.example.jobshunters.network.ApiJobs;
import com.example.jobshunters.network.ResourcesResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RecursoFragment extends Fragment {

    private List<Resources> resources;
    private List<Resources> filteredResources;
    private RecyclerView recyclerView;
    private ResourcesAdapter resourcesAdapter;
    private SearchView searchView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recurso, container, false);

        recyclerView = view.findViewById(R.id.rv_mostrarRecurso);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 1));
        searchView = view.findViewById(R.id.searchBox);


        ShowResources();
        return view;
    }

    public void ShowResources(){
        ApiJobs apiService = ApiClient.getClient().create(ApiJobs.class);
        Call<ResourcesResponse> call = apiService.getResource();

        call.enqueue(new Callback<ResourcesResponse>() {
            @Override
            public void onResponse(Call<ResourcesResponse> call, Response<ResourcesResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    ResourcesResponse resourcesResponse = response.body();
                    resources = resourcesResponse.getResources();
                    if (getContext() != null){
                        if(resourcesAdapter == null){
                            resourcesAdapter = new ResourcesAdapter(resources, requireContext());
                            recyclerView.setAdapter(resourcesAdapter);
                        }else {
                            resourcesAdapter.filterList(resources);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResourcesResponse> call, Throwable t) {
                if (getContext() != null) {
                    Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(resourcesAdapter != null){
            resourcesAdapter.setOnItemClickListener(new ResourcesAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Resources selectedResource = resources.get(position);
                    int resourceId = selectedResource.getId();

                    Intent intent = new Intent(requireContext(), resources_detail.class);
                    intent.putExtra("resource_id",resourceId);
                    startActivity(intent);
                }
            });
        }
    }

    private void filter (String text){
        filteredResources = new ArrayList<>();

        if (text.isEmpty()) {
            filteredResources.addAll(resources);
        } else {
            for (Resources resource : resources) {
                if (resource.getTitle().toLowerCase().contains(text.toLowerCase()) ||
                        resource.getAuthor().toLowerCase().contains(text.toLowerCase())) {
                    filteredResources.add(resource);
                }
            }
        }

        if (resourcesAdapter != null) {
            resourcesAdapter.filterList(filteredResources);
        }
    }
}
