package com.example.hackathon2022.data.models;

import java.util.HashMap;
import java.util.Map;

public class Forum {
    private String ForumKey;
    private String ForumJudul;
    private String ForumPertanyaan;
    private String ForumFilePath;

    public Forum(String ForumKey, String ForumJudul, String ForumPertanyaan, String ForumFilePath) {
        this.ForumKey = ForumKey;
        this.ForumJudul = ForumJudul;
        this.ForumPertanyaan = ForumPertanyaan;
        this.ForumFilePath = ForumFilePath;
    }

    public String getForumKey() {
        return ForumKey;
    }

    public String getForumJudul() {
        return ForumJudul;
    }

    public String getForumPertanyaan() {
        return ForumPertanyaan;
    }

    public String getForumFilePath() {
        return ForumFilePath;
    }


    public Map<String, Object> toMap(){
        Map<String, Object> data = new HashMap<>();
        data.put("judul", ForumJudul);
        data.put("pertanyaan", ForumPertanyaan);
        data.put("filepath", ForumFilePath);
        return data;
    }
}
