package com.and.carbnb_android;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.MyViewHolder> {

    private ArrayList<CarsData> carsData;

    public SearchRecyclerViewAdapter(ArrayList<CarsData> carsData) {
        this.carsData = carsData;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView itemImage;
        private TextView itemName;
        private TextView itemPrice;
        private TextView description;

        public MyViewHolder(@NonNull View view) {
            super(view);

           // itemImage = view.findViewById(R.id.homepage_card_image);
            itemName = view.findViewById(R.id.card_name);
            itemPrice = view.findViewById(R.id.card_price);
            description = view.findViewById(R.id.card_description);
        }
    }

    @NonNull
    @Override
    public SearchRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecyclerViewAdapter.MyViewHolder holder, int position) {
        String itemName = carsData.get(position).name;
        String itemPrice = carsData.get(position).getPrice();
        String itemDescription = carsData.get(position).getDescription();
        //Drawable itemImage = productItemData.get(position).getItemImage();

        holder.itemName.setText(itemName);
        holder.itemPrice.setText(itemPrice);
        holder.description.setText(itemDescription);
        //holder.itemImage.setImageDrawable(itemImage);
    }

    @Override
    public int getItemCount() {
        return carsData.size();
    }
}
