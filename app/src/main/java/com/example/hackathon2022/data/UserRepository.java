package com.example.hackathon2022.data;

import com.example.hackathon2022.data.models.User;
import com.example.hackathon2022.utils.Crypt;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRepository {
    private static FirebaseDatabase db = FirebaseDatabase.getInstance();
    private static DatabaseReference userRef = db.getReference("users");

    public static void insertUser(String userName, String userNomor, String userPass){
        String userKey = userRef.push().getKey();
        User newUser = new User(userKey, userName, userNomor, Crypt.generateHash(userPass));
        userRef.child(userKey).setValue(newUser.toMap());
    }

    //TO DO: authentication user sign in
    public static void auth(String nomor, String password){

    }
}
