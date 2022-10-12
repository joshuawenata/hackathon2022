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
        void onItemClick();
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
                    if(mListener!=null){
                        mListener.onItemClick();
                    }
                }
            });
        }
    }
}
