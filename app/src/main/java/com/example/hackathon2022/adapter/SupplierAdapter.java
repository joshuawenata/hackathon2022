package com.example.hackathon2022.adapter;

import android.annotation.SuppressLint;
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

import com.example.hackathon2022.Object.ObjectSupplier;
import com.example.hackathon2022.Object.ObjectUser;
import com.example.hackathon2022.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SupplierAdapter extends RecyclerView.Adapter<SupplierAdapter.SupplierViewHolder>{

    StorageReference storageReference;

    Context context;
    ArrayList<ObjectUser> SupplierList;
    private SupplierAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(String key, String judul, String category, String description, String nomor, String star, String date, String backgroundimagepath);
    }

    public void setOnItemClickListener(SupplierAdapter.OnItemClickListener listener){
        mListener = listener;
    }

    public SupplierAdapter(Context context, ArrayList<ObjectUser> newList) {
        this.context = context;
        this.SupplierList = newList;
    }

    @NonNull
    @Override
    public SupplierViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_component_card_supplier,parent,false);
        return new SupplierAdapter.SupplierViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SupplierViewHolder holder, int position) {
        holder.txtName.setText(SupplierList.get(position).getNama());
        holder.txtLokasi.setText(SupplierList.get(position).getLokasi());
        holder.txtKategori.setText(SupplierList.get(position).getKategori());
        holder.txtDeskripsi.setText(SupplierList.get(position).getDeskripsi());
        if(Integer.valueOf(SupplierList.get(position).getStar())>999){
            float temp = (float)Float.valueOf(SupplierList.get(position).getStar())/1000;
            holder.star.setText(String.valueOf(String.format("%.1f",temp))+" ribu");
        }else{
            holder.star.setText(SupplierList.get(position).getStar().toString());
        }

        storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://hackathon2022-85c99.appspot.com").child(SupplierList.get(position).getImagePath());
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
        return SupplierList.size();
    }

    public class SupplierViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtLokasi, txtKategori, txtDeskripsi, star;
        ImageView lblimage;
        SupplierViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.componentcardsupplier_nama);
            txtLokasi = itemView.findViewById(R.id.componentcardsupplier_lokasi);
            txtKategori = itemView.findViewById(R.id.componentcardsupplier_kategori);
            txtDeskripsi = itemView.findViewById(R.id.componentcardsupplier_deskripsi);
            lblimage = itemView.findViewById(R.id.componentcardsupplier_image);
            star = itemView.findViewById(R.id.componentcardsupplier_starcount);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    String judul, category, description, nomor, key, star, date, backgroundimagepath;

                    judul = SupplierList.get(position).getNama();
                    category = SupplierList.get(position).getKategori();
                    description = SupplierList.get(position).getDeskripsi();
                    nomor = SupplierList.get(position).getNomor();
                    star = SupplierList.get(position).getStar().toString();
                    key = SupplierList.get(position).getKey();
                    date = SupplierList.get(position).getDate();
                    backgroundimagepath = SupplierList.get(position).getBackgroundimagepath();

                    if(mListener!=null){
                        mListener.onItemClick(key, judul, category, description, nomor, star, date, backgroundimagepath);
                    }
                }
            });
        }
    }
}
