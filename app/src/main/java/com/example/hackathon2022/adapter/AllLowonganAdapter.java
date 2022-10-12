package com.example.hackathon2022.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackathon2022.Object.ObjectLowongan;
import com.example.hackathon2022.R;

import java.util.ArrayList;

public class AllLowonganAdapter extends RecyclerView.Adapter<AllLowonganAdapter.AllLowonganViewHolder>{

    Context context;
    ArrayList <ObjectLowongan> LowonganList;
    private AllLowonganAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick();
    }

    public void setOnItemClickListener(AllLowonganAdapter.OnItemClickListener listener){
        mListener = listener;
    }

    public AllLowonganAdapter(Context context, ArrayList<ObjectLowongan> newList){
        this.context = context;
        this.LowonganList = newList;
    }

    @NonNull
    @Override
    public AllLowonganViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_component_card_jasa,parent,false);
        return new AllLowonganViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AllLowonganViewHolder holder, int position) {
        holder.txtName.setText(LowonganList.get(position).getNama());
        holder.txtLokasi.setText(LowonganList.get(position).getLokasi());
        holder.txtDeskripsi.setText(LowonganList.get(position).getDeskripsi());
    }

    @Override
    public int getItemCount() {
        return LowonganList.size();
    }

    public class AllLowonganViewHolder extends RecyclerView.ViewHolder{
        TextView txtName, txtLokasi, txtDeskripsi;
        public AllLowonganViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.componentcardlowongan_namaUMKM);
            txtLokasi = itemView.findViewById(R.id.componentcardlowongan_lokasi);
            txtDeskripsi = itemView.findViewById(R.id.componentcardlowongan_deskripsi);

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
