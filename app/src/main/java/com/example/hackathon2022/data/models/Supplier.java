package com.example.hackathon2022.data.models;

import java.util.HashMap;
import java.util.Map;

public class Supplier {
    private String supplierKey;
    private String supplierName;
    private String supplierLokasi;
    private String supplierDeskripsi;
    private String supplierKategori;

    public String getSupplierKategori() {
        return supplierKategori;
    }

    public void setSupplierKategori(String supplierKategori) {
        this.supplierKategori = supplierKategori;
    }

    private String supplierimagePath;

    public Supplier(String supplierKey, String supplierName, String supplierLokasi, String supplierKategori, String supplierDeskripsi, String supplierimagePath) {
        this.supplierKey = supplierKey;
        this.supplierKategori = supplierKategori;
        this.supplierName = supplierName;
        this.supplierLokasi = supplierLokasi;
        this.supplierDeskripsi = supplierDeskripsi;
        this.supplierimagePath = supplierimagePath;
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

    public String getSupplierimagePath() {
        return supplierimagePath;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> data = new HashMap<>();
        data.put("nama", supplierName);
        data.put("lokasi", supplierLokasi);
        data.put("kategori", supplierKategori);
        data.put("deskripsi", supplierDeskripsi);
        data.put("imagePath", supplierimagePath);
        return data;
    }
}
