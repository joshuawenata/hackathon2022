package com.example.hackathon2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.hackathon2022.data.UserRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        boolean flag = true;
        if(nama.isEmpty()){
            txtNama.setError("Silahkan masukan nama");
            flag = false;
        }
        else if(lokasi.isEmpty()){
            txtLokasi.setError("Silahkan masukan lokasi");
            flag = false;
        }
        else if(deskripsi.isEmpty()){
            txtDescription.setError("Silahkan masukan deskripsi");
            flag = false;
        }
        else{
            flag = true;
        }

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        if(flag){
            UserRepository.insertUser(nama, nomor, password, deskripsi, kategori, lokasi, "avatar.png", "0", dateFormat.format(date), "backgroundimage.png", "users");
            startActivity(new Intent(this, LoginPage.class));
            finishAffinity();
        }

    }
}