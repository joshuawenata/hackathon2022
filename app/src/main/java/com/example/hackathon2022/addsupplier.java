package com.example.hackathon2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hackathon2022.R;
import com.example.hackathon2022.data.SupplierRepository;
import com.example.hackathon2022.data.UserRepository;
import com.example.hackathon2022.data.models.Supplier;

public class addsupplier extends AppCompatActivity {

    private EditText txtNama, txtLokasi, txtDeskripsi;
    private Button btnAdd;

    private void initComponents() {
        txtNama = findViewById(R.id.inputnamasupplier);
        txtLokasi = findViewById(R.id.inputLokasi);
        txtDeskripsi = findViewById(R.id.inputDeskripsi);
        btnAdd = findViewById(R.id.addSupplier);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsupplier);

        initComponents();
    }

    public void AddSupplier(View view) {
        String nama, lokasi, deskripsi;

        //get data
        nama = txtNama.getText().toString();
        lokasi = txtLokasi.getText().toString();
        deskripsi = txtDeskripsi.getText().toString();

        boolean flag = true;
        if(nama.isEmpty()){
            txtNama.setError("Silahkan masukan nama supplier");
            flag = false;
        }else if(lokasi.isEmpty()){
            txtLokasi.setError("Silahkan masukan lokasi supplier");
            flag = false;
        }else if(deskripsi.isEmpty()){
            txtDeskripsi.setError("Silahkan masukan deskripsi supplier");
            flag = false;
        }

        if(flag){
            SupplierRepository.insertSupplier(nama, lokasi, deskripsi);
            startActivity(new Intent(this, SupplierPage.class));
        }

    }
}