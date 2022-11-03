package com.example.hackathon2022;

import static com.example.hackathon2022.data.UserRepository.LOGGED_IN_USER;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hackathon2022.Object.ObjectUser;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class Profile extends AppCompatActivity {

    private TextView name, phone, lokasi;
    private LinearLayout btnLogout;
    private ImageView imageavatar;
    NestedScrollView myScrollView;

    String namestr, nomorstr, lokasistr, deskripsistr, pathstr, kategoristr;

    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ImageButton btnLogout1;
    TextView btnLogout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initComponents();
    }

    @SuppressLint("WrongViewCast")
    private void initComponents() {
        name = findViewById(R.id.activityprofile_name);
        phone = findViewById(R.id.activityprofile_phone);
        lokasi = findViewById(R.id.activityprofile_lokasi);
        imageavatar = findViewById(R.id.activityprofile_avatar);
        btnLogout = findViewById(R.id.logout_btn);
        btnLogout.setClickable(true);
        btnLogout1 = findViewById(R.id.logout_btn1);
        btnLogout1.setClickable(true);
        btnLogout2 = findViewById(R.id.logout_btn2);
        btnLogout2.setClickable(true);
        myScrollView = findViewById(R.id.myScrollView);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if(postSnapshot.getValue(ObjectUser.class).getKey().equals(LOGGED_IN_USER.getUserKey())){
                        namestr = postSnapshot.getValue(ObjectUser.class).getNama();
                        nomorstr = postSnapshot.getValue(ObjectUser.class).getNomor();
                        lokasistr = postSnapshot.getValue(ObjectUser.class).getLokasi();
                        deskripsistr = postSnapshot.getValue(ObjectUser.class).getDeskripsi();
                        pathstr = postSnapshot.getValue(ObjectUser.class).getImagePath();
                        kategoristr = postSnapshot.getValue(ObjectUser.class).getKategori();

                        name.setText(namestr);
                        phone.setText(nomorstr);
                        lokasi.setText(lokasistr);
                        storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://hackathon2022-85c99.appspot.com").child(pathstr);
                        try {
                            File localfile = File.createTempFile("temp","jpg");
                            storageReference.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                                    imageavatar.setImageBitmap(bitmap);
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError firebaseError) {
            }
        });

        btnLogout.setOnClickListener(v -> {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Profile.this);

            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.commit();

            Intent i = new Intent(Profile.this, WelcomePage.class);
            startActivity(i);
            finish();
        });

        btnLogout1.setOnClickListener(v -> {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Profile.this);

            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.commit();

            Intent i = new Intent(Profile.this, WelcomePage.class);
            startActivity(i);
            finish();
        });

        btnLogout2.setOnClickListener(v -> {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Profile.this);

            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.commit();

            Intent i = new Intent(Profile.this, WelcomePage.class);
            startActivity(i);
            finish();
        });
    }

    public void intoHome(View view) {
        startActivity(new Intent(this, HomePage.class));
        overridePendingTransition(R.transition.slide_enter, R.transition.slide_exit);
        finish();
    }

    public void intoSupplier(View view) {
        startActivity(new Intent(this, SupplierPage.class));
        overridePendingTransition(R.transition.slide_enter, R.transition.slide_exit);
        finish();
    }

    public void intoJasa(View view) {
        startActivity(new Intent(this, JasaPage.class));
        overridePendingTransition(R.transition.slide_enter, R.transition.slide_exit);
        finish();
    }

    public void intoProfile(View view) {
        myScrollView.smoothScrollTo(0,0);
    }

    public void editProfile(View view) {
        Intent i = new Intent(this, UbahProfileActivity.class);
        i.putExtra("name",namestr);
        i.putExtra("phone",nomorstr);
        i.putExtra("kategori",kategoristr);
        i.putExtra("lokasi",lokasistr);
        i.putExtra("deskripsi",deskripsistr);
        i.putExtra("key",LOGGED_IN_USER.getUserKey());
        i.putExtra("imagepath", pathstr);
        startActivity(i);
    }

}