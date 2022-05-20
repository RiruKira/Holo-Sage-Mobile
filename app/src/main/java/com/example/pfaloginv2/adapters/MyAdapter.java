package com.example.plllease.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.plllease.R;
import com.example.plllease.beans.User;
import com.example.plllease.beans.imagemap;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MyAdapter extends FirebaseRecyclerAdapter<imagemap, MyAdapter.myviewholder> {
    public MyAdapter(@NonNull FirebaseRecyclerOptions<imagemap> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull imagemap model) {
        if (model.getHotspots() != null) {
            holder.description.setText(model.getHotspots().get(0).getName().toString());
        } else
            holder.description.setText("delete null ");
        holder.date.setText(model.getDate_captured());
        //Convert String to int and get the Integer Value
        double longitude = model.getGps_location().getLongitude();
        double latitude = model.getGps_location().getLatitude();
        int longitudeSimplifie = (int) longitude;
        int latitudeSimplifie = (int) latitude;
        holder.location.setText(longitudeSimplifie + ", " + latitudeSimplifie);
        Glide.with(holder.img.getContext()).load(model.getFile_url()).into(holder.img);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView description, location, date;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.item_image);
            description = (TextView) itemView.findViewById(R.id.item_description);
            location = (TextView) itemView.findViewById(R.id.item_location);
            date = (TextView) itemView.findViewById(R.id.item_date);
        }
    }
}