package com.example.jobshunters.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.jobshunters.offer_detail;

import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView.OnItemClickListener;

public class OfferAdapter  extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {

    private OnItemClickListener onItemClickListener;
    private List<Offer> offers;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
    public OfferAdapter(List<Offer> offers, Context context) {
        this.offers = offers != null ? offers : new ArrayList<>();
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.titulo.setText(offers.get(holder.getAdapterPosition()).getTitle());
        holder.company.setText(offers.get(holder.getAdapterPosition()).getCompany());
        holder.roles.setText(offers.get(holder.getAdapterPosition()).getRole());
        Glide.with(context).load(offers.get(holder.getAdapterPosition()).getCompany_logo_url()).into(holder.imagen);

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
        layoutParams.setMargins(16, 16, 16, 16); // Establece tus márgenes aquí
        holder.itemView.setLayoutParams(layoutParams);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, offer_detail.class);

                // Si deseas pasar información a la actividad OfferDetailActivity, aquí es donde puedes hacerlo.
                // Por ejemplo, si necesitas pasar el ID de la oferta:
                int offerId = offers.get(holder.getAdapterPosition()).getId();
                intent.putExtra("offer_id", offerId);

                // Iniciar la actividad
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (offers != null) {
            return offers.size();
        } else {
            return 0; // Si la lista es nula, devuelve 0 elementos
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titulo;

        private TextView company;
        private ImageView imagen;
        private TextView roles;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.txtTittle);
            roles = itemView.findViewById(R.id.txtRole);
            company = itemView.findViewById(R.id.txtEmprese);
            imagen = itemView.findViewById(R.id.imageViewProfile);

        }
    }
}


