package com.example.hackathon2022;

import static com.example.hackathon2022.data.UserRepository.LOGGED_IN_USER;
import static com.example.hackathon2022.data.UserRepository.find;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hackathon2022.Object.ObjectGallery;
import com.example.hackathon2022.Object.ObjectUser;
import com.example.hackathon2022.adapter.FreelancerAdapter;
import com.example.hackathon2022.adapter.FrontForumAdapter;
import com.example.hackathon2022.data.GalleryRepository;
import com.example.hackathon2022.data.UserRepository;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class ProfileFreelancer extends AppCompatActivity {

    TextView name, phone, lokasi, deskripsi;
    private final int PICK_IMAGE_REQUEST = 22;
    public static int temp = 0;
    ImageView imageavatar, addgallery;
    ArrayList<ObjectGallery> newList = new ArrayList<>();

    StorageReference storageReference;
    StorageReference storageReferences;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,databaseReferences;
    ProgressDialog pd;
    RecyclerView rv;
    Context context = this;

    private Uri filePath;
    public Uri getFilePath() {
        return filePath;
    }
    public void setFilePath(Uri filePath) {
        this.filePath = filePath;
    }

    String namestr, nomorstr, lokasistr, deskripsistr, pathstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_freelancer);

        initComponents();
    }

    private void initComponents() {
        pd = new ProgressDialog(this);
        pd.setMessage("Uploading....");
        name = findViewById(R.id.activityprofilefreelancer_name);
        phone = findViewById(R.id.activityprofilefreelancer_phone);
        lokasi = findViewById(R.id.activityprofilefreelancer_lokasi);
        deskripsi = findViewById(R.id.activityprofilefreelancer_deskripsi);
        imageavatar = findViewById(R.id.activityprofilefreelancer_avatar);
        addgallery = findViewById(R.id.activityprofilefreelancer_addgallery);
        rv = findViewById(R.id.activityprofilefreelancer_recyclerviewfreelancer);
        storageReferences = FirebaseStorage.getInstance().getReferenceFromUrl("gs://hackathon2022-85c99.appspot.com");

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");
        databaseReferences = firebaseDatabase.getReference("gallery");

        databaseReferences.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    newList.add(postSnapshot.getValue(ObjectGallery.class));
                }

                rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
                FreelancerAdapter TopAdapter = new FreelancerAdapter(context, newList);
                rv.setAdapter(TopAdapter);
                TopAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
                        deskripsi.setText(deskripsistr);
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

        addgallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data)
    {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        if (resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            setFilePath(data.getData());
        }

        if(filePath!=null){
            temp = 0;
        }else{
            temp = 1;
        }

        String path;
        if(temp==0){
            path = "gallery/" + UUID.randomUUID().toString();
            Log.i("TAG", path);
            StorageReference childRef2 = storageReferences.child(path);
            UploadTask uploadTask = childRef2.putFile(filePath);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    pd.dismiss();
                    Toast.makeText(ProfileFreelancer.this, "Upload successful", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(ProfileFreelancer.this, "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            temp = 0;
            path = "";
        }

        GalleryRepository.insertGallery(LOGGED_IN_USER.getUserKey(), path);
    }

    public void intoLogout(View view) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ProfileFreelancer.this);

        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();

        Intent i = new Intent(ProfileFreelancer.this, WelcomePage.class);
        startActivity(i);
        finish();
    }
}