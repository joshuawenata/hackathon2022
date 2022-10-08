package com.example.hackathon2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void intoSupplier(View view) {
        startActivity(new Intent(this, SupplierPage.class));
    }

    public void intoLowongan(View view) {
        startActivity(new Intent(this, LowonganPekerjaan.class));
    }

    public void intoHome(View view) {
        startActivity(new Intent(this, HomePage.class));
    }
}