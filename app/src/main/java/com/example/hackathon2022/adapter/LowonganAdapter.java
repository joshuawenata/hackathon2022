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

public class LowonganAdapter extends RecyclerView.Adapter<LowonganAdapter.LowonganViewHolder>{

    Context context;
    ArrayList <ObjectLowongan> LowonganList;
    private LowonganAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick();
    }

    public void setOnItemClickListener(LowonganAdapter.OnItemClickListener listener){
        mListener = listener;
    }

    public LowonganAdapter(Context context, ArrayList<ObjectLowongan> newList){
        this.context = context;
        this.LowonganList = newList;
    }

    @NonNull
    @Override
    public LowonganViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_component_card_jasa,parent,false);
        return new LowonganViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LowonganViewHolder holder, int position) {
        holder.txtName.setText(LowonganList.get(position).getNama());
        holder.txtLokasi.setText(LowonganList.get(position).getLokasi());
        holder.txtDeskripsi.setText(LowonganList.get(position).getDeskripsi());
    }

    @Override
    public int getItemCount() {
        if(LowonganList.size()>=5){
            return 5;
        }else{
            return LowonganList.size();
        }
    }

    public class LowonganViewHolder extends RecyclerView.ViewHolder{
        TextView txtName, txtLokasi, txtDeskripsi;
        public LowonganViewHolder(@NonNull View itemView) {
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
