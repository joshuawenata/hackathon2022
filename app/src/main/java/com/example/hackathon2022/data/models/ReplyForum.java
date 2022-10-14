package com.example.hackathon2022.data.models;

import java.util.HashMap;
import java.util.Map;

public class ReplyForum {

    private String ReplyKey;
    private String username;
    private String answer;
    private String Date;
    private String ForumKey;

    public ReplyForum(String replyKey, String username, String answer, String date, String forumkey){
        this.ReplyKey = replyKey;
        this.ForumKey = forumkey;
        this.username = username;
        this.answer = answer;
        this.Date = date;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> data = new HashMap<>();
        data.put("key", ReplyKey);
        data.put("username",username);
        data.put("answer", answer);
        data.put("date", Date);
        data.put("forumkey",ForumKey);
        return data;
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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getForumKey() {
        return ForumKey;
    }

    public void setForumKey(String forumKey) {
        ForumKey = forumKey;
    }
}
