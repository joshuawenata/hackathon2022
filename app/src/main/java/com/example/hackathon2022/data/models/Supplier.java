package com.example.hackathon2022.data.models;

import java.util.HashMap;
import java.util.Map;

public class Supplier {
    private String supplierKey;
    private String supplierName;
    private String supplierLokasi;
    private String supplierDeskripsi;

    public Supplier(String supplierKey, String supplierName, String supplierLokasi, String supplierDeskripsi) {
        this.supplierKey = supplierKey;
        this.supplierName = supplierName;
        this.supplierLokasi = supplierLokasi;
        this.supplierDeskripsi = supplierDeskripsi;
    }

    public String getUserKey() {
        return supplierKey;
    }

    public String getUserName() {
        return supplierName;
    }

    public String getUserLokasi() {
        return supplierLokasi;
    }

    public String getUserDeskripsi() {
        return supplierDeskripsi;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> data = new HashMap<>();
        data.put("nama", supplierName);
        data.put("lokasi", supplierLokasi);
        data.put("deskripsi", supplierDeskripsi);
        return data;
    }
}
