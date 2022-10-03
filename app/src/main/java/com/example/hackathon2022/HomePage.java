package com.example.hackathon2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hackathon2022.Object.ObjectForum;
import com.example.hackathon2022.adapter.ForumAdapterTop;
import com.example.hackathon2022.adapter.ForumAdapterNear;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    ArrayList<ObjectForum> newList = new ArrayList<>();
    Context context = this;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        initComponents();
    }

    protected void initComponents() {
        RecyclerView recyclerView = findViewById(R.id.activityhome_recycleviewtop);
        RecyclerView recyclerViewterdekat = findViewById(R.id.activityhome_recycleviewnear);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("forum");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    newList.add(postSnapshot.getValue(ObjectForum.class));
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
                ForumAdapterTop TopAdapter = new ForumAdapterTop(context, newList);
                recyclerView.setAdapter(TopAdapter);

                TopAdapter.notifyDataSetChanged();

                recyclerViewterdekat.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
                ForumAdapterNear NearAdapter = new ForumAdapterNear(context, newList);
                recyclerViewterdekat.setAdapter(NearAdapter);

                NearAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError firebaseError) {
            }
        });


    }

    public void intoSupplier(View view) {
        startActivity(new Intent(this, SupplierPage.class));
    }

    public void intoLowongan(View view) {
        startActivity(new Intent(this, LowonganPekerjaan.class));
    }

    public void intoAdd(View view) {
        startActivity(new Intent(this, addforum.class));
    }

}