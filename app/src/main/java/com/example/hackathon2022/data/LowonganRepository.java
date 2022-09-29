package com.example.hackathon2022.data;

import com.example.hackathon2022.data.models.Lowongan;
import com.example.hackathon2022.data.models.Supplier;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LowonganRepository {

    private static FirebaseDatabase db = FirebaseDatabase.getInstance();
    private static DatabaseReference lowonganRef = db.getReference("lowongan");

    public static void insertLowongan(String lowonganName, String lowonganLokasi, String lowonganDeskripsi){
        String lowonganKey = lowonganRef.push().getKey();
        Lowongan newLowongan = new Lowongan(lowonganKey,
                lowonganName,
                lowonganLokasi,
                lowonganDeskripsi);
        lowonganRef.child(lowonganKey).setValue(newLowongan.toMap());
    }
}
