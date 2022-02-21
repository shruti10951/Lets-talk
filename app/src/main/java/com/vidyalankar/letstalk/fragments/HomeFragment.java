package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.adapter.PostAdapter;
import com.vidyalankar.letstalk.model.PostModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView homeRV;
    ArrayList<PostModel> postList;
    FirebaseDatabase database;
    FirebaseAuth auth;
    ShimmerFrameLayout shimmerFrameLayout;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        shimmerFrameLayout= view.findViewById(R.id.shimmer);
        shimmerFrameLayout.startShimmer();
        homeRV= view.findViewById(R.id.homeRV);
        postList= new ArrayList<>();
        database= FirebaseDatabase.getInstance();
        auth= FirebaseAuth.getInstance();

        PostAdapter postAdapter = new PostAdapter(postList, getContext());
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        homeRV.setLayoutManager(layoutManager);
        homeRV.setNestedScrollingEnabled(false);
        homeRV.setAdapter(postAdapter);

        database.getReference().child("Posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                homeRV.setVisibility(View.VISIBLE);
                postList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    PostModel postModel= dataSnapshot.getValue(PostModel.class);
                    postModel.setPostId(dataSnapshot.getKey());
                    if (!postModel.getPostedBy().equals(FirebaseAuth.getInstance().getUid())){
                        postList.add(postModel);
                    }
                }
                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}