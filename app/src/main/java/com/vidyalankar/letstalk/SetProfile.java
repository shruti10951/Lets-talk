package com.vidyalankar.letstalk;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class SetProfile extends AppCompatActivity {

    ImageView profile_image;
    Button save_profile, change_profile;

    public Uri imageUri;
    FirebaseStorage storage;
    StorageTask uploadTask;
    StorageReference storageProfilePicReference;

    FirebaseAuth mAuth;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_profile);
        profile_image= (ImageView) findViewById(R.id.select_profile_pic);
        change_profile= (Button) findViewById(R.id.change_profile);
        save_profile= (Button) findViewById(R.id.save_profile);

        mAuth= FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users");
        storageProfilePicReference= FirebaseStorage.getInstance().getReference().child("Profile Pic");
        storage= FirebaseStorage.getInstance();

        change_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mGetContent.launch("image/*");
            }
        });

        save_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });

    }

    private void uploadImage() {

        if(imageUri!= null)
        {
            final StorageReference fileRef= storageProfilePicReference.child(mAuth.getCurrentUser().getUid()+ ".jpg");
//                    storage.getReference().child()
                    //getReference("Users").child("Profile" + FirebaseAuth.getInstance().getCurrentUser().getUid());
            fileRef.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                    if(task.isSuccessful())
                    {
                        Toast.makeText(SetProfile.this,"Image uploaded successfully!", Toast.LENGTH_LONG).show();
                    }else
                    {
                        Toast.makeText(SetProfile.this,  task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
        else{
            Toast.makeText(SetProfile.this, "Select an image to upload!", Toast.LENGTH_LONG).show();
        }

    }


    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if(result!= null)
                {
                    profile_image.setImageURI(result);
                    imageUri= result;
                }
            }
        });



}