package com.example.hackathon2022.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackathon2022.Object.ObjectForum;
import com.example.hackathon2022.Object.ObjectReply;
import com.example.hackathon2022.R;

import java.util.ArrayList;

public class ReplyForumAdapter extends RecyclerView.Adapter<ReplyForumAdapter.ForumViewHolder> {
    Context context;
    ArrayList<ObjectReply> replyList;

    public ReplyForumAdapter(Context context, ArrayList<ObjectReply> newList) {
        this.context = context;
        this.replyList = newList;
    }

    @NonNull
    @Override
    public ForumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_component_card_forum_jawaban,parent,false);
        return new ReplyForumAdapter.ForumViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReplyForumAdapter.ForumViewHolder holder, int position) {
        holder.txtanswer.setText(replyList.get(position).getAnswer());
        holder.txtusernamejawaban.setText(replyList.get(position).getUsername());
        holder.txtdatejawaban.setText(replyList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return replyList.size();
    }

    public class ForumViewHolder extends RecyclerView.ViewHolder {
        TextView txtanswer,txtusernamejawaban,txtdatejawaban;
        ForumViewHolder(@NonNull View itemView) {
            super(itemView);
            txtanswer = itemView.findViewById(R.id.componentcardforumjawaban_jawaban);
            txtusernamejawaban = itemView.findViewById(R.id.componentcardforumjawaban_username);
            txtdatejawaban = itemView.findViewById(R.id.componentcardforumjawaban_date);
        }
    }
}
