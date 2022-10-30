package com.example.hackathon2022.data;

import androidx.annotation.NonNull;

import com.example.hackathon2022.data.models.User;
import com.example.hackathon2022.interfaces.FinisihListener;
import com.example.hackathon2022.utils.Crypt;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UserRepository {
    private static FirebaseDatabase db = FirebaseDatabase.getInstance();
    private static DatabaseReference userRef = db.getReference("users");

    public static User LOGGED_IN_USER = null;

    public static void insertUser(String userName, String userNomor, String userPass, String deskripsi, String kategori, String lokasi, String imagepath, String star, String date, String backgroundimagepath, String role){
        String userKey = userRef.push().getKey();
        User newUser = new User(userKey, userName, userNomor, Crypt.generateHash(userPass), deskripsi, kategori, lokasi, imagepath, star, date, backgroundimagepath, role);
        userRef.child(userKey).setValue(newUser.toMap());
    }

    //TO DO: authentication user sign in
    public static void auth(String nomor, String password, FinisihListener<User> listener){
        Query query =userRef.orderByChild("nomor").equalTo(nomor).limitToFirst(1);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User temp = transformSnapshot(snapshot);

                if(temp == null){
                    //handle error
                    if(listener != null) listener.onFinish(null, "Nomor tidak terdaftar!");
                }
                else{
                    if(!Crypt.verifyHash(password, temp.getUserPass())){
                        if(listener != null) listener.onFinish(null, "Password salah!");
                    }
                    else{
                        if(listener != null) listener.onFinish(temp, "OK");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static User transformSnapshot(DataSnapshot snapshot) {
        if(!snapshot.exists()) return null;

        String nomor;
        String nama;
        String password;
        String deskripsi;
        String kategori;
        String lokasi;
        String imagepath;
        String star;
        String date;
        String backgroundimagepath;
        String role;
        snapshot = snapshot.getChildren().iterator().next();

        nomor = snapshot.child("nomor").getValue(String.class);
        nama = snapshot.child("nama").getValue(String.class);
        password = snapshot.child("password").getValue(String.class);
        deskripsi = snapshot.child("deskripsi").getValue(String.class);
        kategori = snapshot.child("kategori").getValue(String.class);
        lokasi = snapshot.child("lokasi").getValue(String.class);
        imagepath = snapshot.child("imagepath").getValue(String.class);
        star = snapshot.child("star").getValue(String.class);
        date = snapshot.child("date").getValue(String.class);
        backgroundimagepath = snapshot.child("backgroundimagepath").getValue(String.class);
        role = snapshot.child("role").getValue(String.class);

        return new User(snapshot.getKey(), nama, nomor, password, deskripsi, kategori, lokasi, imagepath, star, date, backgroundimagepath, role);
    }

    public static void find(String userKey, FinisihListener<User> listener){
        Query query =userRef.orderByKey().equalTo(userKey).limitToFirst(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User temp = transformSnapshot(snapshot);
                if(listener != null) listener.onFinish(temp, null);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}