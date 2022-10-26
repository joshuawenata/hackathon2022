package com.example.hackathon2022;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JasaCardPage extends AppCompatActivity {

    TextView txtnama, txtdeskripsi, txtLokasi, txtdate, txtStar;
    String nama, deskripsi, lokasi, date, star, jasakey, nomor;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ImageButton starBtn;
    ImageView whatsappBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jasa_card_page);

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
        txtnama = findViewById(R.id.activityjasacardpage_nama);
        txtLokasi = findViewById(R.id.activityjasacardpage_lokasi);
        txtdeskripsi = findViewById(R.id.activityjasacardpage_desc_content);
        txtdate = findViewById(R.id.activityjasacardpage_date);
        txtStar = findViewById(R.id.activityjasacardpage_starCount);
        whatsappBtn = findViewById(R.id.wa_btn);

        Intent intent = getIntent();
        nama = intent.getStringExtra("nama");
        lokasi = intent.getStringExtra("lokasi");
        deskripsi = intent.getStringExtra("deskripsi");
        date = intent.getStringExtra("date");
        star = intent.getStringExtra("star");
        jasakey = intent.getStringExtra("key");
        nomor = intent.getStringExtra("nomor");

        Log.i("TAG", nomor);

        txtnama.setText(nama);
        txtLokasi.setText(lokasi);
        txtdeskripsi.setText(deskripsi);
        txtdate.setText(date);
        txtStar.setText(star);
    }

    public void backtoHome(View view) {
        startActivity(new Intent(this, JasaPage.class));
        finish();
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void AddStar(View view) {
        starBtn = findViewById(R.id.activityjasacardpage_starButton);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("lowongan");
        databaseReference.child(jasakey+"/star").setValue(String.valueOf(Integer.parseInt(star)+1));
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