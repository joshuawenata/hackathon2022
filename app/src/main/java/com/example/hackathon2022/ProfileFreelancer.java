package com.example.hackathon2022;

import static com.example.hackathon2022.data.UserRepository.LOGGED_IN_USER;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
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

public class ProfileFreelancer extends AppCompatActivity {

    TextView name, phone, lokasi;
    ImageView imageavatar;

    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    String namestr, nomorstr, lokasistr, deskripsistr, pathstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_freelancer);

        initComponents();
    }

    private void initComponents() {
        name = findViewById(R.id.activityprofilefreelancer_name);
        phone = findViewById(R.id.activityprofilefreelancer_phone);
        lokasi = findViewById(R.id.activityprofilefreelancer_lokasi);
        imageavatar = findViewById(R.id.activityprofilefreelancer_avatar);

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
    }


}