package com.example.hackathon2022;

import static com.example.hackathon2022.data.UserRepository.LOGGED_IN_USER;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ProfileFreelancerEdit extends AppCompatActivity {

    ProgressDialog pd;
    ImageView imageprofile, imagebtn;
    String name, nomor, kategori, lokasi, deskripsi, userKey, imagepath;
    EditText Namefield, Nomorfield, Kategorifield, Lokasifield, Deskripsifield;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public static int temp = 0;
    StorageReference storageReference, storageReference2;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 22;

    public Uri getFilePath() {
        return filePath;
    }

    public void setFilePath(Uri filePath) {
        this.filePath = filePath;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_profile);
        initComponents();
    }

    private void initComponents() {
        pd = new ProgressDialog(this);
        pd.setMessage("Uploading....");
        imageprofile = findViewById(R.id.activityubahprofile_imageprofile);
        imagebtn = findViewById(R.id.activityubahprofile_imagebtn);

        Namefield = findViewById(R.id.activityubahprofile_name);
        Nomorfield = findViewById(R.id.activityubahprofile_phone);
        Kategorifield = findViewById(R.id.activityubahprofile_kategori_umkm);
        Lokasifield = findViewById(R.id.activityubahprofile_lokasi_umkm);
        Deskripsifield = findViewById(R.id.activityubahprofile_deskripsi_umkm);

        Intent i = getIntent();
        name = i.getStringExtra("name");
        nomor = i.getStringExtra("phone");
        kategori = i.getStringExtra("kategori");
        lokasi = i.getStringExtra("lokasi");
        deskripsi = i.getStringExtra("deskripsi");
        userKey = i.getStringExtra("key");
        imagepath = i.getStringExtra("imagepath");

        Namefield.setText(name);
        Nomorfield.setText(nomor);
        Kategorifield.setText(kategori);
        Lokasifield.setText(lokasi);
        Deskripsifield.setText(deskripsi);

        storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://hackathon2022-85c99.appspot.com").child(imagepath);
        try {
            File localfile = File.createTempFile("temp","jpg");
            storageReference.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                    imageprofile.setImageBitmap(bitmap);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        imagebtn.setOnClickListener(new View.OnClickListener() {
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
            try {

                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
                imageprofile.setImageBitmap(bitmap);
            }

            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void Save(View view) {

        if(filePath!=null){
            temp = 0;
        }else{
            temp = 1;
        }

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");
        databaseReference.child(userKey+"/nama").setValue(Namefield.getText().toString());
        databaseReference.child(userKey+"/nomor").setValue(Nomorfield.getText().toString());
        databaseReference.child(userKey+"/kategori").setValue(Kategorifield.getText().toString());
        databaseReference.child(userKey+"/lokasi").setValue(Lokasifield.getText().toString());
        databaseReference.child(userKey+"/deskripsi").setValue(Deskripsifield.getText().toString());

        String path = "";
        if(temp==0){
            path = "userimages/" + UUID.randomUUID().toString();
            databaseReference.child(userKey+"/imagepath").setValue(path);
            storageReference2 = FirebaseStorage.getInstance().getReferenceFromUrl("gs://hackathon2022-85c99.appspot.com");
            StorageReference childRef = storageReference2.child(path);
            UploadTask uploadTask = childRef.putFile(filePath);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    pd.dismiss();
                    Toast.makeText(ProfileFreelancerEdit.this, "Upload successful", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(ProfileFreelancerEdit.this, "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            temp = 0;
            path = "avatar.png";
        }
        startActivity(new Intent(ProfileFreelancerEdit.this, ProfileFreelancer.class));
        finish();
    }

    public void backtoProfile(View view) {
        startActivity(new Intent(this, ProfileFreelancer.class));
        finish();
    }
}