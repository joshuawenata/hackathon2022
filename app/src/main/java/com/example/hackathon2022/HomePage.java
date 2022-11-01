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
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.hackathon2022.Object.ObjectForum;
import com.example.hackathon2022.adapter.FrontForumAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity implements View.OnClickListener{
    Context context = this;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText searchbar;
    ImageView starsBtn;
    NestedScrollView myScrollView;
    ImageButton selfBtt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_page);

        initComponents();
    }

    protected void initComponents() {
        RecyclerView recyclerView = findViewById(R.id.activityhome_recyclerview);
        searchbar = findViewById(R.id.activityhome_search_bar);
        starsBtn = findViewById(R.id.componentcardforum_starbtn);
        myScrollView = findViewById(R.id.myScrollView);
        selfBtt = findViewById(R.id.selfBtt);

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
                        Intent i = new Intent(HomePage.this, ForumCardPage.class);
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

    public void intoHome(View view) {
        selfBtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myScrollView.smoothScrollTo(0,0);
            }
        });
    }

    public void intoSupplier(View view) {
        startActivity(new Intent(this, SupplierPage.class));
        finish();
    }

    public void intoLowongan(View view) {
        startActivity(new Intent(this, JasaPage.class));
        finish();
    }

    public void intoAdd(View view) {
        startActivity(new Intent(this, ForumCategory.class));
    }

    public void intoProfile(View view) {
        startActivity(new Intent(this, Profile.class));
        finish();
    }

    public void intoModal(View view) {
        startActivity(new Intent(this, HomeModalusaha.class));
    }

    @Override
    public void onClick(View v) {

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
                        Intent i = new Intent(HomePage.this, ForumCardPage.class);
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

    public void intoPerizinan(View view) {
        startActivity(new Intent(this, HomePerizinan.class));
    }

    public void intoPemasaran(View view) {
        startActivity(new Intent(this, HomePemasaran.class));
    }

    public void intoSocialMedia(View view) {
        startActivity(new Intent(this, HomeSocialMedia.class));
    }

    public void intoEcommerce(View view) {
        startActivity(new Intent(this, HomeEcommerce.class));
    }

    public void intoTipsAndTrick(View view) {
        startActivity(new Intent(this, HomeTips.class));
    }
}