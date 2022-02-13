package com.vidyalankar.letstalk.fragments;

import android.net.Uri;
import android.os.Bundle;


import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.model.User;


public class ProfileFragment extends Fragment {

    ImageView profilePic;
    TextView userNameTextView, userEmailTextView;
    ImageView editProfile;
    ProgressBar progressBar;

    FirebaseUser user;
    DatabaseReference reference;
    StorageReference storageRef;

    String userID;

    public Uri imageUri;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profilePic= (ImageView) view.findViewById(R.id.profile_user);
        userNameTextView= (TextView) view.findViewById(R.id.profile_username);
        userEmailTextView= (TextView) view.findViewById(R.id.profile_email);
        editProfile= (ImageView) view.findViewById(R.id.changeProfile);
        progressBar= (ProgressBar) view.findViewById(R.id.profileProgressBar);

        user= FirebaseAuth.getInstance().getCurrentUser();
        userID= user.getUid();
        reference= FirebaseDatabase.getInstance().getReference("Users");
       // reference= FirebaseDatabase.getInstance().getReference().child("profile_pic");
        storageRef= FirebaseStorage.getInstance().getReference().child("profile_pic").child(user.getUid());



        reference.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile= snapshot.getValue(User.class);
                if(userProfile!=null)
                {
                    String userName= userProfile.username;
                    String userEmail= userProfile.email;
                    String uri= snapshot.child("profilePic").getValue(String.class);

                    userNameTextView.setText(userName);
                    userEmailTextView.setText(userEmail);
                    Picasso.get().load(uri).into(profilePic);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.fragmentContainerView2, new EditProfile());
//                fragmentTransaction.addToBackStack(null);
//                Toast.makeText(getActivity(), "Edit your profile...", Toast.LENGTH_LONG).show();
//                fragmentTransaction.commit();
                mGetContent.launch("image/*");
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            if(result!= null)
            {
                profilePic.setImageURI(result);
                imageUri= result;
                save_profileImage();
            }
        }
    });

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
                            FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).child("profilePic").setValue(uri.toString());
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
}
