package com.example.jobshunters.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jobshunters.Model.Resources;
import com.example.jobshunters.R;
import com.example.jobshunters.resources_detail;
import java.util.ArrayList;
import java.util.List;


public class ResourcesAdapter extends RecyclerView.Adapter<ResourcesAdapter.ViewHolder> {

    private OnItemClickListener onItemClickListener;
    private Context context;
    private List<Resources> resources;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void  setOnItemClickListener(ResourcesAdapter.OnItemClickListener listener){
        this.onItemClickListener = listener;
    }

    public ResourcesAdapter(List<Resources> resources, Context context){
        this.resources = resources != null? resources : new ArrayList<>();
        this.context = context;
    }
    public void filterList(List<Resources> filteredSource) {
        this.resources = filteredSource;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ResourcesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.resources_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
        holder.titulo.setText(resources.get(holder.getAdapterPosition()).getTitle());
        holder.persona.setText(resources.get(holder.getAdapterPosition()).getAuthor());
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
        layoutParams.setMargins(16, 16, 16, 16); // Establece tus márgenes aquí
        holder.itemView.setLayoutParams(layoutParams);

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, resources_detail.class);
                int resourceId = resources.get(holder.getAdapterPosition()).getId();
                intent.putExtra("resource_id", resourceId);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (resources != null){
            return resources.size();
        }else {
            return 0; // Si la lista es nula, devuelve 0 elementos
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo;

        private TextView persona;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.txtTittle);
            persona = itemView.findViewById(R.id.txtCreador);
        }
    }
}
