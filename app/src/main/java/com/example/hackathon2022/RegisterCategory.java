package com.example.hackathon2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterCategory extends AppCompatActivity implements View.OnClickListener{

    static int checklist = 0 ;
    String nomor, password, kategori;
    ConstraintLayout fnb, pakaian, elektronik, pangan, transportasi, pengolahan;
    ImageButton fnbic, pakaianic, elektronikic, panganic, transportasiic, pengolahanic;
    ImageView fnbcheck, pakaiancheck, elektronikcheck, pangancheck, transportasicheck, pengolahancheck;
    ImageButton lanjut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_category);

        initComponents();
    }

    private void initComponents() {
        Intent intent = getIntent();
        nomor = intent.getStringExtra("nomor");
        password = intent.getStringExtra("password");

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

        fnbic = findViewById(R.id.activityregistercategory_fnbic);
        pakaianic = findViewById(R.id.activityregistercategory_pakaianic);
        elektronikic = findViewById(R.id.activityregistercategory_elektronikic);
        panganic = findViewById(R.id.activityregistercategory_panganic);
        transportasiic = findViewById(R.id.activityregistercategory_transportasiic);
        pengolahanic = findViewById(R.id.activityregistercategory_pengolahanic);

        lanjut.setOnClickListener(this);
        fnb.setOnClickListener(this);
        pakaian.setOnClickListener(this);
        elektronik.setOnClickListener(this);
        pangan.setOnClickListener(this);
        transportasi.setOnClickListener(this);
        pengolahan.setOnClickListener(this);
        fnbic.setOnClickListener(this);
        pakaianic.setOnClickListener(this);
        elektronikic.setOnClickListener(this);
        panganic.setOnClickListener(this);
        transportasiic.setOnClickListener(this);
        pengolahanic.setOnClickListener(this);
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
        else if(v.equals(fnbic)) {
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
        else if(v.equals(pakaianic)) {
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
        else if(v.equals(elektronikic)) {
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
        else if(v.equals(panganic)) {
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
        else if(v.equals(transportasiic)) {
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
        else if(v.equals(pengolahanic)) {
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
                checklist=0;
                Intent i = new Intent(this, RegisterUMKM.class);
                i.putExtra("nomor", nomor);
                i.putExtra("password", password);
                i.putExtra("kategori", kategori);
                startActivity(i);
            }
        }

    }
}