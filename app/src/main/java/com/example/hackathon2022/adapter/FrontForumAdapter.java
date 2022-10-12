package com.example.hackathon2022.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackathon2022.Object.ObjectForum;
import com.example.hackathon2022.R;
import java.util.ArrayList;

public class FrontForumAdapter extends RecyclerView.Adapter<FrontForumAdapter.ForumViewHolder>{

    Context context;
    ArrayList<ObjectForum> forumList;
    private FrontForumAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(String key, String username, String judul, String kategori, String pertanyaan);
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

    @Override
    public void onBindViewHolder(@NonNull ForumViewHolder holder, int position) {
        holder.txtJudul.setText(forumList.get(position).getJudul());
        holder.txtKategori.setText(forumList.get(position).getKategori());
        holder.txtPertanyaan.setText(forumList.get(position).getPertanyaan());
        holder.txtUserName.setText(forumList.get(position).getUsername());
    }

    private final int limit = 4;
    @Override
    public int getItemCount() {
        if(forumList.size() > limit){
            return limit;
        }else{
            return forumList.size();
        }
    }

    public class ForumViewHolder extends RecyclerView.ViewHolder {
        TextView txtJudul, txtKategori, txtPertanyaan, txtUserName;
        ForumViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.componentcardforum_judul);
            txtKategori = itemView.findViewById(R.id.componentcardforum_kategori);
            txtPertanyaan = itemView.findViewById(R.id.componentcardforum_Pertanyaan);
            txtUserName = itemView.findViewById(R.id.componentcardforum_username);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    ArrayList<ObjectForum> Forumlist = getForumList();
                    String key, username, judul, kategori, pertanyaan;
                    key = Forumlist.get(position).getKey();
                    username = Forumlist.get(position).getUsername();
                    judul = Forumlist.get(position).getJudul();
                    kategori = Forumlist.get(position).getKategori();
                    pertanyaan = Forumlist.get(position).getPertanyaan();
                    if(mListener!=null){
                        mListener.onItemClick(key, username,judul,kategori,pertanyaan);
                    }
                }
            });
        }
    }
}