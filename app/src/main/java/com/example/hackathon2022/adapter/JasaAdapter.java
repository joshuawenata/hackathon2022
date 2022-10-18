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

public class JasaAdapter extends RecyclerView.Adapter<JasaAdapter.LowonganViewHolder>{

    Context context;
    ArrayList <ObjectLowongan> LowonganList;

    public ArrayList<ObjectLowongan> getLowonganList() {
        return LowonganList;
    }

    public void setLowonganList(ArrayList<ObjectLowongan> lowonganList) {
        LowonganList = lowonganList;
    }

    private JasaAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(String key, String nama, String lokasi, String deskripsi, String date, String star, String nomor);
    }

    public void setOnItemClickListener(JasaAdapter.OnItemClickListener listener){
        mListener = listener;
    }

    public JasaAdapter(Context context, ArrayList<ObjectLowongan> newList){
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
        holder.txtStar.setText(LowonganList.get(position).getStar());
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
        TextView txtName, txtLokasi, txtDeskripsi, txtDate, txtStar;
        public LowonganViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.componentcardlowongan_namaUMKM);
            txtLokasi = itemView.findViewById(R.id.componentcardlowongan_lokasi);
            txtDeskripsi = itemView.findViewById(R.id.componentcardlowongan_deskripsi);
            txtStar = itemView.findViewById(R.id.componentcardlowongan_starcount);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nama, lokasi, deskripsi, date, star, key, nomor;
                    ArrayList<ObjectLowongan> Llist = getLowonganList();
                    int position = getAdapterPosition();

                    nama = Llist.get(position).getNama();
                    lokasi = Llist.get(position).getLokasi();
                    deskripsi = Llist.get(position).getDeskripsi();
                    date = Llist.get(position).getDate();
                    star = Llist.get(position).getStar();
                    key = Llist.get(position).getKey();
                    nomor = Llist.get(position).getNomor();

                    if(mListener!=null){
                        mListener.onItemClick(key, nama, lokasi, deskripsi, date, star, nomor);
                    }
                }
            });
        }
    }

}
