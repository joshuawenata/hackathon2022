package com.example.hackathon2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.hackathon2022.Object.ObjectForum;
import com.example.hackathon2022.Object.ObjectUser;
import com.example.hackathon2022.adapter.SupplierAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SupplierPage extends AppCompatActivity {
    Context context = this;

    EditText searchbar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    NestedScrollView myScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_page);

        initComponents();
    }

    protected void initComponents() {
        RecyclerView recyclerView = findViewById(R.id.activitysupplier_recycleviewsupplier);
        ArrayList<ObjectUser> newList = new ArrayList<>();
        searchbar = findViewById(R.id.activitysupplier_search_bar);
        myScrollView = findViewById(R.id.myScrollView);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if(postSnapshot.getValue(ObjectUser.class).getRole().equals("users")){
                        newList.add(postSnapshot.getValue(ObjectUser.class));
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
                SupplierAdapter myAdapter = new SupplierAdapter(context, newList);
                myAdapter.setOnItemClickListener(new SupplierAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String key, String judul, String category, String description, String nomor, String star, String date, String backgroundimagepath) {
                        Intent i = new Intent(SupplierPage.this, SupplierCardPage.class);
                        i.putExtra("key", key);
                        i.putExtra("judul",judul);
                        i.putExtra("category",category);
                        i.putExtra("description",description);
                        i.putExtra("nomor", nomor);
                        i.putExtra("star", star);
                        i.putExtra("date", date);
                        i.putExtra("backgroundimagepath", backgroundimagepath);
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
        overridePendingTransition(R.transition.slide_enter, R.transition.slide_exit);
        finish();
    }

    public void intoSupplier(View view) {
        myScrollView.smoothScrollTo(0,0);
    }

    public void intoJasa(View view) {
        startActivity(new Intent(this, JasaPage.class));
        overridePendingTransition(R.transition.slide_in, R.transition.slide_out);
        finish();
    }

    public void intoProfile(View view) {
        startActivity(new Intent(this, Profile.class));
        overridePendingTransition(R.transition.slide_in, R.transition.slide_out);
        finish();
    }








    public void intoFnB(View view) {
        startActivity(new Intent(this, SupplierFnb.class));
        finish();
    }

    public void intoFabrikasi(View view) {
        startActivity(new Intent(this, SupplierFabrikasi.class));
        finish();
    }

    public void intoElektronik(View view) {
        startActivity(new Intent(this, SupplierElektronik.class));
        finish();
    }

    public void intoPangan(View view) {
        startActivity(new Intent(this, SupplierPangan.class));
        finish();
    }

    public void intoTransportasi(View view) {
        startActivity(new Intent(this, SupplierTransportasi.class));
        finish();
    }

    public void intoPengolahan(View view) {
        startActivity(new Intent(this, SupplierPengolahan.class));
        finish();
    }

    public void SearchingSupplier(View view) {
        RecyclerView recyclerView = findViewById(R.id.activitysupplier_recycleviewsupplier);
        ArrayList<ObjectUser> newList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if(postSnapshot.getValue(ObjectUser.class).getRole().equals("users")){
                        if(searchbar.getText().toString().equals("")){
                            newList.add(postSnapshot.getValue(ObjectUser.class));
                        }else{
                            if(postSnapshot.getValue(ObjectUser.class).getNama().contains(searchbar.getText().toString())
                                    ||postSnapshot.getValue(ObjectUser.class).getLokasi().contains(searchbar.getText().toString())
                                    ||postSnapshot.getValue(ObjectUser.class).getKategori().contains(searchbar.getText().toString())
                                    ||postSnapshot.getValue(ObjectUser.class).getDeskripsi().contains(searchbar.getText().toString())){
                                newList.add(postSnapshot.getValue(ObjectUser.class));
                            }
                        }
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
                SupplierAdapter myAdapter = new SupplierAdapter(context, newList);
                myAdapter.setOnItemClickListener(new SupplierAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String key, String judul, String category, String description, String nomor, String star, String date, String backgroundimagepath) {
                        Intent i = new Intent(SupplierPage.this, SupplierCardPage.class);
                        i.putExtra("key", key);
                        i.putExtra("judul",judul);
                        i.putExtra("category",category);
                        i.putExtra("description",description);
                        i.putExtra("nomor", nomor);
                        i.putExtra("star", star);
                        i.putExtra("date", date);
                        i.putExtra("backgroundimagepath", backgroundimagepath);
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