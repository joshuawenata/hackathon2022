package com.example.hackathon2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PickRoles extends AppCompatActivity implements View.OnClickListener {

    int checklist = 0;
    ConstraintLayout umkm, freelancer;
    ImageButton umkmic, freelanceric, lanjut, back;
    ImageView umkmcheck, freelancercheck;
    TextView masuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_roles);

        back = findViewById(R.id.back);

        umkm = findViewById(R.id.activityole_umkm);
        umkmic = findViewById(R.id.activityole_umkmic);
        umkmcheck = findViewById(R.id.role_umkm_check);

        freelancer = findViewById(R.id.activityrole_freelancer);
        freelanceric = findViewById(R.id.activityrole_freelanceric);
        freelancercheck = findViewById(R.id.role_freelancer_check);

        masuk = findViewById(R.id.activity_register_txt_masuk);

        lanjut = findViewById(R.id.activityrole_lanjut);

        umkm.setOnClickListener(this);
        umkmic.setOnClickListener(this);
        freelancer.setOnClickListener(this);
        freelanceric.setOnClickListener(this);
        lanjut.setOnClickListener(this);
        masuk.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(umkm)||v.equals(umkmic)){
            checklist = 1;
            umkm.setBackgroundResource(R.drawable.dimmed_description_column);
            freelancer.setBackgroundResource(R.drawable.description_column);
            umkmcheck.setVisibility(View.VISIBLE);
            freelancercheck.setVisibility(View.INVISIBLE);
        }else if(v.equals(freelancer)||v.equals(freelanceric)){
            checklist = 2;
            freelancer.setBackgroundResource(R.drawable.dimmed_description_column);
            umkm.setBackgroundResource(R.drawable.description_column);
            umkmcheck.setVisibility(View.INVISIBLE);
            freelancercheck.setVisibility(View.VISIBLE);
        }

        if(v.equals(lanjut)){
            if(checklist==0){
                Toast.makeText(this, "Mohon untuk memilih salah satu", Toast.LENGTH_SHORT).show();
            }else if(checklist==1){
                Intent i = new Intent(this, RegisterPage.class);
                startActivity(i);
            }else if(checklist==2){
                Intent i = new Intent(this, RegisterFreelancerPage.class);
                startActivity(i);
            }
        }

        if(v.equals(masuk)){
            Intent i = new Intent(PickRoles.this, LoginPage.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }

        if(v.equals(back)){
            finish();
        }
    }
}