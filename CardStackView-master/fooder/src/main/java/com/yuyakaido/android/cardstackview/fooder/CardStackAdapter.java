package com.yuyakaido.android.cardstackview.fooder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Spot> spots;

    public CardStackAdapter(Context context, List<Spot> spots) {
        this.inflater = LayoutInflater.from(context);
        this.spots = spots;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_spot, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Spot spot = spots.get(position);
        holder.food.setText(spot.food);
        holder.user.setText(spot.user);
        Glide.with(holder.image)
                .load(spot.url)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return spots.size();
    }

    public void addSpots(List<Spot> spots) {
        this.spots.addAll(spots);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView food;
        TextView user;
        ImageView image;
        ViewHolder(View view) {
            super(view);
            this.food = view.findViewById(R.id.item_name);
            this.user = view.findViewById(R.id.item_city);
            this.image = view.findViewById(R.id.item_image);
        }
    }

}
