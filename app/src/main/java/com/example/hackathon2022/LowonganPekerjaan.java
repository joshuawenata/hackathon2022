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
import android.widget.TextView;

import com.example.hackathon2022.Object.ObjectLowongan;
import com.example.hackathon2022.adapter.AllForumAdapter;
import com.example.hackathon2022.adapter.LowonganAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LowonganPekerjaan extends AppCompatActivity {
    ArrayList <ObjectLowongan> newList = new ArrayList<>();
    Context context = this;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lowongan_pekerjaan);

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
                myAdapter.setOnItemClickListener(new AllForumAdapter.OnItemClickListener() {
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
    }

    public void intoSupplier(View view) {
        startActivity(new Intent(this, SupplierPage.class));
    }

    public void intoAdd(View view) {
        startActivity(new Intent(this, addlowongan.class));
    }

    public void intoLamar(View view) {
        startActivity(new Intent(this, LamarPekerjaan.class));
    }

    public void LihatSemua(View view) {
        startActivity(new Intent(this, AllLowongan.class));
    }

    public void intoProfile(View view) {
        startActivity(new Intent(this, Profile.class));
    }

}