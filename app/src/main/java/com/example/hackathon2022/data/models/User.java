package com.example.hackathon2022.data.models;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String userKey;
    private String userName;
    private String userNomor;
    private String userPass;
    private String deskripsi;
    private String kategori;
    private String lokasi;
    private String imagepath;

    public User(String userKey, String userName, String userNomor, String userPass, String deskripsi, String kategori, String lokasi, String imagepath) {
        this.userKey = userKey;
        this.userName = userName;
        this.userNomor = userNomor;
        this.userPass = userPass;
        this.deskripsi = deskripsi;
        this.kategori = kategori;
        this.lokasi = lokasi;
        this.imagepath = imagepath;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getUserKey() {
        return userKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserNomor(String userNomor) {
        this.userNomor = userNomor;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserNomor() {
        return userNomor;
    }

    public String getUserPass() {
        return userPass;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> data = new HashMap<>();
        data.put("nama", userName);
        data.put("nomor", userNomor);
        data.put("password", userPass);
        data.put("deskripsi", deskripsi);
        data.put("kategori", kategori);
        data.put("lokasi", lokasi);
        data.put("imagepath", imagepath);
        return data;
    }
}
