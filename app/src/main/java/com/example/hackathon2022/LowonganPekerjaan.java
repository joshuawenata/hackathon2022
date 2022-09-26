package com.example.hackathon2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hackathon2022.R;

public class LowonganPekerjaan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lowongan_pekerjaan);
    }

    public void intoForum(View view) {
        startActivity(new Intent(this, HomePage.class));
    }

    public void intoSupplier(View view) {
        startActivity(new Intent(this, SupplierPage.class));
    }

    public void intoAdd(View view) {
        startActivity(new Intent(this, addlowongan.class));
    }
}