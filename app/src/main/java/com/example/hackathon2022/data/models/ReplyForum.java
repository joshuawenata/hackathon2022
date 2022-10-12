package com.example.hackathon2022.data.models;

import com.example.hackathon2022.R;

import java.util.HashMap;
import java.util.Map;

public class ReplyForum {
    public String getReplyKey() {
        return ReplyKey;
    }

    public void setReplyKey(String replyKey) {
        ReplyKey = replyKey;
    }

    public String getJudul() {
        return Judul;
    }

    public void setJudul(String judul) {
        Judul = judul;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getForumKey() {
        return ForumKey;
    }

    public void setForumKey(String forumKey) {
        ForumKey = forumKey;
    }

    private String ReplyKey;
    private String Judul;
    private String username;
    private String answer;
    private String ForumKey;

    public ReplyForum(String ReplyKey, String username, String answer, String ForumKey){
        this.ReplyKey = ReplyKey;
        this.username = username;
        this.answer = answer;
        this.ForumKey = ForumKey;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> data = new HashMap<>();
        data.put("key", ForumKey);
        data.put("username",username);
        data.put("answer", answer);
        return data;
    }
}
