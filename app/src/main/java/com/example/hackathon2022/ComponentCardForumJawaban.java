package com.example.hackathon2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ComponentCardForumJawaban extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component_card_forum_jawaban);
    }

    public void intoDetailsForum(View view){
        startActivity(new Intent(ComponentCardForumJawaban.this, ForumCardPage.class));
        finish();
    }
}