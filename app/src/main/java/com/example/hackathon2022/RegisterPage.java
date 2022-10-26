package com.example.hackathon2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hackathon2022.data.UserRepository;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class RegisterPage extends AppCompatActivity implements View.OnClickListener{

    private EditText txtNomor, txtPassword, txtConfirmPassword;
    private ImageButton btnDaftar;
    private TextView txtMasuk;

    private void initComponents() {
        txtNomor = findViewById(R.id.activityregister_inputnomor);
        txtPassword = findViewById(R.id.activityregister_inputpassword);
        txtConfirmPassword = findViewById(R.id.activityregister_inputconfirmpassword);
        btnDaftar = findViewById(R.id.activityregister_btnDaftar);
        txtMasuk = findViewById(R.id.activityregister_txtMasuk);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        initComponents();
        txtMasuk.setOnClickListener(this);
        btnDaftar.setOnClickListener(this);
    }

    private boolean checkConfirmPassword(String password, String confirmPassword){
        if(!password.equals(confirmPassword)){
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if(view.equals(txtMasuk)){
            Intent i = new Intent(RegisterPage.this, LoginPage.class);
            startActivity(i);
        }
        else if(view.equals(btnDaftar)){
            String nomor, password, confirmPassword;

            //get data
            nomor = txtNomor.getText().toString();
            password = txtPassword.getText().toString();
            confirmPassword = txtConfirmPassword.getText().toString();

            //validate data
            boolean flag = true;
            if(nomor.isEmpty()){
                txtNomor.setError("Silahkan masukan nomor handphone");
                flag = false;
            }
            else if(password.isEmpty()){
                txtPassword.setError("Silahkan masukan kata sandi");
                flag = false;
            }
            else if(confirmPassword.isEmpty()){
                txtConfirmPassword.setError("Silahkan masukan konfirmasi kata sandi");
                flag = false;
            }
            else if(checkConfirmPassword(password, confirmPassword)){
                txtConfirmPassword.setError("Konfirmasi kata sandi harus sesuai dengan kata sandi");
                flag = false;
            }
            else{
                flag = true;
            }

            //buat user baru
            if(flag){
                Intent i = new Intent(this, RegisterCategoryActivity.class);
                i.putExtra("nomor", nomor);
                i.putExtra("password", password);
                startActivity(i);
            }
        }
    }
}