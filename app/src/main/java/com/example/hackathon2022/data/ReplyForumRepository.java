package com.example.hackathon2022.data;

import com.example.hackathon2022.data.models.Forum;
import com.example.hackathon2022.data.models.ReplyForum;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReplyForumRepository {
    private static FirebaseDatabase db = FirebaseDatabase.getInstance();
    private static DatabaseReference replyRef = db.getReference("replyforum");

    public static void insertReplyForum(String username, String answer, String date, String forumkey){
        String replyKey = replyRef.push().getKey();
        ReplyForum newReply = new ReplyForum(username,
                answer,
                date,
                forumkey);
        replyRef.child(replyKey).setValue(newReply.toMap());
    }
}
