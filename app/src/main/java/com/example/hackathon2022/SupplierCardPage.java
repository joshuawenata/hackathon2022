package com.example.hackathon2022;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SupplierCardPage extends AppCompatActivity {

    TextView txtjudul, txtcategory, txtdescription, txtStar;
    String judul, category, description, nomor, star, supplierkey;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ImageButton starBtn;

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
        txtStar = findViewById(R.id.activitysuppliercardpage_starCount);
        starBtn = findViewById(R.id.activitysuppliercardpage_starButton);

        Intent intent = getIntent();
        judul = intent.getStringExtra("judul");
        category = intent.getStringExtra("category");
        description = intent.getStringExtra("description");
        nomor = intent.getStringExtra("nomor");
        star = intent.getStringExtra("star");
        supplierkey = intent.getStringExtra("key");

        txtjudul.setText(judul);
        txtcategory.setText(category);
        txtdescription.setText(description);
        txtStar.setText(star);
    }

    public void backtoHome(View view) {
        startActivity(new Intent(this, SupplierPage.class));
        finish();
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void AddStar(View view) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");
        databaseReference.child(supplierkey+"/star").setValue(String.valueOf(Integer.parseInt(star)+1));
        int tempint = Integer.parseInt(star);
        tempint += 1;
        float tempo;
        String tempstr = "";
        if(tempint>999){
            tempo=(float)tempint/1000;
            tempstr=String.valueOf(String.format("%.1f",tempo))+" ribu";
        }
        txtStar.setText(tempstr);
        starBtn.setImageResource(R.drawable.ic_baseline_star_24);
    }
}