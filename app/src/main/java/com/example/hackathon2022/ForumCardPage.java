package com.example.hackathon2022;

import static com.example.hackathon2022.data.UserRepository.LOGGED_IN_USER;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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
import java.util.Objects;

public class ForumCardPage extends AppCompatActivity{

    TextView txtjudul, txtusernamepenanya, txtdate, txtkategori, txtpertanyaan, labelJawaban, txtStarCount, answercount;
    EditText replyanswer;
    String forumkey, judul, username, kategori, pertanyaan, date, dateanswer;
    Integer star;
    ImageButton starBtn;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date dates = new Date();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, ref;

    ArrayList<ObjectReply> newList = new ArrayList<>();
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_card_page);

        initComponents();
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    private void initComponents() {

        answercount = findViewById(R.id.activityforumcardpage_answercount);
        replyanswer = findViewById(R.id.activityforumcardpage_addanswer);
        labelJawaban = findViewById(R.id.activityforumcardpage_lbljawaban);
        txtjudul = findViewById(R.id.activityforumcardpage_judul);
        txtusernamepenanya = findViewById(R.id.activityforumcardpage_username);
        txtkategori = findViewById(R.id.activityforumcardpage_kategori);
        txtpertanyaan = findViewById(R.id.activityforumcardpage_pertanyaan);
        txtdate = findViewById(R.id.date);
        txtStarCount = findViewById(R.id.activityforumcardpage_starCount);
        starBtn = findViewById(R.id.activityforumcardpage_starButton);

        Intent intent = getIntent();
        forumkey = intent.getStringExtra("key");
        judul = intent.getStringExtra("judul");
        username = intent.getStringExtra("username");
        kategori = intent.getStringExtra("kategori");
        pertanyaan = intent.getStringExtra("pertanyaan");
        date = intent.getStringExtra("date");
        dateanswer = intent.getStringExtra("dateanswer");
        star = Integer.valueOf(intent.getStringExtra("star"));

        txtjudul.setText(judul);
        txtdate.setText(date);
        txtusernamepenanya.setText(username);
        txtkategori.setText(kategori);
        txtpertanyaan.setText(pertanyaan);
        if(star>999){
            float temp = (float)star/1000;
            txtStarCount.setText(String.valueOf(String.format("%.1f",temp))+" ribu");
        }else{
            txtStarCount.setText(star.toString());
        }

        replyanswer.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                String temp = String.valueOf(replyanswer.length());
                if(replyanswer.length()>999){
                    temp = String.valueOf(replyanswer.length()/1000)+" ribu";
                }
                answercount.setText(temp);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        RecyclerView recyclerJawaban = findViewById(R.id.activityforumcardpage_recyclerview);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("replyforum");
        ref = firebaseDatabase.getReference("forum");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if(forumkey.equals(Objects.requireNonNull(postSnapshot.getValue(ObjectReply.class)).getForumKey())){
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

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void AddStar(View view) {
        ref.child(forumkey+"/star").setValue(star+1);
        int tempint = star + 1;
        float tempo;
        String tempstr = "";
        if(tempint>999){
            tempo=(float)tempint/1000;
            tempstr=String.valueOf(String.format("%.1f",tempo))+" ribu";
        }
        txtStarCount.setText(tempstr);
        starBtn.setImageResource(R.drawable.ic_baseline_star_24);
    }
}