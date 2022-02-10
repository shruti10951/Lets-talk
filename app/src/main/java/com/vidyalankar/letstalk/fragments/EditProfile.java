package com.vidyalankar.letstalk.fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.model.Profile;


public class EditProfile extends Fragment {

    public EditProfile() {
        // Required empty public constructor
    }

    ImageView edited_profile_image;
    Button save_profile;

    public Uri imageUri;

    FirebaseUser user;
    DatabaseReference reference;
    StorageReference storageRef;

    ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_edit_profile, container, false);

        edited_profile_image= (ImageView) view.findViewById(R.id.edited_profile_pic);
        save_profile= (Button) view.findViewById(R.id.save_edited_profile);
        progressBar= (ProgressBar) view.findViewById(R.id.profile_progressBar);
        progressBar.setVisibility(View.GONE);

        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference().child("ProfilePic");
        storageRef= FirebaseStorage.getInstance().getReference().child("ProfilePic");

        edited_profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGetContent.launch("image/*");
            }
        });

        save_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save_profileImage();
            }
        });

        return view;
    }

    private void save_profileImage() {

        progressBar.setVisibility(View.VISIBLE);

        StorageReference fileRef= storageRef.child(user.getUid() + ".jpg");
        fileRef.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful())
                {
                   fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                       @Override
                       public void onSuccess(Uri uri) {
                            Profile profile= new Profile(user.getUid(), uri.toString());
                            FirebaseDatabase.getInstance().getReference().child("Profile").setValue(profile);
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), "Image uploaded", Toast.LENGTH_LONG).show();
                       }
                   });
                }else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getActivity(),  task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(),"Failed! "+ e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            if(result!= null)
            {
                edited_profile_image.setImageURI(result);
                imageUri= result;
            }
        }
    });
}