package com.example.hackathon2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.hackathon2022.Object.ObjectForum;
import com.example.hackathon2022.adapter.FrontForumAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ForumSearchPage extends AppCompatActivity {
    Context context = this;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText searchbar;
    ImageView starsBtn;
    NestedScrollView myScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_search_page);

        initComponents();
    }

    protected void initComponents() {
        RecyclerView recyclerView = findViewById(R.id.activityhome_recyclerview);
        searchbar = findViewById(R.id.activityhome_search_bar);
        starsBtn = findViewById(R.id.componentcardforum_starbtn);
        myScrollView = findViewById(R.id.myScrollView);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("forum");

        ArrayList<ObjectForum> newList = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    newList.add(postSnapshot.getValue(ObjectForum.class));
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
                FrontForumAdapter TopAdapter = new FrontForumAdapter(context, newList);
                TopAdapter.setOnItemClickListener(new FrontForumAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(View v, String key, String username, String judul, String kategori, String pertanyaan, String date, String star, String path) {
                        Intent i = new Intent(ForumSearchPage.this, ForumCardPage.class);
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
                recyclerView.setAdapter(TopAdapter);
                TopAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError firebaseError) {
            }
        });
    }

    public void SearchingForum(View view) {
        ArrayList<ObjectForum> newList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.activityhome_recyclerview);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if(searchbar.getText().toString().equals("")){
                        newList.add(postSnapshot.getValue(ObjectForum.class));
                    }else{
                        if(postSnapshot.getValue(ObjectForum.class).getJudul().contains(searchbar.getText().toString())
                                ||postSnapshot.getValue(ObjectForum.class).getKategori().contains(searchbar.getText().toString())
                                ||postSnapshot.getValue(ObjectForum.class).getUsername().contains(searchbar.getText().toString())
                                ||postSnapshot.getValue(ObjectForum.class).getPertanyaan().contains(searchbar.getText().toString())
                        ){
                            newList.add(postSnapshot.getValue(ObjectForum.class));
                        }
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
                FrontForumAdapter TopAdapter = new FrontForumAdapter(context, newList);
                TopAdapter.setOnItemClickListener(new FrontForumAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(View v, String key, String username, String judul, String kategori, String pertanyaan, String date, String star, String path) {
                        Intent i = new Intent(ForumSearchPage.this, ForumCardPage.class);
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
                recyclerView.setAdapter(TopAdapter);
                TopAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError firebaseError) {
            }
        });
    }

    public void back(View view) {
        finish();
    }
}