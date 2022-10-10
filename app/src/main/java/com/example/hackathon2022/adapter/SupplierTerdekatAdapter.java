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

import com.example.hackathon2022.Object.ObjectSupplier;
import com.example.hackathon2022.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SupplierTerdekatAdapter extends RecyclerView.Adapter<SupplierTerdekatAdapter.SupplierViewHolder>{

    StorageReference storageReference;

    Context context;
    ArrayList<ObjectSupplier> SupplierList;

    public SupplierTerdekatAdapter(Context context, ArrayList<ObjectSupplier> newList) {
        this.context = context;
        this.SupplierList = newList;
    }

    @NonNull
    @Override
    public SupplierViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.component_cardsupplier,parent,false);
        return new SupplierTerdekatAdapter.SupplierViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SupplierViewHolder holder, int position) {
        holder.txtName.setText(SupplierList.get(position).getNama());
        holder.txtLokasi.setText(SupplierList.get(position).getLokasi());
        holder.txtKategori.setText(SupplierList.get(position).getKategori());
        holder.txtDeskripsi.setText(SupplierList.get(position).getDeskripsi());

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
        if(SupplierList.size()>=2){
            return 2;
        }else{
            return SupplierList.size();
        }
    }

    public class SupplierViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtLokasi, txtKategori, txtDeskripsi;
        ImageView lblimage;
        SupplierViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.componentcardsupplier_nama);
            txtLokasi = itemView.findViewById(R.id.componentcardsupplier_lokasi);
            txtKategori = itemView.findViewById(R.id.componentcardsupplier_kategori);
            txtDeskripsi = itemView.findViewById(R.id.componentcardsupplier_deskripsi);
            lblimage = itemView.findViewById(R.id.componentcardsupplier_image);
        }
    }
}
