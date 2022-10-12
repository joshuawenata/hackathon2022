package com.example.hackathon2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class ComponentCardJasa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component_card_jasa);
    }

    public void intoDetailsJasa(View view) {
        startActivity(new Intent(ComponentCardJasa.this, JasaCardPage.class));
        finish();
    }

}