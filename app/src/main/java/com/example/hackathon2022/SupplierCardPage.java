package com.example.hackathon2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SupplierCardPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_card_page);
    }

    public void backtoHome(View view) {
        startActivity(new Intent(this, SupplierPage.class));
        finish();
    }
}