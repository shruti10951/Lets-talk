package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.adapter.UserProfileAdapter;
import com.vidyalankar.letstalk.model.FollowingModel;
import com.vidyalankar.letstalk.model.FriendsModel;
import com.vidyalankar.letstalk.model.NotificationModel;
import com.vidyalankar.letstalk.model.PostModel;
import com.vidyalankar.letstalk.model.UserModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class UserProfileFragment extends Fragment {

    ImageView profilePic;
    TextView userNameText, followerCount, followingCount;
    TextView follow_textview;

    RecyclerView profileRV;
    ArrayList<PostModel> postList;

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
        follow_textview = (TextView) view.findViewById(R.id.follow_btn);

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

                            FirebaseDatabase.getInstance().getReference()
                                    .child("Users")
                                    .child(FirebaseAuth.getInstance().getUid())
                                    .child("Following")
                                    .child(userId)
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if(snapshot.exists()){
                                                follow_textview.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.following_btn_bg));
                                                follow_textview.setText("Following");
                                                follow_textview.setTextColor(getContext().getResources().getColor(R.color.black));
                                                follow_textview.setEnabled(false);
                                            }else {
                                                follow_textview.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {

                                                        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                                                        Date date= new Date();

                                                        FriendsModel friendsModel = new FriendsModel();
                                                        friendsModel.setFollowedBy(FirebaseAuth.getInstance().getUid());
                                                        friendsModel.setFollowedAt(date.getTime());

                                                        FollowingModel followingModel=new FollowingModel();
                                                        followingModel.setFollowedAt(date.getTime());
                                                        followingModel.setFollowedTo(userId);

                                                        FirebaseDatabase.getInstance().getReference()
                                                                .child("Users")
                                                                .child(userId)
                                                                .child("followers")
                                                                .child(FirebaseAuth.getInstance().getUid())
                                                                .setValue(friendsModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void unused) {
                                                                FirebaseDatabase.getInstance().getReference()
                                                                        .child("Users")
                                                                        .child(userId)
                                                                        .child("followerCount")
                                                                        .setValue(userModel.getFollowerCount()+1)
                                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                            @Override
                                                                            public void onSuccess(Void unused) {

                                                                                FirebaseDatabase.getInstance().getReference()
                                                                                        .child("Users")
                                                                                        .child(FirebaseAuth.getInstance().getUid())
                                                                                        .child("Following")
                                                                                        .child(userId)
                                                                                        .setValue(followingModel)
                                                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                            @Override
                                                                                            public void onSuccess(Void unused) {

                                                                                                FirebaseDatabase.getInstance().getReference()
                                                                                                        .child("Users")
                                                                                                        .child(FirebaseAuth.getInstance().getUid())
                                                                                                        .child("followingCount")
                                                                                                        .setValue(userModel.getFollowingCount()+1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                                    @Override
                                                                                                    public void onSuccess(Void unused) {
                                                                                                        follow_textview.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.following_btn_bg));
                                                                                                        follow_textview.setText("Following");
                                                                                                        follow_textview.setTextColor(getContext().getResources().getColor(R.color.black));
                                                                                                        follow_textview.setEnabled(false);
                                                                                                        Toast.makeText(getContext(), "You followed "+ userModel.getUsername() , Toast.LENGTH_SHORT).show();

                                                                                                        NotificationModel notificationModel= new NotificationModel();
                                                                                                        notificationModel.setNotificationBy(FirebaseAuth.getInstance().getUid());
                                                                                                        notificationModel.setNotificationAt(new Date().getTime());
                                                                                                        notificationModel.setType("follow");

                                                                                                        FirebaseDatabase.getInstance().getReference()
                                                                                                                .child("Notification")
                                                                                                                .child(userId)
                                                                                                                .push()
                                                                                                                .setValue(notificationModel);
                                                                                                    }
                                                                                                });
                                                                                            }
                                                                                        });


                                                                            }
                                                                        });
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

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