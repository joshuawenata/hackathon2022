package com.example.hackathon2022.data.models;

import java.util.HashMap;
import java.util.Map;

public class Supplier {
    private String supplierKey;
    private String supplierName;
    private String supplierLokasi;
    private String supplierDeskripsi;
    private String supplierProfile;

    public Supplier(String supplierKey, String supplierName, String supplierLokasi, String supplierDeskripsi, String supplierProfile) {
        this.supplierKey = supplierKey;
        this.supplierName = supplierName;
        this.supplierLokasi = supplierLokasi;
        this.supplierDeskripsi = supplierDeskripsi;
        this.supplierProfile = supplierProfile;
    }

    public String getSupplierKey() {
        return supplierKey;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getSupplierLokasi() {
        return supplierLokasi;
    }

    public String getSupplierDeskripsi() {
        return supplierDeskripsi;
    }

    public String getSupplierProfile() {
        return supplierProfile;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> data = new HashMap<>();
        data.put("nama", supplierName);
        data.put("lokasi", supplierLokasi);
        data.put("deskripsi", supplierDeskripsi);
        data.put("profile", supplierProfile);
        return data;
    }
}
