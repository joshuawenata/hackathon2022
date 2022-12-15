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

public class RegisterFreelancerPageTwo extends AppCompatActivity implements View.OnClickListener{

    EditText txtNama, txtDescription, txtLokasi, txtJasa;
    ImageButton btnDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_freelancer_page_two);

        initComponents();
    }

    private void initComponents() {
        txtNama = findViewById(R.id.activityregisterfreelancertwo_inputfullname);
        txtDescription = findViewById(R.id.activityregisterfreelancertwo_inputdescription);
        txtLokasi = findViewById(R.id.activityregisterfreelancertwo_inputlokasi);
        txtJasa = findViewById(R.id.activityregisterfreelancertwo_inputjasa);
        btnDaftar = findViewById(R.id.activityregisterfreelancertwo_btnDaftar);

        btnDaftar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String nama, deskripsi, lokasi, nomor, password, jasa;
        nama = txtNama.getText().toString();
        deskripsi = txtDescription.getText().toString();
        jasa = txtJasa.getText().toString();
        lokasi = txtLokasi.getText().toString();

        Intent intent = getIntent();

        nomor = intent.getStringExtra("nomor");
        password = intent.getStringExtra("password");

        boolean flag = true;
        if(nama.isEmpty()){
            txtNama.setError("Silahkan masukan nama");
            flag = false;
        }
        else if(lokasi.isEmpty()){
            txtLokasi.setError("Silahkan masukan lokasi");
            flag = false;
        }
        else if(jasa.isEmpty()){
            txtJasa.setError("Silahkan masukan jasa anda");
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
            UserRepository.insertUser(nama, nomor, password, deskripsi, jasa, lokasi, "avatar.png", "0", dateFormat.format(date), "backgroundimage.png", "freelancer");
            startActivity(new Intent(this, LoginPage.class));
            finishAffinity();
        }

    }
}