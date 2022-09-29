package com.example.hackathon2022.data.models;

import java.util.HashMap;
import java.util.Map;

public class Lowongan {
    private String LowonganKey;
    private String LowonganName;
    private String LowonganLokasi;
    private String LowonganDeskripsi;

    public Lowongan(String LowonganKey, String LowonganName, String LowonganLokasi, String LowonganDeskripsi) {
        this.LowonganKey = LowonganKey;
        this.LowonganName = LowonganName;
        this.LowonganLokasi = LowonganLokasi;
        this.LowonganDeskripsi = LowonganDeskripsi;
    }

    public String getLowonganKey() {
        return LowonganKey;
    }

    public String getLowonganName() {
        return LowonganName;
    }

    public String getLowonganLokasi() {
        return LowonganLokasi;
    }

    public String getLowonganDeskripsi() {
        return LowonganDeskripsi;
    }


    public Map<String, Object> toMap(){
        Map<String, Object> data = new HashMap<>();
        data.put("nama", LowonganName);
        data.put("lokasi", LowonganLokasi);
        data.put("deskripsi", LowonganDeskripsi);
        return data;
    }
}
