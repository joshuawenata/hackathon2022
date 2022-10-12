package com.example.hackathon2022.data;

import com.example.hackathon2022.data.models.Forum;
import com.example.hackathon2022.data.models.ReplyForum;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReplyForumRepository {
    private static FirebaseDatabase db = FirebaseDatabase.getInstance();
    private static DatabaseReference replyRef = db.getReference("replyforum");

    public static void insertReplyForum(String key, String username, String answer){
        String replyKey = replyRef.push().getKey();
        ReplyForum newReply = new ReplyForum(replyKey,
                username,
                answer,
                key);
        replyRef.child(replyKey).setValue(newReply.toMap());
    }
}
