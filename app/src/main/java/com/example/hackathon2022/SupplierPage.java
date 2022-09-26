package com.example.hackathon2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hackathon2022.R;

public class SupplierPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_page);
    }

    public void intoForum(View view) {
        startActivity(new Intent(this, HomePage.class));
    }

    public void intoLowongan(View view) { startActivity(new Intent(this, LowonganPekerjaan.class)); }

    public void intoAdd(View view) { startActivity(new Intent(this, addsupplier.class)); }
}