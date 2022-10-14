package com.example.hackathon2022.data;

import com.example.hackathon2022.data.models.Forum;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ForumRepository {

    private static FirebaseDatabase db = FirebaseDatabase.getInstance();
    private static DatabaseReference forumRef = db.getReference("forum");

    public static void insertForum(String forumJudul, String forumKategori, String forumPertanyaan, String filePath, String username, String date, Integer star){
        String forumKey = forumRef.push().getKey();
        Forum newForum = new Forum(forumKey,
                forumJudul,
                forumKategori,
                forumPertanyaan,
                filePath,
                username,
                date,
                star);
        forumRef.child(forumKey).setValue(newForum.toMap());
    }
}
