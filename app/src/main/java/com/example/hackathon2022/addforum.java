package com.example.hackathon2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hackathon2022.R;
import com.example.hackathon2022.data.ForumRepository;
import com.example.hackathon2022.data.SupplierRepository;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class addforum extends AppCompatActivity {

    StorageReference storageReference;

    public Uri getFilePath() {
        return filePath;
    }

    public void setFilePath(Uri filePath) {
        this.filePath = filePath;
    }

    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 22;

    private EditText txtJudul, txtPertanyaan;
    private ImageButton btnUploadFile;
    private TextView FileForum;
    ProgressDialog pd;

    private void initComponents() {
        pd = new ProgressDialog(this);
        pd.setMessage("Uploading....");
        txtJudul = findViewById(R.id.activityaddforum_inputjudulforum);
        txtPertanyaan = findViewById(R.id.activityaddforum_inputpertanyaan);
        btnUploadFile = findViewById(R.id.activityaddforum_btnTambahMedia);
        FileForum = findViewById(R.id.activityaddforum_inputmedia);
        storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://hackathon2022-85c99.appspot.com");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addforum);

        initComponents();

        btnUploadFile.setOnClickListener(new View.OnClickListener() {
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
            FileForum.setText(data.getData().toString());
        }
    }

    public void AddForum(View view) {
        String judul, pertanyaan;

        judul = txtJudul.getText().toString();
        pertanyaan = txtPertanyaan.getText().toString();

        boolean flag = true;
        if(judul.isEmpty()){
            txtJudul.setError("Silahkan masukan judul Forum");
            flag = false;
        }else if(pertanyaan.isEmpty()){
            txtPertanyaan.setError("Silahkan masukan pertanyaan Forum");
            flag = false;
        }

        if(flag){
            String path = "forumfile/" + UUID.randomUUID().toString();
            StorageReference childRef = storageReference.child(path);
            UploadTask uploadTask = childRef.putFile(filePath);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    pd.dismiss();
                    Toast.makeText(addforum.this, "Upload successful", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(addforum.this, "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
                }
            });

            ForumRepository.insertForum(judul, pertanyaan, path);
            startActivity(new Intent(this, HomePage.class));
        }

    }

}