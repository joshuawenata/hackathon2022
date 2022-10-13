package com.example.hackathon2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hackathon2022.Object.ObjectLowongan;
import com.example.hackathon2022.adapter.LowonganAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LowonganPekerjaan extends AppCompatActivity {
    ArrayList <ObjectLowongan> newList = new ArrayList<>();
    Context context = this;

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

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("lowongan");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    newList.add(postSnapshot.getValue(ObjectLowongan.class));
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
                LowonganAdapter myAdapter = new LowonganAdapter(context, newList);
                myAdapter.setOnItemClickListener(new LowonganAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick() {
                        startActivity(new Intent(LowonganPekerjaan.this, JasaCardPage.class));
                        finish();
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

}