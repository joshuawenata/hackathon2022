package com.example.hackathon2022.data;

import com.example.hackathon2022.data.models.Forum;
import com.example.hackathon2022.data.models.Lamar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LamarRepository {
    private static FirebaseDatabase db = FirebaseDatabase.getInstance();
    private static DatabaseReference lamarRef = db.getReference("lamar");

    public static void insertLamar(String lamarPertanyaan, String filePath){
        String lamarKey = lamarRef.push().getKey();
        Lamar newLamar = new Lamar(lamarKey,
                lamarPertanyaan,
                filePath);
        lamarRef.child(lamarKey).setValue(newLamar.toMap());
    }
}
