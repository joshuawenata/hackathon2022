package com.example.hackathon2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hackathon2022.data.UserRepository;

public class RegisterCategoryActivity extends AppCompatActivity implements View.OnClickListener{

    static int checklist = 0 ;
    String nama, nomor, password, deskripsi, kategori;
    ImageButton fnb, pakaian, elektronik, pangan, transportasi, pengolahan;
    ImageView fnbcheck, pakaiancheck, elektronikcheck, pangancheck, transportasicheck, pengolahancheck;
    Button lanjut;

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
        deskripsi = intent.getStringExtra("deskripsi");

        fnbcheck = findViewById(R.id.fnb_check);
        pakaiancheck = findViewById(R.id.fabrikasi_check);
        elektronikcheck = findViewById(R.id.elektronik_check);
        pangancheck = findViewById(R.id.pangan_check);
        transportasicheck = findViewById(R.id.transportasi_check);
        pengolahancheck = findViewById(R.id.pengolahan_check);

        lanjut = findViewById(R.id.activityregistercategory_lanjutbtn);
        fnb = findViewById(R.id.activityregistercategory_fnb);
        pakaian = findViewById(R.id.activityregistercategory_pakaian);
        elektronik = findViewById(R.id.activityregistercategory_elektronik);
        pangan = findViewById(R.id.activityregistercategory_pangan);
        transportasi = findViewById(R.id.activityregistercategory_transportasi);
        pengolahan = findViewById(R.id.activityregistercategory_pengolahan);

        lanjut.setOnClickListener(this);
        fnb.setOnClickListener(this);
        pakaian.setOnClickListener(this);
        elektronik.setOnClickListener(this);
        pangan.setOnClickListener(this);
        transportasi.setOnClickListener(this);
        pengolahan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.equals(fnb)) {
            kategori = "FnB";
            if(checklist==0){
                fnbcheck.setVisibility(View.VISIBLE);
                checklist = 1;
            }else{
                fnbcheck.setVisibility(View.VISIBLE);
                pakaiancheck.setVisibility(View.INVISIBLE);
                elektronikcheck.setVisibility(View.INVISIBLE);
                pangancheck.setVisibility(View.INVISIBLE);
                transportasicheck.setVisibility(View.INVISIBLE);
                pengolahancheck.setVisibility(View.INVISIBLE);
            }
        }
        else if(v.equals(pakaian)) {
            kategori = "Pakaian / Fabrikasi";
            if(checklist==0){
                pakaiancheck.setVisibility(View.VISIBLE);
                checklist = 1;
            }else{
                fnbcheck.setVisibility(View.INVISIBLE);
                pakaiancheck.setVisibility(View.VISIBLE);
                elektronikcheck.setVisibility(View.INVISIBLE);
                pangancheck.setVisibility(View.INVISIBLE);
                transportasicheck.setVisibility(View.INVISIBLE);
                pengolahancheck.setVisibility(View.INVISIBLE);
            }
        }
        else if(v.equals(elektronik)) {
            kategori = "Elektronik";
            if(checklist==0){
                elektronikcheck.setVisibility(View.VISIBLE);
                checklist = 1;
            }else{
                fnbcheck.setVisibility(View.INVISIBLE);
                pakaiancheck.setVisibility(View.INVISIBLE);
                elektronikcheck.setVisibility(View.VISIBLE);
                pangancheck.setVisibility(View.INVISIBLE);
                transportasicheck.setVisibility(View.INVISIBLE);
                pengolahancheck.setVisibility(View.INVISIBLE);
            }
        }
        else if(v.equals(pangan)) {
            kategori = "Pangan";
            if(checklist==0){
                pangancheck.setVisibility(View.VISIBLE);
                checklist = 1;
            }else{
                fnbcheck.setVisibility(View.INVISIBLE);
                pakaiancheck.setVisibility(View.INVISIBLE);
                elektronikcheck.setVisibility(View.INVISIBLE);
                pangancheck.setVisibility(View.VISIBLE);
                transportasicheck.setVisibility(View.INVISIBLE);
                pengolahancheck.setVisibility(View.INVISIBLE);
            }
        }
        else if(v.equals(transportasi)) {
            kategori = "Transportasi";
            if(checklist==0){
                transportasicheck.setVisibility(View.VISIBLE);
                checklist = 1;
            }else{
                fnbcheck.setVisibility(View.INVISIBLE);
                pakaiancheck.setVisibility(View.INVISIBLE);
                elektronikcheck.setVisibility(View.INVISIBLE);
                pangancheck.setVisibility(View.INVISIBLE);
                transportasicheck.setVisibility(View.VISIBLE);
                pengolahancheck.setVisibility(View.INVISIBLE);
            }
        }
        else if(v.equals(pengolahan)) {
            kategori = "Pengolahan";
            if(checklist==0){
                pengolahancheck.setVisibility(View.VISIBLE);
                checklist = 1;
            }else{
                fnbcheck.setVisibility(View.INVISIBLE);
                pakaiancheck.setVisibility(View.INVISIBLE);
                elektronikcheck.setVisibility(View.INVISIBLE);
                pangancheck.setVisibility(View.INVISIBLE);
                transportasicheck.setVisibility(View.INVISIBLE);
                pengolahancheck.setVisibility(View.VISIBLE);
            }
        }
        else if(v.equals(lanjut)){
            if(checklist==0){
                Toast.makeText(this, "Please pick a category!", Toast.LENGTH_SHORT).show();
            }else{
                UserRepository.insertUser(nama, nomor, password, deskripsi, kategori);
                startActivity(new Intent(this, LoginPage.class));
                checklist=0;
            }
        }

    }
}