package com.example.hackathon2022;

import static com.example.hackathon2022.data.UserRepository.LOGGED_IN_USER;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hackathon2022.Object.ObjectForum;
import com.example.hackathon2022.adapter.FrontForumAdapter;
import com.example.hackathon2022.data.ReplyForumRepository;
import com.example.hackathon2022.data.models.ReplyForum;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ForumCardPage extends AppCompatActivity {

    TextView txtjudul, txtusernamekecil, txtusername, txtkategori, txtpertanyaan, replyusername;
    EditText replyanswer;
    String forumkey, judul, username, kategori, pertanyaan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_card_page);

        initComponents();
    }

    private void initComponents() {

        replyanswer = findViewById(R.id.activityforumcardpage_replycontent);
        txtjudul = findViewById(R.id.activityforumcardpage_judul);
        txtusernamekecil = findViewById(R.id.activityforumcardpage_username);
        txtkategori = findViewById(R.id.activityforumcardpage_kategori);
        txtpertanyaan = findViewById(R.id.activityforumcardpage_pertanyaan);

        Intent intent = getIntent();
        forumkey = intent.getStringExtra("key");
        judul = intent.getStringExtra("judul");
        username = intent.getStringExtra("username");
        kategori = intent.getStringExtra("kategori");
        pertanyaan = intent.getStringExtra("pertanyaan");

        txtjudul.setText(judul);
        txtusernamekecil.setText(username);
        txtkategori.setText(kategori);
        txtpertanyaan.setText(pertanyaan);
    }

    public void pushtoDB(View view) {
        ReplyForumRepository.insertReplyForum(forumkey,LOGGED_IN_USER.getUserName(),replyanswer.getText().toString());
    }
}