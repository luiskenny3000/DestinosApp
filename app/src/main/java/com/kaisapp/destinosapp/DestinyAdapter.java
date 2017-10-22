package com.kaisapp.destinosapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by kennyorellana on 21/10/17.
 */

public class DestinyAdapter extends RecyclerView.Adapter<DestinyAdapter.DestinyViewHolder>{
    Context context;
    ArrayList<Destiny> list;

    public DestinyAdapter(Context context, ArrayList<Destiny> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public DestinyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_destiny, parent, false);
        return new DestinyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DestinyViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).name);
        holder.tvAddress.setText(list.get(position).getAddress());

        Glide.with(context)
                .load(list.get(position).image)
                .into(holder.ivBackground);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class DestinyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvAddress;
        ImageView ivBackground;

        public DestinyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView)itemView.findViewById(R.id.text_view_name);
            tvAddress = (TextView)itemView.findViewById(R.id.text_view_address);
            ivBackground = (ImageView)itemView.findViewById(R.id.imageView);
        }
    }
}
