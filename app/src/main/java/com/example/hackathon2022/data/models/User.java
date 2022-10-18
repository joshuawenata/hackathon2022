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
    private String star;
    private String date;
    private String backgroundimagepath;

    public User(String key, String userName, String userNomor, String userPass, String deskripsi, String kategori, String lokasi, String imagepath, String star, String date, String backgroundimagepath) {
        this.userKey = key;
        this.userName = userName;
        this.userNomor = userNomor;
        this.userPass = userPass;
        this.deskripsi = deskripsi;
        this.kategori = kategori;
        this.lokasi = lokasi;
        this.imagepath = imagepath;
        this.star = star;
        this.date = date;
        this.backgroundimagepath = backgroundimagepath;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBackgroundimagepath() {
        return backgroundimagepath;
    }

    public void setBackgroundimagepath(String backgroundimagepath) {
        this.backgroundimagepath = backgroundimagepath;
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

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> data = new HashMap<>();
        data.put("key", userKey);
        data.put("nama", userName);
        data.put("nomor", userNomor);
        data.put("password", userPass);
        data.put("deskripsi", deskripsi);
        data.put("kategori", kategori);
        data.put("lokasi", lokasi);
        data.put("imagepath", imagepath);
        data.put("star", star);
        data.put("date",date);
        return data;
    }
}
