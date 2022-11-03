package com.example.hackathon2022.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

import com.example.hackathon2022.Object.ObjectGallery;
import com.example.hackathon2022.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FreelancerAdapter extends RecyclerView.Adapter<FreelancerAdapter.GalleryViewHolder>{

    StorageReference storageReference;
    Context context;
    ArrayList<ObjectGallery> galleryList;

    public FreelancerAdapter(Context context, ArrayList<ObjectGallery> newList) {
        this.context = context;
        this.galleryList = newList;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_component_gallery,parent,false);
        return new FreelancerAdapter.GalleryViewHolder(v);
    }

    public ArrayList<ObjectGallery> getGalleryList() {
        return galleryList;
    }

    public void setGalleryList(ArrayList<ObjectGallery> forumList) {
        this.galleryList = forumList;
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://hackathon2022-85c99.appspot.com").child(galleryList.get(position).getFilepath());
        try {
            File localfile = File.createTempFile("temp","jpg");
            storageReference.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                    holder.imgview.setImageBitmap(bitmap);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    public class GalleryViewHolder extends RecyclerView.ViewHolder {
        ImageView imgview;
        GalleryViewHolder(@NonNull View itemView) {
            super(itemView);
            imgview = itemView.findViewById(R.id.componentgallery_imgview);
        }
    }

}
