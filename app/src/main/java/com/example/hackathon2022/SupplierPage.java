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

import com.example.hackathon2022.Object.ObjectSupplier;
import com.example.hackathon2022.adapter.AllForumAdapter;
import com.example.hackathon2022.adapter.SupplierAdapter;
import com.example.hackathon2022.adapter.SupplierTerdekatAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SupplierPage extends AppCompatActivity {
    ArrayList<ObjectSupplier> newList = new ArrayList<>();
    Context context = this;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_page);

        initComponents();
    }

    protected void initComponents() {
        RecyclerView recyclerView = findViewById(R.id.activitysupplier_recycleviewsupplier);
        RecyclerView recyclerViewterdekat = findViewById(R.id.activitysupplier_recycleviewsupplierterdekat);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("suppliers");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    newList.add(postSnapshot.getValue(ObjectSupplier.class));
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
                SupplierAdapter myAdapter = new SupplierAdapter(context, newList);
                myAdapter.setOnItemClickListener(new AllForumAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick() {
                        startActivity(new Intent(SupplierPage.this, SupplierCardPage.class));
                    }
                });
                recyclerView.setAdapter(myAdapter);

                myAdapter.notifyDataSetChanged();

                recyclerViewterdekat.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
                SupplierTerdekatAdapter terdekatAdapter = new SupplierTerdekatAdapter(context, newList);
                terdekatAdapter.setOnItemClickListener(new AllForumAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick() {
                        startActivity(new Intent(SupplierPage.this, SupplierCardPage.class));
                    }
                });
                recyclerViewterdekat.setAdapter(terdekatAdapter);

                terdekatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError firebaseError) {
            }
        });


    }

    public void intoHome(View view) {
        startActivity(new Intent(this, HomePage.class));
    }

    public void intoLowongan(View view) { startActivity(new Intent(this, LowonganPekerjaan.class)); }

    public void intoAdd(View view) { startActivity(new Intent(this, addsupplier.class)); }

    public void TopSupplier(View view) { startActivity(new Intent(this, TopSupplier.class)); }

    public void NearSupplier(View view) { startActivity(new Intent(this, NearSupplier.class)); }

    public void intoProfile(View view) {
        startActivity(new Intent(this, Profile.class));
    }

    public void intoFnB(View view) {
        startActivity(new Intent(this, SupplierFnb.class));
    }
}