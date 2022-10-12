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

public class AllForumAdapter extends RecyclerView.Adapter<AllForumAdapter.ForumViewHolder> {
    Context context;
    ArrayList<ObjectForum> forumList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public AllForumAdapter(Context context, ArrayList<ObjectForum> newList) {
        this.context = context;
        this.forumList = newList;
    }

    @NonNull
    @Override
    public ForumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_component_card_forum,parent,false);
        return new AllForumAdapter.ForumViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AllForumAdapter.ForumViewHolder holder, int position) {
        holder.txtJudul.setText(forumList.get(position).getJudul());
        holder.txtKategori.setText(forumList.get(position).getKategori());
        holder.txtPertanyaan.setText(forumList.get(position).getPertanyaan());
        holder.txtUsername.setText(forumList.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return forumList.size();
    }

    public class ForumViewHolder extends RecyclerView.ViewHolder {
        TextView txtJudul, txtKategori, txtPertanyaan, txtUsername;
        ForumViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.componentcardforum_judul);
            txtKategori = itemView.findViewById(R.id.componentcardforum_kategori);
            txtPertanyaan = itemView.findViewById(R.id.componentcardforum_Pertanyaan);
            txtUsername = itemView.findViewById(R.id.componentcardforum_username);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        mListener.onItemClick();
                    }
                }
            });
        }
    }
}
