package com.example.hackathon2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.hackathon2022.Object.ObjectForum;
import com.example.hackathon2022.adapter.AllForumAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class HomePerizinan extends AppCompatActivity {

    Context context = this;
    EditText searchbar;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_perizinan);

        initComponents();
    }

    protected void initComponents() {
        ArrayList<ObjectForum> newList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.activityhomeperizinan_recycleview);
        searchbar = findViewById(R.id.activityhomeperizinan_searchbar);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("forum");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if(Objects.requireNonNull(postSnapshot.getValue(ObjectForum.class)).getKategori().equals("Perizinan")) {
                        newList.add(postSnapshot.getValue(ObjectForum.class));
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
                AllForumAdapter myAdapter = new AllForumAdapter(context, newList);
                myAdapter.setOnItemClickListener(new AllForumAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, String key, String username, String judul, String kategori, String pertanyaan, String date, String star, String path) {
                        Intent i = new Intent(HomePerizinan.this, ForumCardPage.class);
                        i.putExtra("key",key);
                        i.putExtra("username",username);
                        i.putExtra("judul",judul);
                        i.putExtra("kategori",kategori);
                        i.putExtra("pertanyaan",pertanyaan);
                        i.putExtra("date",date);
                        i.putExtra("star",star);
                        i.putExtra("path",path);
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

    public void backtoHome(View view) {
        startActivity(new Intent(this, HomePage.class));
        finish();
    }

    public void SearchingPerizinan(View view) {
        ArrayList<ObjectForum> newList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.activityhomeperizinan_recycleview);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if(Objects.requireNonNull(postSnapshot.getValue(ObjectForum.class)).getKategori().equals("Perizinan")) {
                        if (postSnapshot.getValue(ObjectForum.class).getJudul().contains(searchbar.getText().toString()) ||
                                postSnapshot.getValue(ObjectForum.class).getUsername().contains(searchbar.getText().toString()) ||
                                postSnapshot.getValue(ObjectForum.class).getPertanyaan().contains(searchbar.getText().toString()) ||
                                postSnapshot.getValue(ObjectForum.class).getDate().contains(searchbar.getText().toString())
                        ){
                            newList.add(postSnapshot.getValue(ObjectForum.class));
                        }
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
                AllForumAdapter myAdapter = new AllForumAdapter(context, newList);
                myAdapter.setOnItemClickListener(new AllForumAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, String key, String username, String judul, String kategori, String pertanyaan, String date, String star, String path) {
                        Intent i = new Intent(HomePerizinan.this, ForumCardPage.class);
                        i.putExtra("key",key);
                        i.putExtra("username",username);
                        i.putExtra("judul",judul);
                        i.putExtra("kategori",kategori);
                        i.putExtra("pertanyaan",pertanyaan);
                        i.putExtra("date",date);
                        i.putExtra("star",star);
                        i.putExtra("path",path);
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

    public void SortingPerizinan(View view) {
        ArrayList<ObjectForum> newList = new ArrayList<>();
        ArrayList<ObjectForum> templist = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.activityhomeperizinan_recycleview);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if(Objects.requireNonNull(postSnapshot.getValue(ObjectForum.class)).getKategori().equals("Perizinan")) {
                        templist.add(postSnapshot.getValue(ObjectForum.class));
                    }
                }

                for(int i=0;i<templist.size();i++){
                    for(int j=i+1;j<templist.size();j++) {
                        if (templist.get(i).getStar() < templist.get(j).getStar()) {
                            Collections.swap(templist, i, j);
                        }
                    }
                }

                for(int i=0;i<templist.size();i++){
                    newList.add(templist.get(i));
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
                AllForumAdapter myAdapter = new AllForumAdapter(context, newList);
                myAdapter.setOnItemClickListener(new AllForumAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, String key, String username, String judul, String kategori, String pertanyaan, String date, String star, String path) {
                        Intent i = new Intent(HomePerizinan.this, ForumCardPage.class);
                        i.putExtra("key",key);
                        i.putExtra("username",username);
                        i.putExtra("judul",judul);
                        i.putExtra("kategori",kategori);
                        i.putExtra("pertanyaan",pertanyaan);
                        i.putExtra("date",date);
                        i.putExtra("star",star);
                        i.putExtra("path",path);
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