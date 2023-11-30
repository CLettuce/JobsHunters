package com.example.jobshunters;
import com.example.jobshunters.HomeFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder> {
    private final List<HomeFragment.Offer> offerList;

    public OfferAdapter(List<HomeFragment.Offer> offerList) {
        this.offerList = offerList;
    }



    @NonNull
    @Override
    public OfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_offer, parent, false);
        return new OfferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferViewHolder holder, int position) {
        HomeFragment.Offer offer = offerList.get(position);
        holder.bind(offer);
    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }

    public static class OfferViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;

        public OfferViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView); // Reemplaza R.id.titleTextView por tu ID correspondiente
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView); // Reemplaza R.id.descriptionTextView por tu ID correspondiente
        }

        public void bind(HomeFragment.Offer offer) {
            titleTextView.setText(offer.getTitle());
            descriptionTextView.setText(offer.getDescription());
        }


    }


}
