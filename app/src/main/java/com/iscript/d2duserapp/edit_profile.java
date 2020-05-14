package com.iscript.d2duserapp;

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
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.UUID;


public class edit_profile extends AppCompatActivity {

    private EditText name,phone,email;
    private ImageView profilepoto;
    private  String sname,sphone,semail;
    private Button submit;
    FirebaseStorage storage;
    StorageReference storageReference;
    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();
    DatabaseReference myRef = database.getReference("profiles").child(uid);

    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;
    private String downloadurl;

    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("profiles");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        name=(EditText)findViewById(R.id.edit_name);
        phone=(EditText)findViewById(R.id.edit_phone);
        email=(EditText)findViewById(R.id.edit_mail);
        submit=(Button)findViewById(R.id.submit);
        profilepoto=(ImageView)findViewById(R.id.edit_img);

        profilepoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });


        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        mAuth = FirebaseAuth.getInstance();


        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                        sname=name.getText().toString();
                        sphone=phone.getText().toString();
                        semail=email.getText().toString();
                        String image=downloadurl;

                        myRef.child("Name").setValue(sname);
                        myRef.child("Phone").setValue(sphone);
                        myRef.child("Email").setValue(semail);
                        myRef.child("photoUrl").setValue(image);
                        finish();
                        //Toast.makeText(edit_profile.this, "name" +sname  +"ph"+sphone +"mail"+image, Toast.LENGTH_SHORT).show();

            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String jphone = dataSnapshot.child("Phone").getValue().toString();
                String jemail = dataSnapshot.child("Email").getValue().toString();
                String jname = dataSnapshot.child("Name").getValue().toString();
                String jpoto = dataSnapshot.child("photoUrl").getValue().toString();
                downloadurl=jpoto;
                Picasso.with(edit_profile.this)
                        .load(jpoto)
                        .fit()
                        .centerInside()
                        .into(profilepoto);

                if(!jname.equals("Name"))
                {
                    name.setText(jname);
                    phone.setText(jphone);
                    email.setText(jemail);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    //choose image
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                profilepoto.setImageBitmap(bitmap);
                uploadImage();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    // upload image
    private void uploadImage() {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            final StorageReference ref = storageReference.child("profilephotos/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri downloadPhotoUrl) {
                                    //Now play with downloadPhotoUrl
                                    //Store data into Firebase Realtime Database
                                    downloadurl = downloadPhotoUrl.toString();
                                    //Toast.makeText(edit_profile.this, "url "+downloadurl, Toast.LENGTH_SHORT).show();
                                }
                            });

                            Toast.makeText(edit_profile.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(edit_profile.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }
}
