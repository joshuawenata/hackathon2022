package com.example.hackathon2022.data;

import android.net.Uri;

import com.example.hackathon2022.data.models.Gallery;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class GalleryRepository {
    private static FirebaseDatabase db = FirebaseDatabase.getInstance();
    private static DatabaseReference galleryRef = db.getReference("gallery");

    public static void insertGallery(String GalleryKey, String filePath) {
        String galleryKey = galleryRef.push().getKey();
        Gallery newGallery = new Gallery(GalleryKey, filePath);
        galleryRef.child(galleryKey).setValue(newGallery.toMap());
    }
}
