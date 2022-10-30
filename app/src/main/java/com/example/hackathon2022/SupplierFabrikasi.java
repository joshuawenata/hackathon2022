package com.example.hackathon2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hackathon2022.Object.ObjectSupplier;
import com.example.hackathon2022.Object.ObjectUser;
import com.example.hackathon2022.adapter.AllForumAdapter;
import com.example.hackathon2022.adapter.AllSupplierAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class SupplierFabrikasi extends AppCompatActivity {
    ArrayList<ObjectUser> newList = new ArrayList<>();
    Context context = this;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_fnb);

        initComponents();
    }

    protected void initComponents() {
        RecyclerView recyclerView = findViewById(R.id.activitysupplierfnb_recycleview);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if(Objects.requireNonNull(postSnapshot.getValue(ObjectUser.class)).getKategori().equals("Pakaian / Fabrikasi")&&postSnapshot.getValue(ObjectUser.class).getRole().equals("users")){
                        newList.add(postSnapshot.getValue(ObjectUser.class));
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
                AllSupplierAdapter myAdapter = new AllSupplierAdapter(context, newList);
                myAdapter.setOnItemClickListener(new AllSupplierAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String key, String judul, String category, String description, String nomor, String date, String star, String backgroundimagepath) {
                        Intent i = new Intent(SupplierFabrikasi.this, SupplierCardPage.class);
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

    public void backtoSupplier(View view) { startActivity(new Intent(this, SupplierPage.class)); finish();}

}