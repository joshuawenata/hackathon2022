package com.example.hackathon2022.data;

import com.example.hackathon2022.data.models.Supplier;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SupplierRepository {
    private static FirebaseDatabase db = FirebaseDatabase.getInstance();
    private static DatabaseReference supplierRef = db.getReference("suppliers");

    public static void insertSupplier(String supplierName, String supplierLokasi, String supplierDeskripsi){
        String supplierKey = supplierRef.push().getKey();
        Supplier newSupplier = new Supplier(supplierKey, supplierName, supplierLokasi, supplierDeskripsi);
        supplierRef.child(supplierKey).setValue(newSupplier.toMap());
    }
}
