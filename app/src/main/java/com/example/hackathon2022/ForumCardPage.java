package com.example.hackathon2022;

import static com.example.hackathon2022.data.UserRepository.LOGGED_IN_USER;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hackathon2022.Object.ObjectForum;
import com.example.hackathon2022.Object.ObjectReply;
import com.example.hackathon2022.adapter.FrontForumAdapter;
import com.example.hackathon2022.adapter.ReplyForumAdapter;
import com.example.hackathon2022.data.ReplyForumRepository;
import com.example.hackathon2022.data.models.ReplyForum;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ForumCardPage extends AppCompatActivity{

    TextView txtjudul, txtusernamepenanya, txtdate, txtkategori, txtpertanyaan, labelJawaban;
    EditText replyanswer;
    String forumkey, judul, username, kategori, pertanyaan, date, dateanswer;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date dates = new Date();

    ArrayList<ObjectReply> newList = new ArrayList<>();
    Context context = this;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_card_page);

        initComponents();
    }

    private void initComponents() {

        labelJawaban = findViewById(R.id.activityforumcardpage_lbljawaban);
        txtjudul = findViewById(R.id.activityforumcardpage_judul);
        txtusernamepenanya = findViewById(R.id.activityforumcardpage_username);
        txtkategori = findViewById(R.id.activityforumcardpage_kategori);
        txtpertanyaan = findViewById(R.id.activityforumcardpage_pertanyaan);
        txtdate = findViewById(R.id.date);

        Intent intent = getIntent();
        forumkey = intent.getStringExtra("key");
        judul = intent.getStringExtra("judul");
        username = intent.getStringExtra("username");
        kategori = intent.getStringExtra("kategori");
        pertanyaan = intent.getStringExtra("pertanyaan");
        date = intent.getStringExtra("date");
        dateanswer = intent.getStringExtra("dateanswer");

        txtjudul.setText(judul);
        txtdate.setText(date);
        txtusernamepenanya.setText(username);
        txtkategori.setText(kategori);
        txtpertanyaan.setText(pertanyaan);

        RecyclerView recyclerJawaban = findViewById(R.id.activityforumcardpage_recyclerview);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("replyforum");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if(forumkey.equals(postSnapshot.getValue(ObjectReply.class).getKey())){
                        newList.add(postSnapshot.getValue(ObjectReply.class));
                    }
                }

                if(newList.size()>0){
                    labelJawaban.setVisibility(View.VISIBLE);
                }else{
                    labelJawaban.setVisibility(View.INVISIBLE);
                }

                recyclerJawaban.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
                ReplyForumAdapter replyAdapter = new ReplyForumAdapter(context, newList);
                recyclerJawaban.setAdapter(replyAdapter);

                replyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError firebaseError) {
            }
        });

    }

    public void backtoHome(View view) {
        startActivity(new Intent(this, HomePage.class));
        finish();
    }

    public void pushToDatabase(View view) {
        replyanswer = findViewById(R.id.activityforumcardpage_addanswer);
        ReplyForumRepository.insertReplyForum(LOGGED_IN_USER.getUserName(),replyanswer.getText().toString(),dateFormat.format(dates),forumkey);
    }
}