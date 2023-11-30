package com.example.jobshunters.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jobshunters.Model.Offer;
import com.example.jobshunters.R;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {

    private List<Offer> offers;
    private Context context;

    public OfferAdapter(List<Offer> offers, Context context) {
        this.offers = offers;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_titulo.setText(offers.get(position).getTitle());
        // Establecer el texto en el TextView correspondiente
        holder.iv_portada.setText(offers.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView iv_portada;
        private TextView tv_titulo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_portada=itemView.findViewById(R.id.tv_descripcion);
            tv_titulo=itemView.findViewById(R.id.tv_titulo);
        }
    }
}
