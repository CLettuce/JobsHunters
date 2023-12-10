package com.example.jobshunters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jobshunters.Model.Resources;
import com.example.jobshunters.network.ApiClient;
import com.example.jobshunters.network.ApiJobs;
import com.example.jobshunters.network.ResourcesDetailResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class resources_detail extends AppCompatActivity {


    private TextView txtTitulo;
    private TextView txtAutor;
    private TextView txtSummary;
    private TextView txtContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources_detail);

        txtTitulo = findViewById(R.id.txtTitulo);
        txtAutor = findViewById(R.id.txtAutor);
        txtSummary = findViewById(R.id.txtSummary);
        txtContent = findViewById(R.id.txtDescripcionRecurso);

        Intent intent = getIntent();
        if (intent != null) {
            int resourceId = intent.getIntExtra("resource_id", -1);
            if (resourceId != -1) {
                // Llamar a la función para obtener detalles de la oferta por su ID usando Retrofit
                obtenerDetallesOfertaPorID(resourceId);
            }
        }
    }
    private void obtenerDetallesOfertaPorID(int resourceId) {
        ApiJobs apiService = ApiClient.getClient().create(ApiJobs.class);
        Call<ResourcesDetailResponse> call = apiService.getResourceDetails(resourceId);

        call.enqueue(new Callback<ResourcesDetailResponse>() {
            @Override
            public void onResponse(Call<ResourcesDetailResponse> call, Response<ResourcesDetailResponse> response) {
                if(response.isSuccessful()){
                    Resources resourcesDetails = response.body().getResourcesDetails();
                    if (resourcesDetails != null){
                        txtTitulo.setText(resourcesDetails.getTitle());
                        txtAutor.setText(resourcesDetails.getAuthor());
                        txtSummary.setText(resourcesDetails.getSummary());
                        txtContent.setText(resourcesDetails.getContent());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResourcesDetailResponse> call, Throwable t) {

            }
        });

    }

}