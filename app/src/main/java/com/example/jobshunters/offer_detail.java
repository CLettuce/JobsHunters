package com.example.jobshunters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jobshunters.Model.Offer;
import com.example.jobshunters.network.ApiClient;
import com.example.jobshunters.network.ApiJobs;
import com.example.jobshunters.network.OfferDetailsResponse;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class offer_detail extends AppCompatActivity {

    private TextView txtNombreEmpresa;
    private TextView txtRolTrabajo;
    private ImageView imgLogoEmpresa;
    private TextView txtLugar;
    private TextView txtSalario;
    private TextView txtContacto;
    private TextView txtPublicacion;
    private TextView txtDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_detail);

        txtNombreEmpresa = findViewById(R.id.txtNombreEmpresa);
        txtRolTrabajo = findViewById(R.id.txtRolTrabajo);
        imgLogoEmpresa = findViewById(R.id.imgLogoEmpresa);
        txtLugar = findViewById(R.id.txtLugardeTrabajo);
        txtSalario = findViewById(R.id.txtSalario);
        txtContacto = findViewById(R.id.txtInfoContacto);
        txtPublicacion = findViewById(R.id.txtPublicacion);
        txtDescripcion = findViewById(R.id.txtDescripcionEmpleo);
        // Inicializa otros TextView e ImageView

        Intent intent = getIntent();
        if (intent != null) {
            int offerId = intent.getIntExtra("offer_id", -1);
            if (offerId != -1) {
                // Llamar a la función para obtener detalles de la oferta por su ID usando Retrofit
                obtenerDetallesOfertaPorID(offerId);
            }
        }
    }
    private void obtenerDetallesOfertaPorID(int offerId) {
        ApiJobs apiService = ApiClient.getClient().create(ApiJobs.class);
        Call<OfferDetailsResponse> call = apiService.getOfferDetails(offerId);

        call.enqueue(new Callback<OfferDetailsResponse>() {
            @Override
            public void onResponse(Call<OfferDetailsResponse> call, Response<OfferDetailsResponse> response) {
                if (response.isSuccessful()) {
                    Offer offerDetails = response.body().getOfferDetails();
                    if (offerDetails != null) {
                        //Se configura los TextView e ImageView con la información de la oferta obtenida
                        txtNombreEmpresa.setText(offerDetails.getCompany());
                        txtRolTrabajo.setText(offerDetails.getRole());
                        Glide.with(offer_detail.this)
                                .load(offerDetails.getCompany_logo_url())
                                .into(imgLogoEmpresa);
                        txtLugar.setText(offerDetails.getLocation());
                        txtSalario.setText(offerDetails.getSalary());
                        txtContacto.setText(offerDetails.getContact_info());
                        txtPublicacion.setText(offerDetails.getCreated_at());
                        txtDescripcion.setText(offerDetails.getContent());
                    }
                } else {
                    // Manejar errores si la solicitud no fue exitosa
                }
            }

            @Override
            public void onFailure(Call<OfferDetailsResponse> call, Throwable t) {

            }
        });
    }





}
