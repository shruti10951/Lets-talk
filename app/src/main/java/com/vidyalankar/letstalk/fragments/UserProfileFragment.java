package com.vidyalankar.letstalk.fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.adapter.ProfileAdapter;
import com.vidyalankar.letstalk.adapter.UserProfileAdapter;
import com.vidyalankar.letstalk.model.PostModel;
import com.vidyalankar.letstalk.model.UserModel;

import java.util.ArrayList;
import java.util.Collections;

public class UserProfileFragment extends Fragment {

    ImageView profilePic;
    TextView userNameText, friendsTextView, followerCount, followingCount;

    FirebaseDatabase database;
    StorageReference reference;
    RecyclerView profileRV;
    ArrayList<PostModel> postList;

    public Uri imageUri;

    String userId;


    public UserProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_user_profile, container, false);

        profilePic= (ImageView) view.findViewById(R.id.user_profile_image);
        userNameText= (TextView) view.findViewById(R.id.user_profile_username);
        followerCount= (TextView) view.findViewById(R.id.user_follower_count);
        followingCount= (TextView) view.findViewById(R.id.user_following_count);
        profileRV= (RecyclerView) view.findViewById(R.id.profileRV);

        postList= new ArrayList<>();

        userId= getArguments().getString("userId");

        FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(userId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel= snapshot.getValue(UserModel.class);
                        if(userModel!=null)
                        {
                            String user= userModel.getUsername();
                            String uri= snapshot.child("profilePic").getValue(String.class);
                            int follower= userModel.getFollowerCount();
                            int following= userModel.getFollowingCount();

                            userNameText.setText(user);
                            Picasso.get().load(uri)
                                    .placeholder(R.drawable.user_profile_default)
                                    .into(profilePic);
                            followerCount.setText(follower+"");
                            followingCount.setText(following+"");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        UserProfileAdapter userProfileAdapter = new UserProfileAdapter(postList, getContext());
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        profileRV.setLayoutManager(layoutManager);
        profileRV.setNestedScrollingEnabled(false);
        profileRV.setAdapter(userProfileAdapter);

        FirebaseDatabase.getInstance().getReference()
                .child("Posts")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        postList.clear();
                        for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                            PostModel postModel= dataSnapshot.getValue(PostModel.class);
                            postModel.setPostId(dataSnapshot.getKey());
                            if(postModel.getPostedBy().equals(userId)){
                                postList.add(postModel);
                            }
                            Collections.reverse(postList);
                            userProfileAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        return view;
    }
}