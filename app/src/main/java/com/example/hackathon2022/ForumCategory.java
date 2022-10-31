package com.example.hackathon2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class ForumCategory extends AppCompatActivity implements View.OnClickListener {

    int checklist = 0;
    ImageButton lanjut;
    String kategori = "";
    ConstraintLayout saranusaha, pemasaran, perizinan, sosmed, ecommerce, tipsandtrick;
    ImageButton saranusahaic, pemasaranic, perizinanic, sosmedic, ecommerceic, tipsandtrickic;
    ImageView saranusahacheck, pemasarancheck, perizinancheck, sosmedcheck, ecommercecheck, tipsandtrickcheck;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_category);

        lanjut = findViewById(R.id.activityforumcategory_lanjutbtn);

        saranusaha = findViewById(R.id.activityforumcategory_saranusaha);
        saranusahaic = findViewById(R.id.activityforumcategory_saranusahaic);
        saranusahacheck = findViewById(R.id.saranusaha_check);

        pemasaran = findViewById(R.id.activityforumcategory_pemasaran);
        pemasaranic = findViewById(R.id.activityforumcategory_pemasaranic);
        pemasarancheck = findViewById(R.id.pemasaran_check);

        perizinan = findViewById(R.id.activityforumcategory_perizinan);
        perizinanic = findViewById(R.id.activityforumcategory_perizinanic);
        perizinancheck = findViewById(R.id.perizinan_check);

        sosmed = findViewById(R.id.activityforumcategory_sosmed);
        sosmedic = findViewById(R.id.activityforumcategory_sosmedic);
        sosmedcheck = findViewById(R.id.socialmedia_check);

        ecommerce = findViewById(R.id.activityforumcategory_ecommerce);
        ecommerceic = findViewById(R.id.activityforumcategory_ecommerceic);
        ecommercecheck = findViewById(R.id.ecommerce_check);

        tipsandtrick = findViewById(R.id.activityforumcategory_tipsandtrick);
        tipsandtrickic = findViewById(R.id.activityforumcategory_tipsandtrickic);
        tipsandtrickcheck = findViewById(R.id.tipsandtrick_check);

        saranusaha.setOnClickListener(this);
        saranusahaic.setOnClickListener(this);

        pemasaran.setOnClickListener(this);
        pemasaranic.setOnClickListener(this);

        perizinan.setOnClickListener(this);
        perizinanic.setOnClickListener(this);

        sosmed.setOnClickListener(this);
        sosmedic.setOnClickListener(this);

        ecommerce.setOnClickListener(this);
        ecommerceic.setOnClickListener(this);

        tipsandtrick.setOnClickListener(this);
        tipsandtrickic.setOnClickListener(this);

        lanjut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(ForumCategory.this, addforum.class);
        if(v.equals(saranusaha)||v.equals(saranusahaic)){
            kategori = "Saran Usaha";
            saranusahacheck.setVisibility(View.VISIBLE);
            pemasarancheck.setVisibility(View.INVISIBLE);
            perizinancheck.setVisibility(View.INVISIBLE);
            sosmedcheck.setVisibility(View.INVISIBLE);
            ecommercecheck.setVisibility(View.INVISIBLE);
            tipsandtrickcheck.setVisibility(View.INVISIBLE);
            checklist=1;
        }else if(v.equals(pemasaran)||v.equals(pemasaranic)){
            kategori = "Pemasaran";
            saranusahacheck.setVisibility(View.INVISIBLE);
            pemasarancheck.setVisibility(View.VISIBLE);
            perizinancheck.setVisibility(View.INVISIBLE);
            sosmedcheck.setVisibility(View.INVISIBLE);
            ecommercecheck.setVisibility(View.INVISIBLE);
            tipsandtrickcheck.setVisibility(View.INVISIBLE);
            checklist=1;
        }else if(v.equals(perizinan)||v.equals(perizinanic)){
            kategori = "Perizinan";
            saranusahacheck.setVisibility(View.INVISIBLE);
            pemasarancheck.setVisibility(View.INVISIBLE);
            perizinancheck.setVisibility(View.VISIBLE);
            sosmedcheck.setVisibility(View.INVISIBLE);
            ecommercecheck.setVisibility(View.INVISIBLE);
            tipsandtrickcheck.setVisibility(View.INVISIBLE);
            checklist=1;
        }else if(v.equals(sosmed)||v.equals(sosmedic)){
            kategori = "Social Media";
            saranusahacheck.setVisibility(View.INVISIBLE);
            pemasarancheck.setVisibility(View.INVISIBLE);
            perizinancheck.setVisibility(View.INVISIBLE);
            sosmedcheck.setVisibility(View.VISIBLE);
            ecommercecheck.setVisibility(View.INVISIBLE);
            tipsandtrickcheck.setVisibility(View.INVISIBLE);
            checklist=1;
        }else if(v.equals(ecommerce)||v.equals(ecommerceic)){
            kategori = "E-Commerce";
            saranusahacheck.setVisibility(View.INVISIBLE);
            pemasarancheck.setVisibility(View.INVISIBLE);
            perizinancheck.setVisibility(View.INVISIBLE);
            sosmedcheck.setVisibility(View.INVISIBLE);
            ecommercecheck.setVisibility(View.VISIBLE);
            tipsandtrickcheck.setVisibility(View.INVISIBLE);
            checklist=1;
        }else if(v.equals(tipsandtrick)||v.equals(tipsandtrickic)){
            kategori = "Tips and Trick";
            saranusahacheck.setVisibility(View.INVISIBLE);
            pemasarancheck.setVisibility(View.INVISIBLE);
            perizinancheck.setVisibility(View.INVISIBLE);
            sosmedcheck.setVisibility(View.INVISIBLE);
            ecommercecheck.setVisibility(View.INVISIBLE);
            tipsandtrickcheck.setVisibility(View.VISIBLE);
            checklist=1;
        }
        else if(v.equals(lanjut)){
            if(checklist==0){
                Toast.makeText(this, "Please pick a category!", Toast.LENGTH_SHORT).show();
            }else{
                checklist=0;
                i.putExtra("kategori", kategori);
                startActivity(i);
            }
        }
    }
}