package com.example.hackathon2022.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackathon2022.Object.ObjectForum;
import com.example.hackathon2022.Object.ObjectReply;
import com.example.hackathon2022.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FrontForumAdapter extends RecyclerView.Adapter<FrontForumAdapter.ForumViewHolder>{

    StorageReference storageReference;
    Context context;
    ArrayList<ObjectForum> forumList;
    private FrontForumAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(View v, String key, String username, String judul, String kategori, String pertanyaan, String date, String star, String path);
    }

    public void setOnItemClickListener(FrontForumAdapter.OnItemClickListener listener){
        mListener = listener;
    }

    public FrontForumAdapter(Context context, ArrayList<ObjectForum> newList) {
        this.context = context;
        this.forumList = newList;
    }

    @NonNull
    @Override
    public ForumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_component_card_forum,parent,false);
        return new FrontForumAdapter.ForumViewHolder(v);
    }

    public ArrayList<ObjectForum> getForumList() {
        return forumList;
    }

    public void setForumList(ArrayList<ObjectForum> forumList) {
        this.forumList = forumList;
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(@NonNull ForumViewHolder holder, int position) {
        holder.txtJudul.setText(forumList.get(position).getJudul());
        holder.txtKategori.setText(forumList.get(position).getKategori());
        holder.txtPertanyaan.setText(forumList.get(position).getPertanyaan());
        holder.txtUserName.setText(forumList.get(position).getUsername());
        holder.txtDate.setText(forumList.get(position).getDate());
        if(forumList.get(position).getStar()>999){
            float temp = (float)forumList.get(position).getStar()/1000;
            holder.txtStar.setText(String.valueOf(String.format("%.1f",temp))+" ribu");
        }else{
            holder.txtStar.setText(forumList.get(position).getStar().toString());
        }
    }

    @Override
    public int getItemCount() {
        return forumList.size();
    }

    public class ForumViewHolder extends RecyclerView.ViewHolder {
        TextView txtJudul, txtKategori, txtPertanyaan, txtUserName, txtDate, txtStar;
        ForumViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.componentcardforum_judul);
            txtKategori = itemView.findViewById(R.id.componentcardforum_kategori);
            txtPertanyaan = itemView.findViewById(R.id.componentcardforum_Pertanyaan);
            txtUserName = itemView.findViewById(R.id.componentcardforum_username);
            txtDate = itemView.findViewById(R.id.componentcardforum_date);
            txtStar = itemView.findViewById(R.id.componentcardforum_starcount);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    ArrayList<ObjectForum> Forumlist = getForumList();
                    String key, username, judul, kategori, pertanyaan, date, star, path;

                    key = Forumlist.get(position).getKey();
                    username = Forumlist.get(position).getUsername();
                    judul = Forumlist.get(position).getJudul();
                    kategori = Forumlist.get(position).getKategori();
                    pertanyaan = Forumlist.get(position).getPertanyaan();
                    date = Forumlist.get(position).getDate();
                    star = Forumlist.get(position).getStar().toString();
                    path = Forumlist.get(position).getFilepath();

                    if(mListener!=null){
                        mListener.onItemClick(v,key,username,judul,kategori,pertanyaan,date,star,path);
                    }
                }
            });
        }
    }

}
