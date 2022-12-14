package com.example.hackathon2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hackathon2022.Object.ObjectForum;
import com.example.hackathon2022.Object.ObjectUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RegisterPage extends AppCompatActivity implements View.OnClickListener{

    private EditText txtNomor, txtPassword, txtConfirmPassword;
    private ImageButton btnDaftar;
    private TextView txtMasuk;
    Context context = this;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        initComponents();
    }

    private void initComponents() {
        txtNomor = findViewById(R.id.activityregister_inputnomor);
        txtPassword = findViewById(R.id.activityregister_inputpassword);
        txtConfirmPassword = findViewById(R.id.activityregister_inputconfirmpassword);
        btnDaftar = findViewById(R.id.activityregister_btnDaftar);
        btnDaftar.setOnClickListener(this);
    }

    private boolean checkConfirmPassword(String password, String confirmPassword){
        if(!password.equals(confirmPassword)){
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        int sama = 0;
        ArrayList <ObjectUser> tempList = new ArrayList<>();

        if(view.equals(txtMasuk)){
            Intent i = new Intent(RegisterPage.this, LoginPage.class);
            startActivity(i);
        }
        else if(view.equals(btnDaftar)){
            String nomor, password, confirmPassword;

            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference("users");

            //get data
            nomor = txtNomor.getText().toString();
            password = txtPassword.getText().toString();
            confirmPassword = txtConfirmPassword.getText().toString();

            databaseReference.addValueEventListener(new ValueEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        if(postSnapshot.getValue(ObjectUser.class).getNomor().equals(nomor)){
                            tempList.add(postSnapshot.getValue(ObjectUser.class));
                        }
                    }

                    boolean flag = true;

                    if(nomor.isEmpty()){
                        txtNomor.setError("Silahkan masukan nomor handphone");
                        flag = false;
                    }
                    else if(tempList.size()>0){
                        txtNomor.setError("Nomor sudah terpakai");
                        flag = false;
                    }
                    else if(nomor.charAt(0)!='6'&&nomor.charAt(1)!='2'){
                        txtNomor.setError("Silahkan masukan nomor dengan awalan 62");
                        flag = false;
                    }
                    else if(password.isEmpty()){
                        txtPassword.setError("Silahkan masukan kata sandi");
                        flag = false;
                    }
                    else if(confirmPassword.isEmpty()){
                        txtConfirmPassword.setError("Silahkan masukan konfirmasi kata sandi");
                        flag = false;
                    }
                    else if(checkConfirmPassword(password, confirmPassword)){
                        txtConfirmPassword.setError("Konfirmasi kata sandi harus sesuai dengan kata sandi");
                        flag = false;
                    }
                    else{
                        flag = true;
                    }

                    if(flag){
                        Intent i = new Intent(context, RegisterCategory.class);
                        i.putExtra("nomor", nomor);
                        i.putExtra("password", password);
                        i.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                        startActivity(i);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error){
                }
            });

        }
    }

    public void back(View view) {
        finish();
    }
}