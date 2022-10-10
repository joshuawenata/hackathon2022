package com.example.hackathon2022;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hackathon2022.data.SupplierRepository;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class addsupplier extends AppCompatActivity {

    StorageReference storageReference;

    public Uri getFilePath() {
        return filePath;
    }

    public void setFilePath(Uri filePath) {
        this.filePath = filePath;
    }

    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 22;

    private EditText txtNama, txtLokasi, txtKategori, txtDeskripsi;
    private ImageButton btnUploadAddSupplier;
    private ImageView imageprofile;
    ProgressDialog pd;

    private void initComponents() {
        pd = new ProgressDialog(this);
        pd.setMessage("Uploading....");
        txtNama = findViewById(R.id.activityaddsupplier_inputnamasupplier);
        txtLokasi = findViewById(R.id.activityaddsupplier_inputLokasi);
        txtKategori = findViewById(R.id.activityaddsupplier_inputKategori);
        txtDeskripsi = findViewById(R.id.activityaddsupplier_inputDeskripsi);
        btnUploadAddSupplier = findViewById(R.id.activityaddsupplier_btnTambahDariPerangkat);
        imageprofile = findViewById(R.id.activityaddsupplier_imageprofile);
        storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://hackathon2022-85c99.appspot.com");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsupplier);

        initComponents();

        btnUploadAddSupplier.setOnClickListener(new View.OnClickListener() {
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

    public void AddSupplier(View view) {
        String nama, lokasi, kategori, deskripsi;

        nama = txtNama.getText().toString();
        lokasi = txtLokasi.getText().toString();
        kategori = txtKategori.getText().toString();
        deskripsi = txtDeskripsi.getText().toString();

        boolean flag = true;
        if(nama.isEmpty()){
            txtNama.setError("Silahkan masukan nama supplier");
            flag = false;
        }else if(lokasi.isEmpty()){
            txtLokasi.setError("Silahkan masukan lokasi supplier");
            flag = false;
        }else if(kategori.isEmpty()){
            txtKategori.setError("Silahkan masukan kategori supplier");
            flag = false;
        }else if(deskripsi.isEmpty()){
            txtDeskripsi.setError("Silahkan masukan deskripsi supplier");
            flag = false;
        }

        if(flag){
            String path = "supplierimages/" + UUID.randomUUID().toString();
            StorageReference childRef = storageReference.child(path);
            UploadTask uploadTask = childRef.putFile(filePath);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    pd.dismiss();
                    Toast.makeText(addsupplier.this, "Upload successful", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(addsupplier.this, "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
                }
            });

            SupplierRepository.insertSupplier(nama, lokasi, kategori, deskripsi, path);
            startActivity(new Intent(this, SupplierPage.class));
        }

    }
}