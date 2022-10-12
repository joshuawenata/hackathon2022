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
import com.example.hackathon2022.adapter.AllForumAdapter;
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
        RecyclerView recyclerWeeklyPopularForum = findViewById(R.id.activityhome_recyclerweeklypopularforum);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("forum");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    newList.add(postSnapshot.getValue(ObjectForum.class));
                }

                recyclerWeeklyPopularForum.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
                ForumAdapterTop TopAdapter = new ForumAdapterTop(context, newList);
                TopAdapter.setOnItemClickListener(new AllForumAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick() {
                        startActivity(new Intent(HomePage.this, ForumCardPage.class));
                    }
                });
                recyclerWeeklyPopularForum.setAdapter(TopAdapter);

                TopAdapter.notifyDataSetChanged();
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

    public void intoPopuler(View view) {
        startActivity(new Intent(this, PopularForum.class));
    }

    public void intoTeratas(View view) {
        startActivity(new Intent(this, TopForum.class));
    }
    public void intoProfile(View view) {
        startActivity(new Intent(this, Profile.class));
    }

    public void intoModal(View view) {
        startActivity(new Intent(this, HomeModalusaha.class));
    }

}