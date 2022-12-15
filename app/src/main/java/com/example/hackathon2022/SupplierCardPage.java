package com.example.hackathon2022;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SupplierCardPage extends AppCompatActivity {

    TextView txtjudul, txtcategory, txtdescription, txtStar, txtDate;
    String judul, category, description, nomor, star, supplierkey, date;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ImageButton starBtn;
//    ImageView whatsappBtn;
    LinearLayout whatsappBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_card_page);

        initComponents();

        whatsappBtn.setOnClickListener(view -> {
            Intent kirimWA = new Intent(Intent.ACTION_SEND);
            kirimWA.setType("text/plain");
            kirimWA.putExtra(Intent.EXTRA_TEXT, "Halo!");
            kirimWA.putExtra("jid", nomor + "@s.whatsapp.net");
            kirimWA.setPackage("com.whatsapp");

            startActivity(kirimWA);
        });
    }

    private void initComponents() {
        txtjudul = findViewById(R.id.activitysuppliercardpage_name);
        txtcategory = findViewById(R.id.activitysuppliercardpage_category);
        txtdescription = findViewById(R.id.activitysuppliercardpage_description);
        txtStar = findViewById(R.id.activitysuppliercardpage_starCount);
        starBtn = findViewById(R.id.activitysuppliercardpage_starButton);
        txtDate = findViewById(R.id.activitysuppliercardpage_date);
        whatsappBtn = findViewById(R.id.whatsapp_btn);
        whatsappBtn.setClickable(true);

        Intent intent = getIntent();
        judul = intent.getStringExtra("judul");
        category = intent.getStringExtra("category");
        description = intent.getStringExtra("description");
        nomor = intent.getStringExtra("nomor");
        star = intent.getStringExtra("star");
        supplierkey = intent.getStringExtra("key");
        date = intent.getStringExtra("date");

        txtjudul.setText(judul);
        txtcategory.setText(category);
        txtdescription.setText(description);
        txtDate.setText(date);
        if(Integer.valueOf(star)>999){
            float temp = (float)Float.valueOf(star)/1000;
            txtStar.setText(String.valueOf(String.format("%.1f",temp))+" ribu");
        }else{
            txtStar.setText(star);
        }
    }

    public void backtoHome(View view) {
        startActivity(new Intent(this, SupplierPage.class));
        finishAffinity();
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
        }else{
            tempstr = String.valueOf(tempint);
        }
        txtStar.setText(tempstr);
        starBtn.setImageResource(R.drawable.ic_baseline_star_24);
        Toast.makeText(this, "star berhasil ditambahkan", Toast.LENGTH_SHORT).show();
    }
}