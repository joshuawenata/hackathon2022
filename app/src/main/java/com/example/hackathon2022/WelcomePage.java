package com.example.hackathon2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class WelcomePage extends AppCompatActivity {

    ImageButton daftar, masuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        daftar = findViewById(R.id.activitywelcome_btnDaftar);
        masuk = findViewById(R.id.activitywelcome_btnMasuk);
    }

    public void intoRegisterPage(View view){
        Intent i = new Intent(this, PickRoles.class);
        startActivity(i);
    }
    public void intoLoginPage(View view){
        Intent i = new Intent(this, LoginPage.class);
        startActivity(i);
    }

}