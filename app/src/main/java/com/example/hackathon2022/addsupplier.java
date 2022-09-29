package com.example.hackathon2022;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hackathon2022.R;
import com.example.hackathon2022.data.SupplierRepository;
import com.example.hackathon2022.data.UserRepository;
import com.example.hackathon2022.data.models.Supplier;

public class addsupplier extends AppCompatActivity {

    private EditText txtNama, txtLokasi, txtDeskripsi;
    private Button btnAdd, btnUploadAddSupplier;
    private ImageView imageprofile;

    @SuppressLint("WrongViewCast")
    private void initComponents() {
        txtNama = findViewById(R.id.activityaddsupplier_inputnamasupplier);
        txtLokasi = findViewById(R.id.activityaddsupplier_inputLokasi);
        txtDeskripsi = findViewById(R.id.activityaddsupplier_inputDeskripsi);
        btnAdd = findViewById(R.id.activityaddsupplier_btnTambahSupplier);
        btnUploadAddSupplier = findViewById(R.id.activityaddsupplier_btnTambahDariPerangkat);
        imageprofile = findViewById(R.id.activityaddsupplier_imageprofile);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsupplier);

        initComponents();

        btnUploadAddSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent iGallery = new Intent(Intent.ACTION_PICK);
               iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
               startActivityForResult(iGallery,1000);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            imageprofile.setImageURI(data.getData());
        }
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
            SupplierRepository.insertSupplier(nama, lokasi, deskripsi, imageprofile.toString());
            startActivity(new Intent(this, SupplierPage.class));
        }

    }
}