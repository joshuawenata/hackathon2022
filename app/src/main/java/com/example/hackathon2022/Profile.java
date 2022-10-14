package com.example.hackathon2022;

import static com.example.hackathon2022.data.UserRepository.LOGGED_IN_USER;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    private EditText name;
    private EditText phone;
    private ImageButton btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initComponents();
        name.setText(LOGGED_IN_USER.getUserName());
        phone.setText(LOGGED_IN_USER.getUserNomor());

        btnLogout.setOnClickListener(v -> {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Profile.this);

            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.commit();

            Intent i = new Intent(Profile.this, WelcomePage.class);
            startActivity(i);
            finish();
        });
    }

    @SuppressLint("WrongViewCast")
    private void initComponents() {
        name = findViewById(R.id.activityprofile_name);
        phone = findViewById(R.id.activityprofile_phone);
        btnLogout = findViewById(R.id.logout_btn);
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

    public void editProfile(View view) {
        LOGGED_IN_USER.setUserName(name.getText().toString());
        LOGGED_IN_USER.setUserNomor(phone.getText().toString());
    }

}