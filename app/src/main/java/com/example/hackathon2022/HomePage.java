package com.example.hackathon2022;

import static com.example.hackathon2022.data.UserRepository.LOGGED_IN_USER;

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
import android.widget.EditText;

import com.example.hackathon2022.Object.ObjectForum;
import com.example.hackathon2022.adapter.FrontForumAdapter;
import com.example.hackathon2022.data.ReplyForumRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        RecyclerView recyclerWeeklyPopularForum = findViewById(R.id.activityhome_recyclerview);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("forum");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    newList.add(postSnapshot.getValue(ObjectForum.class));
                }

                recyclerWeeklyPopularForum.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
                FrontForumAdapter TopAdapter = new FrontForumAdapter(context, newList);
                TopAdapter.setOnItemClickListener(new FrontForumAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(String key, String username, String judul, String kategori, String pertanyaan, String date, String star) {
                        Intent i = new Intent(HomePage.this, ForumCardPage.class);
                        i.putExtra("key",key);
                        i.putExtra("username",username);
                        i.putExtra("judul",judul);
                        i.putExtra("kategori",kategori);
                        i.putExtra("pertanyaan",pertanyaan);
                        i.putExtra("date",date);
                        i.putExtra("star",star);
                        startActivity(i);
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
        finish();
    }

    public void intoLowongan(View view) {
        startActivity(new Intent(this, LowonganPekerjaan.class));
        finish();
    }

    public void intoAdd(View view) {
        startActivity(new Intent(this, addforum.class));
        finish();
    }

    public void intoProfile(View view) {
        startActivity(new Intent(this, Profile.class));
        finish();
    }

    public void intoModal(View view) {
        startActivity(new Intent(this, HomeModalusaha.class));
    }

}