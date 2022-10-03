package com.example.hackathon2022.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackathon2022.Object.ObjectForum;
import com.example.hackathon2022.R;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ForumAdapterNear extends RecyclerView.Adapter<ForumAdapterNear.ForumViewHolder>{

    Context context;
    ArrayList<ObjectForum> ForumList;

    public ForumAdapterNear(Context context, ArrayList<ObjectForum> newList) {
        this.context = context;
        this.ForumList = newList;
    }

    @NonNull
    @Override
    public ForumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.component_cardforum,parent,false);
        return new ForumAdapterNear.ForumViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ForumViewHolder holder, int position) {
        holder.txtJudul.setText(ForumList.get(position).getJudul());
        holder.txtPertanyaan.setText(ForumList.get(position).getPertanyaan());
    }

    @Override
    public int getItemCount() {
        if(ForumList.size()>=2){
            return 2;
        }else{
            return ForumList.size();
        }
    }

    public class ForumViewHolder extends RecyclerView.ViewHolder {
        TextView txtJudul, txtPertanyaan;
        ForumViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.componentcardforum_judul);
            txtPertanyaan = itemView.findViewById(R.id.componentcardforum_Pertanyaan);
        }
    }
}

