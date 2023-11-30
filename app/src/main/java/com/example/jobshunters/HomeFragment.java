package com.example.jobshunters;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import com.example.jobshunters.OfferAdapter;


public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {

    }
    public class Offer {
        private int id;
        private String title;
        private String description;

        public Offer() {
        }

        public Offer(int id, String title, String description) {
            this.id = id;
            this.title = title;
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Mostrar un mensaje indicando que se está conectando a la API
        TextView loadingTextView = rootView.findViewById(R.id.noDataTextView);
        loadingTextView.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.143.43:4001/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<List<Offer>> call = apiService.getOffers();
        call.enqueue(new Callback<List<Offer>>() {
            @Override
            public void onResponse(Call<List<Offer>> call, Response<List<Offer>> response) {
                // Ocultar el mensaje de carga después de obtener la respuesta
                loadingTextView.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    List<Offer> offers = response.body();

                    // Verifica si se reciben datos en el Logcat
                    Log.d("API_RESPONSE", "Number of offers received: " + offers.size());

                    RecyclerView recyclerView = rootView.findViewById(R.id.recyclerBox);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    OfferAdapter adapter = new OfferAdapter(offers);
                    recyclerView.setAdapter(adapter);

                    // Muestra un mensaje si no hay datos en la lista
                    if (offers.isEmpty()) {
                        TextView noDataTextView = rootView.findViewById(R.id.noDataTextView);
                        noDataTextView.setVisibility(View.VISIBLE);
                    }
                } else {
                    Log.e("API_RESPONSE", "Response not successful");
                }
            }

            @Override
            public void onFailure(Call<List<Offer>> call, Throwable t) {
                // Ocultar el mensaje de carga en caso de fallo en la conexión
                loadingTextView.setVisibility(View.GONE);

                // Manejar errores de conexión pero como es api sencilla no es necesario
            }
        });

        return rootView;
    }


    public interface ApiService {
        @GET("offers")
        Call<List<Offer>> getOffers();
    }



}