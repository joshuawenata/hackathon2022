package com.example.hackathon2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

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
            else if(nomor.charAt(0)!='6'&&nomor.charAt(1)!='2'){
                txtNomor.setError("Silahkan masukan nomor dengan awalan 62");
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

            if(flag){
                Intent i = new Intent(this, RegisterCategory.class);
                i.putExtra("nomor", nomor);
                i.putExtra("password", password);
                startActivity(i);
            }
        }
    }
}