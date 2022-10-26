package com.example.hackathon2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hackathon2022.data.UserRepository;
import com.example.hackathon2022.data.models.User;
import com.example.hackathon2022.interfaces.FinisihListener;

public class LoginPage extends AppCompatActivity implements View.OnClickListener, FinisihListener<User> {

    private EditText txtNomor, txtPassword;
    private ImageButton btnMasuk;
    private TextView txtRegistrasi;

    private void initComponents(){
        txtNomor = findViewById(R.id.activitylogin_inputnomor);
        txtPassword = findViewById(R.id.activitylogin_inputpassword);
        btnMasuk = findViewById(R.id.activitylogin_btnMasuk);
        txtRegistrasi = findViewById(R.id.activitylogin_txtRegistrasi);
    }

    private void checkIfLoggedIn(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginPage.this);
        if(preferences.contains("userKey")){
            UserRepository.find(preferences.getString("userKey", null), (data, msg)->{
                UserRepository.LOGGED_IN_USER = data;

                if(data != null){
                    Intent i = new Intent(LoginPage.this, HomePage.class);
                    startActivity(i);
                    finish();
                }
                else{
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear();
                    editor.commit();
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        checkIfLoggedIn();
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
            else if(nomor.charAt(0)!='6'&&nomor.charAt(1)!='2'){
                txtNomor.setError("Silahkan masukan nomor dengan awalan 62");
                flag = false;
            }
            else if(password.isEmpty()){
                txtPassword.setError("Silahkan masukan kata sandi");
                flag = false;
            }

            //autentikasi user
            if(flag){
                //TO DO: auth user sign in
                UserRepository.auth(nomor, password, this);
            }
        }
    }

    @Override
    public void onFinish(User data, String msg) {
        UserRepository.LOGGED_IN_USER = data;

        if(data == null){
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
        else{
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(LoginPage.this);
            SharedPreferences.Editor editor = prefs.edit();

            editor.putString("userKey", data.getUserKey());
            editor.putString("nomor", data.getUserNomor());
            editor.commit();

            Intent i = new Intent(LoginPage.this, HomePage.class);
            startActivity(i);
            finish();
        }
    }
}