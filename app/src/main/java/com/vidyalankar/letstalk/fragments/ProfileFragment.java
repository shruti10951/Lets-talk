package com.vidyalankar.letstalk.fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.vidyalankar.letstalk.adapter.ProfileAdapter;
import com.vidyalankar.letstalk.model.PostModel;
import com.vidyalankar.letstalk.model.UserModel;

import java.util.ArrayList;
import java.util.Collections;


public class ProfileFragment extends Fragment {

    ImageView profilePic;
    TextView userNameTextView, userEmailTextView, friendsTextView, followingTextView, followerCount, followingCount;
    ImageView editProfile;
    ProgressBar progressBar;

    FirebaseUser user;
    DatabaseReference reference;
    StorageReference storageRef;
    RecyclerView profileRV;
    ArrayList<PostModel> postList;
    FirebaseDatabase database;
    FirebaseAuth auth;

    String userID;

    public Uri imageUri;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profilePic= (ImageView) view.findViewById(R.id.user_profile_comment);
        userNameTextView= (TextView) view.findViewById(R.id.profile_username);
        userEmailTextView= (TextView) view.findViewById(R.id.profile_email);
        friendsTextView= (TextView) view.findViewById(R.id.friends_profile);
        editProfile= (ImageView) view.findViewById(R.id.changeProfile);
        followerCount= (TextView) view.findViewById(R.id.follower_count);
        progressBar= (ProgressBar) view.findViewById(R.id.profileProgressBar);
        followingCount= (TextView) view.findViewById(R.id.following_count);
        followingTextView= (TextView) view.findViewById(R.id.following);

        user= FirebaseAuth.getInstance().getCurrentUser();
        userID= user.getUid();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        storageRef= FirebaseStorage.getInstance().getReference().child("profile_pic").child(user.getUid());
        profileRV= view.findViewById(R.id.profileRV);
        postList= new ArrayList<>();
        database= FirebaseDatabase.getInstance();
        auth= FirebaseAuth.getInstance();


        reference.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userModelProfile = snapshot.getValue(UserModel.class);
                if(userModelProfile !=null)
                {
                    String userName= userModelProfile.username;
                    String userEmail= userModelProfile.email;
                    String uri= snapshot.child("profilePic").getValue(String.class);
                    int follower_count= userModelProfile.getFollowerCount();
                    int following_count= userModelProfile.getFollowingCount();

                    userNameTextView.setText(userName);
                    userEmailTextView.setText(userEmail);
                    Picasso.get().load(uri).placeholder(R.drawable.user_profile_default)
                            .into(profilePic);
                    followerCount.setText(follower_count+"");
                    followingCount.setText(following_count+"");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        followingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FriendsFragment nextFrag= new FriendsFragment();

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView2, new FollowingFragment())
                        .addToBackStack(null)
                        .commit();
                Toast.makeText(getActivity(), "Your friends are here to help you!", Toast.LENGTH_SHORT).show();
            }
        });

        friendsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FriendsFragment nextFrag= new FriendsFragment();

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView2, new FriendsFragment())
                        .addToBackStack(null)
                        .commit();
                Toast.makeText(getActivity(), "Your friends are here to help you!", Toast.LENGTH_SHORT).show();
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGetContent.launch("image/*");
            }
        });

        ProfileAdapter profileAdapter = new ProfileAdapter(postList, getContext());
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        profileRV.setLayoutManager(layoutManager);
        profileRV.setNestedScrollingEnabled(false);
        profileRV.setAdapter(profileAdapter);

        database.getReference().child("Posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    PostModel postModel= dataSnapshot.getValue(PostModel.class);
                    postModel.setPostId(dataSnapshot.getKey());
                    if (postModel.getPostedBy().equals(FirebaseAuth.getInstance().getUid())){
                        postList.add(postModel);
                    }
                }
                Collections.reverse(postList);
                profileAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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