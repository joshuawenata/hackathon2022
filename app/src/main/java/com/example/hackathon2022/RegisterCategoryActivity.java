package com.example.hackathon2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.hackathon2022.data.UserRepository;

public class RegisterCategoryActivity extends AppCompatActivity {

    String nama, nomor, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_category);

        initComponents();
    }

    private void initComponents() {
        Intent intent = getIntent();
        nama = intent.getStringExtra("nama");
        nomor = intent.getStringExtra("nomor");
        password = intent.getStringExtra("password");
        UserRepository.insertUser(nama, nomor, password);
    }
}