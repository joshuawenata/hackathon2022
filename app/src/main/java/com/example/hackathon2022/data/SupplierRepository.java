package com.example.hackathon2022.data;

import com.example.hackathon2022.data.models.Supplier;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SupplierRepository {
    private static FirebaseDatabase db = FirebaseDatabase.getInstance();
    private static DatabaseReference supplierRef = db.getReference("suppliers");

    public static void insertSupplier(String supplierName, String supplierLokasi, String supplierKategori, String supplierDeskripsi, String imagePath){
        String supplierKey = supplierRef.push().getKey();
        Supplier newSupplier = new Supplier(supplierKey,
                supplierName,
                supplierLokasi,
                supplierKategori,
                supplierDeskripsi,
                imagePath);
        supplierRef.child(supplierKey).setValue(newSupplier.toMap());
    }
}
