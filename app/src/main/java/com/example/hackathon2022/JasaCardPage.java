package com.example.hackathon2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hackathon2022.Object.ObjectGallery;
import com.example.hackathon2022.adapter.FreelancerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class JasaCardPage extends AppCompatActivity {

    TextView txtnama, txtdeskripsi, txtLokasi, txtdate, txtStar, txtjasa, txtPorto;
    String nama, deskripsi, lokasi, date, star, jasakey, nomor, jasa;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ImageButton starBtn;
    LinearLayout whatsappBtn;
    RecyclerView rv;
    DatabaseReference databaseReferences;
    StorageReference storageReference;
    Context context = this;
    ArrayList<ObjectGallery> newList = new ArrayList<>();

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
        txtjasa = findViewById(R.id.activityjasacardpage_jasa);
        txtPorto = findViewById(R.id.activityjasacardpage_portofoliotitle);
        whatsappBtn = findViewById(R.id.wa_btn);
        rv = findViewById(R.id.activityprofilefreelancer_recyclerviewfreelancer);

        Intent intent = getIntent();
        nama = intent.getStringExtra("nama");
        lokasi = intent.getStringExtra("lokasi");
        deskripsi = intent.getStringExtra("deskripsi");
        date = intent.getStringExtra("date");
        star = intent.getStringExtra("star");
        jasakey = intent.getStringExtra("key");
        jasa = intent.getStringExtra("jasa");
        nomor = intent.getStringExtra("nomor");

        Log.i("TAG", nomor);

        txtnama.setText(nama);
        txtLokasi.setText(lokasi);
        txtdeskripsi.setText(deskripsi);
        txtdate.setText(date);
        txtStar.setText(star);
        txtjasa.setText(jasa);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReferences = firebaseDatabase.getReference("gallery");
        storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://hackathon2022-85c99.appspot.com");

        databaseReferences.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if(postSnapshot.getValue((ObjectGallery.class)).getGalleryKey().equals(jasakey)){
                        newList.add(postSnapshot.getValue(ObjectGallery.class));
                    }
                }

                if(newList.size()==0){
                    txtPorto.setVisibility(View.GONE);
                    rv.setVisibility(View.GONE);
                }else{
                    rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
                    FreelancerAdapter TopAdapter = new FreelancerAdapter(context, newList);
                    rv.setAdapter(TopAdapter);
                    TopAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void backtoHome(View view) {
        startActivity(new Intent(this, JasaPage.class));
        finishAffinity();
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void AddStar(View view) {
        starBtn = findViewById(R.id.activityjasacardpage_starButton);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");
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