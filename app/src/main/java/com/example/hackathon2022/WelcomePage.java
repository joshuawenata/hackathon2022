package com.example.hackathon2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

    }

    public void intoRegisterPage(View view){
        startActivity(new Intent(this, PickRoles.class));
        finish();
    }
    public void intoLoginPage(View view){
        startActivity(new Intent(this, LoginPage.class));
        finish();
    }

}