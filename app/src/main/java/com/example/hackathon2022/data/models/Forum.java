package com.example.hackathon2022.data.models;

import java.util.HashMap;
import java.util.Map;

public class Forum {
    private String ForumKey;
    private String ForumJudul;
    private String ForumKategori;
    private String ForumPertanyaan;
    private String ForumFilePath;

    public String getForumUserName() {
        return ForumUserName;
    }

    public void setForumUserName(String forumUserName) {
        ForumUserName = forumUserName;
    }

    private String ForumUserName;

    public String getForumKategori() {
        return ForumKategori;
    }

    public void setForumKategori(String forumKategori) {
        ForumKategori = forumKategori;
    }

    public Forum(String ForumKey, String ForumJudul, String ForumKategori, String ForumPertanyaan, String ForumFilePath, String username) {
        this.ForumKey = ForumKey;
        this.ForumJudul = ForumJudul;
        this.ForumKategori = ForumKategori;
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
        data.put("kategori", ForumKategori);
        data.put("pertanyaan", ForumPertanyaan);
        data.put("filepath", ForumFilePath);
        data.put("username",ForumUserName);
        return data;
    }
}
