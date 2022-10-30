package com.example.hackathon2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.hackathon2022.Object.ObjectForum;
import com.example.hackathon2022.Object.ObjectLowongan;
import com.example.hackathon2022.Object.ObjectUser;
import com.example.hackathon2022.adapter.JasaAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class JasaPage extends AppCompatActivity {
    Context context = this;

    EditText searchbar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jasa_page);

        initComponents();
    }

    protected void initComponents() {
        RecyclerView recyclerView = findViewById(R.id.activitylowongan_recycleviewlowongan);
        ArrayList <ObjectLowongan> newList = new ArrayList<>();
        searchbar = findViewById(R.id.activityjasa_search_bar);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if(postSnapshot.getValue(ObjectLowongan.class).getRole().equals("freelancer")){
                        newList.add(postSnapshot.getValue(ObjectLowongan.class));
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
                JasaAdapter myAdapter = new JasaAdapter(context, newList);
                myAdapter.setOnItemClickListener(new JasaAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String key, String nama, String lokasi, String deskripsi, String date, String star, String nomor) {
                        Intent i = new Intent(JasaPage.this, JasaCardPage.class);
                        i.putExtra("nama",nama);
                        i.putExtra("lokasi",lokasi);
                        i.putExtra("deskripsi",deskripsi);
                        i.putExtra("date",date);
                        i.putExtra("star",star);
                        i.putExtra("key",key);
                        i.putExtra("nomor",nomor);
                        startActivity(i);
                    }
                });
                recyclerView.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError firebaseError) {
            }
        });

    }

    public void intoHome(View view) {
        startActivity(new Intent(this, HomePage.class));
        finish();
    }

    public void intoSupplier(View view) {
        startActivity(new Intent(this, SupplierPage.class));
        finish();
    }

    public void intoAdd(View view) {
        startActivity(new Intent(this, addlowongan.class));
        finish();
    }

    public void intoLamar(View view) {
        startActivity(new Intent(this, LamarPekerjaan.class));
        finish();
    }

    public void LihatSemua(View view) {
        startActivity(new Intent(this, AllLowongan.class));
        finish();
    }

    public void intoProfile(View view) {
        startActivity(new Intent(this, Profile.class));
        finish();
    }

    public void SearchingJasa(View view) {
        RecyclerView recyclerView = findViewById(R.id.activitylowongan_recycleviewlowongan);
        ArrayList <ObjectLowongan> newList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if(searchbar.getText().toString().equals("")){
                        newList.add(postSnapshot.getValue(ObjectLowongan.class));
                    }else{
                        if(postSnapshot.getValue(ObjectLowongan.class).getNama().contains(searchbar.getText().toString())
                        ||postSnapshot.getValue(ObjectLowongan.class).getLokasi().contains(searchbar.getText().toString())
                        ||postSnapshot.getValue(ObjectLowongan.class).getDeskripsi().contains(searchbar.getText().toString())){
                            newList.add(postSnapshot.getValue(ObjectLowongan.class));
                        }
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
                JasaAdapter myAdapter = new JasaAdapter(context, newList);
                myAdapter.setOnItemClickListener(new JasaAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String key, String nama, String lokasi, String deskripsi, String date, String star, String nomor) {
                        Intent i = new Intent(JasaPage.this, JasaCardPage.class);
                        i.putExtra("nama",nama);
                        i.putExtra("lokasi",lokasi);
                        i.putExtra("deskripsi",deskripsi);
                        i.putExtra("date",date);
                        i.putExtra("star",star);
                        i.putExtra("key",key);
                        i.putExtra("nomor",nomor);
                        startActivity(i);
                    }
                });
                recyclerView.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError firebaseError) {
            }
        });
    }
}