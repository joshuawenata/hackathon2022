package com.example.hackathon2022.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackathon2022.Object.ObjectLowongan;
import com.example.hackathon2022.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JasaAdapter extends RecyclerView.Adapter<JasaAdapter.LowonganViewHolder>{

    StorageReference storageReference;
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
        void onItemClick(String key, String nama, String jasa, String lokasi, String deskripsi, String date, String star, String nomor);
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
        holder.txtJasa.setText(LowonganList.get(position).getKategori());
        holder.txtStar.setText(LowonganList.get(position).getStar());

        storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://hackathon2022-85c99.appspot.com").child(LowonganList.get(position).getImagepath());
        try {
            File localfile = File.createTempFile("temp","jpg");
            storageReference.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                    holder.lblimage.setImageBitmap(bitmap);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return LowonganList.size();
    }

    public class LowonganViewHolder extends RecyclerView.ViewHolder{
        TextView txtName, txtLokasi, txtDeskripsi, txtStar, txtJasa;
        ImageView lblimage;
        public LowonganViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.componentcardlowongan_namaUMKM);
            txtLokasi = itemView.findViewById(R.id.componentcardlowongan_lokasi);
            txtDeskripsi = itemView.findViewById(R.id.componentcardlowongan_deskripsi);
            txtStar = itemView.findViewById(R.id.componentcardlowongan_starcount);
            txtJasa = itemView.findViewById(R.id.componentcardjasa_jasa);
            lblimage = itemView.findViewById(R.id.componentcardjasa_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nama, lokasi, deskripsi, date, star, key, nomor, jasa;
                    ArrayList<ObjectLowongan> Llist = getLowonganList();
                    int position = getAdapterPosition();

                    nama = Llist.get(position).getNama();
                    lokasi = Llist.get(position).getLokasi();
                    deskripsi = Llist.get(position).getDeskripsi();
                    date = Llist.get(position).getDate();
                    jasa = Llist.get(position).getKategori();
                    star = Llist.get(position).getStar();
                    key = Llist.get(position).getKey();
                    nomor = Llist.get(position).getNomor();

                    if(mListener!=null){
                        mListener.onItemClick(key, nama, jasa, lokasi, deskripsi, date, star, nomor);
                    }
                }
            });
        }
    }

}
