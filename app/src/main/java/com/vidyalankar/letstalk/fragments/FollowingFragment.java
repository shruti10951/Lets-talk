package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.adapter.FollowingAdapter;
import com.vidyalankar.letstalk.adapter.FriendsAdapter;
import com.vidyalankar.letstalk.model.FollowingModel;
import com.vidyalankar.letstalk.model.FriendsModel;

import java.util.ArrayList;
import java.util.Collections;

public class FollowingFragment extends Fragment {

    ArrayList<FollowingModel> list= new ArrayList<FollowingModel>();
    FirebaseAuth auth;
    FirebaseDatabase database;
    RecyclerView followingRV;

    public FollowingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth= FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_following, container, false);

        followingRV= view.findViewById(R.id.following_rv );

        list= new ArrayList<>();

        FollowingAdapter adapter= new FollowingAdapter(getContext(), list);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext());
        followingRV.setLayoutManager(layoutManager);
        followingRV.setNestedScrollingEnabled(false);
        followingRV.setAdapter(adapter);

        database.getReference()
                .child("Users")
                .child(auth.getUid())
                .child("Following")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for(DataSnapshot dataSnapshot: snapshot.getChildren())
                        {
                            FollowingModel user= dataSnapshot.getValue(FollowingModel.class);
                            list.add(user);
                        }
                        Collections.reverse(list);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getActivity(), "Something happened!", Toast.LENGTH_SHORT).show();
                    }
                });

        return view;
    }
}