package com.example.hackathon2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SupplierCardPage extends AppCompatActivity {

    TextView txtjudul, txtcategory, txtdescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_card_page);

        initComponents();
    }

    private void initComponents() {
        txtjudul = findViewById(R.id.activitysuppliercardpage_name);
        txtcategory = findViewById(R.id.activitysuppliercardpage_category);
        txtdescription = findViewById(R.id.activitysuppliercardpage_description);

        String judul, category, description, nomor;
        Intent intent = getIntent();
        judul = intent.getStringExtra("key");
        category = intent.getStringExtra("category");
        description = intent.getStringExtra("description");
        nomor = intent.getStringExtra("nomor");

        txtjudul.setText(judul);
        txtcategory.setText(category);
        txtdescription.setText(description);
    }

    public void backtoHome(View view) {
        startActivity(new Intent(this, SupplierPage.class));
        finish();
    }
}