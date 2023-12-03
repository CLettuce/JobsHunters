package com.example.jobshunters.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobshunters.Model.Offer;
import com.example.jobshunters.R;

import java.util.List;

public class OfferAdapter  extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {

    private List<Offer> offers;
    private Context context;

    public OfferAdapter(List<Offer> offers, Context context) {
        this.offers = offers;
        this.context = context;
    }
    public void filterList(List<Offer> filteredOffers) {
        this.offers = filteredOffers;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.offer_view, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titulo.setText(offers.get(position).getCompany());
        holder.descripcion.setText(offers.get(position).getDescription());
        holder.company.setText(offers.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titulo;
        private TextView descripcion;
        private TextView company;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.txtTittle);
            descripcion = itemView.findViewById(R.id.txtDescrip);
            company = itemView.findViewById(R.id.txtEmprese);
        }
    }
}


