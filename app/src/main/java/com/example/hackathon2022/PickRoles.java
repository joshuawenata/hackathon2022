package com.example.hackathon2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class PickRoles extends AppCompatActivity implements View.OnClickListener {

    int checklist = 0;
    ConstraintLayout umkm, freelancer;
    ImageButton umkmic, freelanceric, lanjut;
    ImageView umkmcheck, freelancercheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_roles);

        umkm = findViewById(R.id.activityole_umkm);
        umkmic = findViewById(R.id.activityole_umkmic);
        umkmcheck = findViewById(R.id.role_umkm_check);

        freelancer = findViewById(R.id.activityrole_freelancer);
        freelanceric = findViewById(R.id.activityrole_freelanceric);
        freelancercheck = findViewById(R.id.role_freelancer_check);

        lanjut = findViewById(R.id.activityrole_lanjut);

        umkm.setOnClickListener(this);
        umkmic.setOnClickListener(this);
        freelancer.setOnClickListener(this);
        freelanceric.setOnClickListener(this);
        lanjut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.equals(umkm)||v.equals(umkmic)){
            checklist = 1;
            umkmcheck.setVisibility(View.VISIBLE);
            freelancercheck.setVisibility(View.INVISIBLE);
        }else if(v.equals(freelancer)||v.equals(freelanceric)){
            checklist = 2;
            umkmcheck.setVisibility(View.INVISIBLE);
            freelancercheck.setVisibility(View.VISIBLE);
        }

        if(v.equals(lanjut)){
            if(checklist==0){
                Toast.makeText(this, "Please pick a role!", Toast.LENGTH_SHORT).show();
            }else if(checklist==1){
                Intent i = new Intent(PickRoles.this, RegisterPage.class);
                startActivity(i);
                finish();
            }else if(checklist==2){
                Intent i = new Intent(PickRoles.this, RegisterFreelancerPage.class);
                startActivity(i);
                finish();
            }
        }
    }
}