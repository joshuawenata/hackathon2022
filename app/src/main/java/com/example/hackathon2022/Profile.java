package com.example.hackathon2022;

import static com.example.hackathon2022.data.UserRepository.LOGGED_IN_USER;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    private EditText name;
    private EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initComponents();
        name.setText(LOGGED_IN_USER.getUserName());
        phone.setText(LOGGED_IN_USER.getUserNomor());
    }

    @SuppressLint("WrongViewCast")
    private void initComponents() {
        name = findViewById(R.id.activityprofile_name);
        phone = findViewById(R.id.activityprofile_phone);
    }

    public void intoSupplier(View view) {
        startActivity(new Intent(this, SupplierPage.class));
        finish();
    }

    public void intoLowongan(View view) {
        startActivity(new Intent(this, LowonganPekerjaan.class));
        finish();
    }

    public void intoHome(View view) {
        startActivity(new Intent(this, HomePage.class));
        finish();
    }

    public void Logout(View view) {
        startActivity(new Intent(this, WelcomePage.class));
        finish();
    }

    public void editProfile(View view) {
        LOGGED_IN_USER.setUserName(name.getText().toString());
        LOGGED_IN_USER.setUserNomor(phone.getText().toString());
    }

}