package com.example.hackathon2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.hackathon2022.data.UserRepository;

public class RegisterUMKM extends AppCompatActivity implements View.OnClickListener{

    EditText txtNama, txtDescription, txtLokasi;
    ImageButton btnDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_umkm);

        initComponents();
    }

    private void initComponents() {
        txtNama = findViewById(R.id.activityregisterumkm_inputfullname);
        txtDescription = findViewById(R.id.activityregisterumkm_inputdescription);
        txtLokasi = findViewById(R.id.activityregisterumkm_inputlokasi);
        btnDaftar = findViewById(R.id.activityregisterumkm_btnDaftar);

        btnDaftar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String nama, deskripsi, lokasi, nomor, password, kategori;
        nama = txtNama.getText().toString();
        deskripsi = txtDescription.getText().toString();
        lokasi = txtLokasi.getText().toString();

        Intent intent = getIntent();
        nomor = intent.getStringExtra("nomor");
        password = intent.getStringExtra("password");
        kategori = intent.getStringExtra("kategori");

        UserRepository.insertUser(nama, nomor, password, deskripsi, kategori, lokasi, "avatar.png");
        startActivity(new Intent(this, LoginPage.class));
        finish();
    }
}