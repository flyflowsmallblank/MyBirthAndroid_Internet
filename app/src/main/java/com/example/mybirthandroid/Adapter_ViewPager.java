package com.example.mybirthandroid;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_ViewPager extends RecyclerView.Adapter<Adapter_ViewPager.InnerHolder> {
    private ArrayList<Character> data;

    public Adapter_ViewPager(ArrayList<Character> data){
        this.data = data;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_text, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.tvViewPager2.setText(data.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class InnerHolder extends RecyclerView.ViewHolder{
        private TextView tvViewPager2;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            tvViewPager2 = itemView.findViewById(R.id.tv_vp_text);
        }
    }
}
