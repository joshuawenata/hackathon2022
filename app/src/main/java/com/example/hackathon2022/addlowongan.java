package com.example.hackathon2022;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.hackathon2022.R;
import com.example.hackathon2022.data.LowonganRepository;
import com.example.hackathon2022.data.SupplierRepository;

public class addlowongan extends AppCompatActivity {

    private EditText txtNama, txtLokasi, txtDeskripsi;
    private ImageButton btnAdd;

    @SuppressLint("WrongViewCast")
    private void initComponents() {
        txtNama = findViewById(R.id.activityaddlowongan_inputnamalowongan);
        txtLokasi = findViewById(R.id.activityaddlowongan_inputlokasilowongan);
        txtDeskripsi = findViewById(R.id.activityaddlowongan_inputDeskripsi);
        btnAdd = findViewById(R.id.activityaddlowongan_btnTambahLowongan);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlowongan);

        initComponents();
    }

    public void AddLowongan(View view) {
        String nama, lokasi, deskripsi;

        //get data
        nama = txtNama.getText().toString();
        lokasi = txtLokasi.getText().toString();
        deskripsi = txtDeskripsi.getText().toString();

        boolean flag = true;
        if(nama.isEmpty()){
            txtNama.setError("Silahkan masukan nama Lowongan");
            flag = false;
        }else if(lokasi.isEmpty()){
            txtLokasi.setError("Silahkan masukan lokasi Lowongan");
            flag = false;
        }else if(deskripsi.isEmpty()){
            txtDeskripsi.setError("Silahkan masukan deskripsi Lowongan");
            flag = false;
        }

        if(flag){
            LowonganRepository.insertLowongan(nama,
                    lokasi,
                    deskripsi);
            startActivity(new Intent(this, LowonganPekerjaan.class));
        }

    }

}