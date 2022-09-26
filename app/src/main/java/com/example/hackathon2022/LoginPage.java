package com.example.hackathon2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hackathon2022.data.UserRepository;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    private EditText txtNomor, txtPassword;
    private Button btnMasuk;
    private TextView txtRegistrasi;

    private void initComponents(){
        txtNomor = findViewById(R.id.inputnomor);
        txtPassword = findViewById(R.id.inputpassword);
        btnMasuk = findViewById(R.id.btnMasuk);
        txtRegistrasi = findViewById(R.id.txtRegistrasi);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        initComponents();
        btnMasuk.setOnClickListener(this);
        txtRegistrasi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(txtRegistrasi)){
            Intent i = new Intent(LoginPage.this, RegisterPage.class);
            startActivity(i);
        }
        else if(view.equals(btnMasuk)){
            String nomor, password;

            //get data
            nomor = txtNomor.getText().toString();
            password = txtPassword.getText().toString();

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

            //autentikasi user
            if(flag){
                //TO DO: auth user sign in
                UserRepository.auth(nomor, password);
            }
        }
    }
}