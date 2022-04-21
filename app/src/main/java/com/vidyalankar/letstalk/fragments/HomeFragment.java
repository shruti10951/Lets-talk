package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.adapter.PostAdapter;
import com.vidyalankar.letstalk.model.PostModel;

import java.util.ArrayList;
import java.util.Collections;

public class HomeFragment extends Fragment {

    RecyclerView homeRV;
    ArrayList<PostModel> postList;
    FirebaseDatabase database;
    FirebaseAuth auth;
    ShimmerFrameLayout shimmerFrameLayout;
    TextView all, family, selfHarm, friends,
            hopes, bullying, health, work, parenting,
            education, religion, lgbt, positive,
            pregnancy, mentalHealth, addiction,
            selfCare, grief, anxiety, disabilities,
            depression, others;
    ConstraintLayout constraintLayout;

    String type="";

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        constraintLayout= view.findViewById(R.id.constraintLayout123);

        constraintLayout.setVisibility(View.GONE);
        shimmerFrameLayout= view.findViewById(R.id.shimmer);
        shimmerFrameLayout.startShimmer();
        homeRV= view.findViewById(R.id.homeRV);
        postList= new ArrayList<>();
        database= FirebaseDatabase.getInstance();
        auth= FirebaseAuth.getInstance();

        all= (TextView) view.findViewById(R.id.all);
        family= (TextView) view.findViewById(R.id.family);
        selfHarm= (TextView) view.findViewById(R.id.selfHarm);
        friends= (TextView) view.findViewById(R.id.friends);
        hopes= (TextView) view.findViewById(R.id.hopes);
        bullying= (TextView) view.findViewById(R.id.bullying);
        health= (TextView) view.findViewById(R.id.health);
        work= (TextView) view.findViewById(R.id.work);
        parenting= (TextView) view.findViewById(R.id.parenting);
        education= (TextView) view.findViewById(R.id.education);
        religion= (TextView) view.findViewById(R.id.religion);
        lgbt= (TextView) view.findViewById(R.id.lgbt);
        positive= (TextView) view.findViewById(R.id.positive);
        pregnancy= (TextView) view.findViewById(R.id.pregnancy);
        mentalHealth= (TextView) view.findViewById(R.id.mentalHealth);
        selfCare= (TextView) view.findViewById(R.id.selfCare);
        addiction= (TextView) view.findViewById(R.id.addiction);
        grief= (TextView) view.findViewById(R.id.grief);
        anxiety= (TextView) view.findViewById(R.id.anxiety);
        disabilities= (TextView) view.findViewById(R.id.disabilities);
        depression= (TextView) view.findViewById(R.id.depression);
        others= (TextView) view.findViewById(R.id.others);

        PostAdapter postAdapter = new PostAdapter(postList, getContext());
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        homeRV.setLayoutManager(layoutManager);
        homeRV.setNestedScrollingEnabled(false);
        homeRV.setAdapter(postAdapter);

        all.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.white));
        all.setTextColor(getContext().getColor(R.color.blue_bg));

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
                Collections.reverse(postList);
                postAdapter.notifyDataSetChanged();
                if(postList.isEmpty()){
                    constraintLayout.setVisibility(View.VISIBLE);
                }else{
                    constraintLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query= database.getReference().child("Posts").orderByChild("type").equalTo(family.getText().toString());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        homeRV.setVisibility(View.VISIBLE);
                        postList.clear();
                        all.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        all.setTextColor(getContext().getColor(R.color.white));
                        family.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.white));
                        family.setTextColor(getContext().getColor(R.color.blue_bg));
                        selfHarm.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfHarm.setTextColor(getContext().getColor(R.color.white));
                        friends.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        friends.setTextColor(getContext().getColor(R.color.white));
                        hopes.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        hopes.setTextColor(getContext().getColor(R.color.white));
                        bullying.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        bullying.setTextColor(getContext().getColor(R.color.white));
                        health.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        health.setTextColor(getContext().getColor(R.color.white));
                        work.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        work.setTextColor(getContext().getColor(R.color.white));
                        parenting.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        parenting.setTextColor(getContext().getColor(R.color.white));
                        education.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        education.setTextColor(getContext().getColor(R.color.white));
                        religion.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        religion.setTextColor(getContext().getColor(R.color.white));
                        lgbt.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        lgbt.setTextColor(getContext().getColor(R.color.white));
                        positive.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        positive.setTextColor(getContext().getColor(R.color.white));
                        pregnancy.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        pregnancy.setTextColor(getContext().getColor(R.color.white));
                        mentalHealth.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        mentalHealth.setTextColor(getContext().getColor(R.color.white));
                        selfCare.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfCare.setTextColor(getContext().getColor(R.color.white));
                        addiction.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        addiction.setTextColor(getContext().getColor(R.color.white));
                        grief.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        grief.setTextColor(getContext().getColor(R.color.white));
                        anxiety.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        anxiety.setTextColor(getContext().getColor(R.color.white));
                        disabilities.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        disabilities.setTextColor(getContext().getColor(R.color.white));
                        depression.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        depression.setTextColor(getContext().getColor(R.color.white));
                        others.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        others.setTextColor(getContext().getColor(R.color.white));

                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            PostModel postModel= dataSnapshot.getValue(PostModel.class);
                            postModel.setPostId(dataSnapshot.getKey());
                            if (!postModel.getPostedBy().equals(FirebaseAuth.getInstance().getUid())){
                                postList.add(postModel);
                            }
                        }
                        Collections.reverse(postList);
                        postAdapter.notifyDataSetChanged();
                        if(postList.isEmpty()){
                            constraintLayout.setVisibility(View.VISIBLE);
                        }else{
                            constraintLayout.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        selfHarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query= database.getReference().child("Posts").orderByChild("type").equalTo(selfHarm.getText().toString());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        homeRV.setVisibility(View.VISIBLE);
                        postList.clear();
                        all.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        all.setTextColor(getContext().getColor(R.color.white));
                        family.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        family.setTextColor(getContext().getColor(R.color.white));
                        selfHarm.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.white));
                        selfHarm.setTextColor(getContext().getColor(R.color.blue_bg));
                        friends.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        friends.setTextColor(getContext().getColor(R.color.white));
                        hopes.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        hopes.setTextColor(getContext().getColor(R.color.white));
                        bullying.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        bullying.setTextColor(getContext().getColor(R.color.white));
                        health.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        health.setTextColor(getContext().getColor(R.color.white));
                        work.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        work.setTextColor(getContext().getColor(R.color.white));
                        parenting.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        parenting.setTextColor(getContext().getColor(R.color.white));
                        education.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        education.setTextColor(getContext().getColor(R.color.white));
                        religion.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        religion.setTextColor(getContext().getColor(R.color.white));
                        lgbt.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        lgbt.setTextColor(getContext().getColor(R.color.white));
                        positive.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        positive.setTextColor(getContext().getColor(R.color.white));
                        pregnancy.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        pregnancy.setTextColor(getContext().getColor(R.color.white));
                        mentalHealth.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        mentalHealth.setTextColor(getContext().getColor(R.color.white));
                        selfCare.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfCare.setTextColor(getContext().getColor(R.color.white));
                        addiction.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        addiction.setTextColor(getContext().getColor(R.color.white));
                        grief.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        grief.setTextColor(getContext().getColor(R.color.white));
                        anxiety.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        anxiety.setTextColor(getContext().getColor(R.color.white));
                        disabilities.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        disabilities.setTextColor(getContext().getColor(R.color.white));
                        depression.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        depression.setTextColor(getContext().getColor(R.color.white));
                        others.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        others.setTextColor(getContext().getColor(R.color.white));
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            PostModel postModel= dataSnapshot.getValue(PostModel.class);
                            postModel.setPostId(dataSnapshot.getKey());
                            if (!postModel.getPostedBy().equals(FirebaseAuth.getInstance().getUid())){
                                postList.add(postModel);
                            }
                        }
                        Collections.reverse(postList);
                        postAdapter.notifyDataSetChanged();
                        if(postList.isEmpty()){
                            constraintLayout.setVisibility(View.VISIBLE);
                        }else{
                            constraintLayout.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query= database.getReference().child("Posts").orderByChild("type").equalTo(friends.getText().toString());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        homeRV.setVisibility(View.VISIBLE);
                        postList.clear();
                        all.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        all.setTextColor(getContext().getColor(R.color.white));
                        family.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        family.setTextColor(getContext().getColor(R.color.white));
                        selfHarm.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfHarm.setTextColor(getContext().getColor(R.color.white));
                        friends.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.white));
                        friends.setTextColor(getContext().getColor(R.color.blue_bg));
                        hopes.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        hopes.setTextColor(getContext().getColor(R.color.white));
                        bullying.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        bullying.setTextColor(getContext().getColor(R.color.white));
                        health.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        health.setTextColor(getContext().getColor(R.color.white));
                        work.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        work.setTextColor(getContext().getColor(R.color.white));
                        parenting.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        parenting.setTextColor(getContext().getColor(R.color.white));
                        education.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        education.setTextColor(getContext().getColor(R.color.white));
                        religion.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        religion.setTextColor(getContext().getColor(R.color.white));
                        lgbt.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        lgbt.setTextColor(getContext().getColor(R.color.white));
                        positive.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        positive.setTextColor(getContext().getColor(R.color.white));
                        pregnancy.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        pregnancy.setTextColor(getContext().getColor(R.color.white));
                        mentalHealth.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        mentalHealth.setTextColor(getContext().getColor(R.color.white));
                        selfCare.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfCare.setTextColor(getContext().getColor(R.color.white));
                        addiction.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        addiction.setTextColor(getContext().getColor(R.color.white));
                        grief.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        grief.setTextColor(getContext().getColor(R.color.white));
                        anxiety.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        anxiety.setTextColor(getContext().getColor(R.color.white));
                        disabilities.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        disabilities.setTextColor(getContext().getColor(R.color.white));
                        depression.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        depression.setTextColor(getContext().getColor(R.color.white));
                        others.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        others.setTextColor(getContext().getColor(R.color.white));
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            PostModel postModel= dataSnapshot.getValue(PostModel.class);
                            postModel.setPostId(dataSnapshot.getKey());
                            if (!postModel.getPostedBy().equals(FirebaseAuth.getInstance().getUid())){
                                postList.add(postModel);
                            }
                        }
                        Collections.reverse(postList);
                        postAdapter.notifyDataSetChanged();
                        if(postList.isEmpty()){
                            constraintLayout.setVisibility(View.VISIBLE);
                        }else{
                            constraintLayout.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        hopes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query= database.getReference().child("Posts").orderByChild("type").equalTo(hopes.getText().toString());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        homeRV.setVisibility(View.VISIBLE);
                        postList.clear();
                        all.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        all.setTextColor(getContext().getColor(R.color.white));
                        family.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        family.setTextColor(getContext().getColor(R.color.white));
                        selfHarm.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfHarm.setTextColor(getContext().getColor(R.color.white));
                        friends.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        friends.setTextColor(getContext().getColor(R.color.white));
                        hopes.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.white));
                        hopes.setTextColor(getContext().getColor(R.color.blue_bg));
                        bullying.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        bullying.setTextColor(getContext().getColor(R.color.white));
                        health.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        health.setTextColor(getContext().getColor(R.color.white));
                        work.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        work.setTextColor(getContext().getColor(R.color.white));
                        parenting.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        parenting.setTextColor(getContext().getColor(R.color.white));
                        education.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        education.setTextColor(getContext().getColor(R.color.white));
                        religion.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        religion.setTextColor(getContext().getColor(R.color.white));
                        lgbt.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        lgbt.setTextColor(getContext().getColor(R.color.white));
                        positive.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        positive.setTextColor(getContext().getColor(R.color.white));
                        pregnancy.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        pregnancy.setTextColor(getContext().getColor(R.color.white));
                        mentalHealth.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        mentalHealth.setTextColor(getContext().getColor(R.color.white));
                        selfCare.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfCare.setTextColor(getContext().getColor(R.color.white));
                        addiction.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        addiction.setTextColor(getContext().getColor(R.color.white));
                        grief.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        grief.setTextColor(getContext().getColor(R.color.white));
                        anxiety.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        anxiety.setTextColor(getContext().getColor(R.color.white));
                        disabilities.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        disabilities.setTextColor(getContext().getColor(R.color.white));
                        depression.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        depression.setTextColor(getContext().getColor(R.color.white));
                        others.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        others.setTextColor(getContext().getColor(R.color.white));
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            PostModel postModel= dataSnapshot.getValue(PostModel.class);
                            postModel.setPostId(dataSnapshot.getKey());
                            if (!postModel.getPostedBy().equals(FirebaseAuth.getInstance().getUid())){
                                postList.add(postModel);
                            }
                        }
                        Collections.reverse(postList);
                        postAdapter.notifyDataSetChanged();
                        if(postList.isEmpty()){
                            constraintLayout.setVisibility(View.VISIBLE);
                        }else{
                            constraintLayout.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        bullying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query= database.getReference().child("Posts").orderByChild("type").equalTo(bullying.getText().toString());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        homeRV.setVisibility(View.VISIBLE);
                        postList.clear();
                        all.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        all.setTextColor(getContext().getColor(R.color.white));
                        family.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        family.setTextColor(getContext().getColor(R.color.white));
                        selfHarm.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfHarm.setTextColor(getContext().getColor(R.color.white));
                        friends.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        friends.setTextColor(getContext().getColor(R.color.white));
                        hopes.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        hopes.setTextColor(getContext().getColor(R.color.white));
                        bullying.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.white));
                        bullying.setTextColor(getContext().getColor(R.color.blue_bg));
                        health.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        health.setTextColor(getContext().getColor(R.color.white));
                        work.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        work.setTextColor(getContext().getColor(R.color.white));
                        parenting.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        parenting.setTextColor(getContext().getColor(R.color.white));
                        education.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        education.setTextColor(getContext().getColor(R.color.white));
                        religion.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        religion.setTextColor(getContext().getColor(R.color.white));
                        lgbt.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        lgbt.setTextColor(getContext().getColor(R.color.white));
                        positive.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        positive.setTextColor(getContext().getColor(R.color.white));
                        pregnancy.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        pregnancy.setTextColor(getContext().getColor(R.color.white));
                        mentalHealth.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        mentalHealth.setTextColor(getContext().getColor(R.color.white));
                        selfCare.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfCare.setTextColor(getContext().getColor(R.color.white));
                        addiction.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        addiction.setTextColor(getContext().getColor(R.color.white));
                        grief.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        grief.setTextColor(getContext().getColor(R.color.white));
                        anxiety.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        anxiety.setTextColor(getContext().getColor(R.color.white));
                        disabilities.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        disabilities.setTextColor(getContext().getColor(R.color.white));
                        depression.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        depression.setTextColor(getContext().getColor(R.color.white));
                        others.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        others.setTextColor(getContext().getColor(R.color.white));
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            PostModel postModel= dataSnapshot.getValue(PostModel.class);
                            postModel.setPostId(dataSnapshot.getKey());
                            if (!postModel.getPostedBy().equals(FirebaseAuth.getInstance().getUid())){
                                postList.add(postModel);
                            }
                        }
                        Collections.reverse(postList);
                        postAdapter.notifyDataSetChanged();
                        if(postList.isEmpty()){
                            constraintLayout.setVisibility(View.VISIBLE);
                        }else{
                            constraintLayout.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query= database.getReference().child("Posts").orderByChild("type").equalTo(health.getText().toString());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        homeRV.setVisibility(View.VISIBLE);
                        postList.clear();
                        all.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        all.setTextColor(getContext().getColor(R.color.white));
                        family.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        family.setTextColor(getContext().getColor(R.color.white));
                        selfHarm.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfHarm.setTextColor(getContext().getColor(R.color.white));
                        friends.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        friends.setTextColor(getContext().getColor(R.color.white));
                        hopes.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        hopes.setTextColor(getContext().getColor(R.color.white));
                        bullying.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        bullying.setTextColor(getContext().getColor(R.color.white));
                        health.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.white));
                        health.setTextColor(getContext().getColor(R.color.blue_bg));
                        work.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        work.setTextColor(getContext().getColor(R.color.white));
                        parenting.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        parenting.setTextColor(getContext().getColor(R.color.white));
                        education.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        education.setTextColor(getContext().getColor(R.color.white));
                        religion.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        religion.setTextColor(getContext().getColor(R.color.white));
                        lgbt.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        lgbt.setTextColor(getContext().getColor(R.color.white));
                        positive.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        positive.setTextColor(getContext().getColor(R.color.white));
                        pregnancy.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        pregnancy.setTextColor(getContext().getColor(R.color.white));
                        mentalHealth.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        mentalHealth.setTextColor(getContext().getColor(R.color.white));
                        selfCare.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfCare.setTextColor(getContext().getColor(R.color.white));
                        addiction.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        addiction.setTextColor(getContext().getColor(R.color.white));
                        grief.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        grief.setTextColor(getContext().getColor(R.color.white));
                        anxiety.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        anxiety.setTextColor(getContext().getColor(R.color.white));
                        disabilities.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        disabilities.setTextColor(getContext().getColor(R.color.white));
                        depression.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        depression.setTextColor(getContext().getColor(R.color.white));
                        others.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        others.setTextColor(getContext().getColor(R.color.white));
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            PostModel postModel= dataSnapshot.getValue(PostModel.class);
                            postModel.setPostId(dataSnapshot.getKey());
                            if (!postModel.getPostedBy().equals(FirebaseAuth.getInstance().getUid())){
                                postList.add(postModel);
                            }
                        }
                        Collections.reverse(postList);
                        postAdapter.notifyDataSetChanged();
                        if(postList.isEmpty()){
                            constraintLayout.setVisibility(View.VISIBLE);
                        }else{
                            constraintLayout.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query= database.getReference().child("Posts").orderByChild("type").equalTo(work.getText().toString());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        homeRV.setVisibility(View.VISIBLE);
                        postList.clear();
                        all.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        all.setTextColor(getContext().getColor(R.color.white));
                        family.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        family.setTextColor(getContext().getColor(R.color.white));
                        selfHarm.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfHarm.setTextColor(getContext().getColor(R.color.white));
                        friends.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        friends.setTextColor(getContext().getColor(R.color.white));
                        hopes.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        hopes.setTextColor(getContext().getColor(R.color.white));
                        bullying.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        bullying.setTextColor(getContext().getColor(R.color.white));
                        health.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        health.setTextColor(getContext().getColor(R.color.white));
                        work.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.white));
                        work.setTextColor(getContext().getColor(R.color.blue_bg));
                        parenting.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        parenting.setTextColor(getContext().getColor(R.color.white));
                        education.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        education.setTextColor(getContext().getColor(R.color.white));
                        religion.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        religion.setTextColor(getContext().getColor(R.color.white));
                        lgbt.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        lgbt.setTextColor(getContext().getColor(R.color.white));
                        positive.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        positive.setTextColor(getContext().getColor(R.color.white));
                        pregnancy.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        pregnancy.setTextColor(getContext().getColor(R.color.white));
                        mentalHealth.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        mentalHealth.setTextColor(getContext().getColor(R.color.white));
                        selfCare.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfCare.setTextColor(getContext().getColor(R.color.white));
                        addiction.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        addiction.setTextColor(getContext().getColor(R.color.white));
                        grief.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        grief.setTextColor(getContext().getColor(R.color.white));
                        anxiety.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        anxiety.setTextColor(getContext().getColor(R.color.white));
                        disabilities.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        disabilities.setTextColor(getContext().getColor(R.color.white));
                        depression.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        depression.setTextColor(getContext().getColor(R.color.white));
                        others.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        others.setTextColor(getContext().getColor(R.color.white));
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            PostModel postModel= dataSnapshot.getValue(PostModel.class);
                            postModel.setPostId(dataSnapshot.getKey());
                            if (!postModel.getPostedBy().equals(FirebaseAuth.getInstance().getUid())){
                                postList.add(postModel);
                            }
                        }
                        Collections.reverse(postList);
                        postAdapter.notifyDataSetChanged();
                        if(postList.isEmpty()){
                            constraintLayout.setVisibility(View.VISIBLE);
                        }else{
                            constraintLayout.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        parenting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query= database.getReference().child("Posts").orderByChild("type").equalTo(parenting.getText().toString());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        homeRV.setVisibility(View.VISIBLE);
                        postList.clear();
                        all.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        all.setTextColor(getContext().getColor(R.color.white));
                        family.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        family.setTextColor(getContext().getColor(R.color.white));
                        selfHarm.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfHarm.setTextColor(getContext().getColor(R.color.white));
                        friends.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        friends.setTextColor(getContext().getColor(R.color.white));
                        hopes.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        hopes.setTextColor(getContext().getColor(R.color.white));
                        bullying.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        bullying.setTextColor(getContext().getColor(R.color.white));
                        health.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        health.setTextColor(getContext().getColor(R.color.white));
                        work.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        work.setTextColor(getContext().getColor(R.color.white));
                        parenting.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.white));
                        parenting.setTextColor(getContext().getColor(R.color.blue_bg));
                        education.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        education.setTextColor(getContext().getColor(R.color.white));
                        religion.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        religion.setTextColor(getContext().getColor(R.color.white));
                        lgbt.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        lgbt.setTextColor(getContext().getColor(R.color.white));
                        positive.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        positive.setTextColor(getContext().getColor(R.color.white));
                        pregnancy.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        pregnancy.setTextColor(getContext().getColor(R.color.white));
                        mentalHealth.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        mentalHealth.setTextColor(getContext().getColor(R.color.white));
                        selfCare.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfCare.setTextColor(getContext().getColor(R.color.white));
                        addiction.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        addiction.setTextColor(getContext().getColor(R.color.white));
                        grief.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        grief.setTextColor(getContext().getColor(R.color.white));
                        anxiety.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        anxiety.setTextColor(getContext().getColor(R.color.white));
                        disabilities.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        disabilities.setTextColor(getContext().getColor(R.color.white));
                        depression.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        depression.setTextColor(getContext().getColor(R.color.white));
                        others.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        others.setTextColor(getContext().getColor(R.color.white));
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            PostModel postModel= dataSnapshot.getValue(PostModel.class);
                            postModel.setPostId(dataSnapshot.getKey());
                            if (!postModel.getPostedBy().equals(FirebaseAuth.getInstance().getUid())){
                                postList.add(postModel);
                            }
                        }
                        Collections.reverse(postList);
                        postAdapter.notifyDataSetChanged();
                        if(postList.isEmpty()){
                            constraintLayout.setVisibility(View.VISIBLE);
                        }else{
                            constraintLayout.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query= database.getReference().child("Posts").orderByChild("type").equalTo(education.getText().toString());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        homeRV.setVisibility(View.VISIBLE);
                        postList.clear();
                        all.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        all.setTextColor(getContext().getColor(R.color.white));
                        family.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        family.setTextColor(getContext().getColor(R.color.white));
                        selfHarm.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfHarm.setTextColor(getContext().getColor(R.color.white));
                        friends.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        friends.setTextColor(getContext().getColor(R.color.white));
                        hopes.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        hopes.setTextColor(getContext().getColor(R.color.white));
                        bullying.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        bullying.setTextColor(getContext().getColor(R.color.white));
                        health.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        health.setTextColor(getContext().getColor(R.color.white));
                        work.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        work.setTextColor(getContext().getColor(R.color.white));
                        parenting.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        parenting.setTextColor(getContext().getColor(R.color.white));
                        education.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.white));
                        education.setTextColor(getContext().getColor(R.color.blue_bg));
                        religion.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        religion.setTextColor(getContext().getColor(R.color.white));
                        lgbt.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        lgbt.setTextColor(getContext().getColor(R.color.white));
                        positive.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        positive.setTextColor(getContext().getColor(R.color.white));
                        pregnancy.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        pregnancy.setTextColor(getContext().getColor(R.color.white));
                        mentalHealth.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        mentalHealth.setTextColor(getContext().getColor(R.color.white));
                        selfCare.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfCare.setTextColor(getContext().getColor(R.color.white));
                        addiction.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        addiction.setTextColor(getContext().getColor(R.color.white));
                        grief.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        grief.setTextColor(getContext().getColor(R.color.white));
                        anxiety.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        anxiety.setTextColor(getContext().getColor(R.color.white));
                        disabilities.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        disabilities.setTextColor(getContext().getColor(R.color.white));
                        depression.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        depression.setTextColor(getContext().getColor(R.color.white));
                        others.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        others.setTextColor(getContext().getColor(R.color.white));
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            PostModel postModel= dataSnapshot.getValue(PostModel.class);
                            postModel.setPostId(dataSnapshot.getKey());
                            if (!postModel.getPostedBy().equals(FirebaseAuth.getInstance().getUid())){
                                postList.add(postModel);
                            }
                        }
                        Collections.reverse(postList);
                        postAdapter.notifyDataSetChanged();
                        if(postList.isEmpty()){
                            constraintLayout.setVisibility(View.VISIBLE);
                        }else{
                            constraintLayout.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        religion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query= database.getReference().child("Posts").orderByChild("type").equalTo(religion.getText().toString());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        homeRV.setVisibility(View.VISIBLE);
                        postList.clear();
                        all.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        all.setTextColor(getContext().getColor(R.color.white));
                        family.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        family.setTextColor(getContext().getColor(R.color.white));
                        selfHarm.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfHarm.setTextColor(getContext().getColor(R.color.white));
                        friends.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        friends.setTextColor(getContext().getColor(R.color.white));
                        hopes.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        hopes.setTextColor(getContext().getColor(R.color.white));
                        bullying.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        bullying.setTextColor(getContext().getColor(R.color.white));
                        health.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        health.setTextColor(getContext().getColor(R.color.white));
                        work.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        work.setTextColor(getContext().getColor(R.color.white));
                        parenting.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        parenting.setTextColor(getContext().getColor(R.color.white));
                        education.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        education.setTextColor(getContext().getColor(R.color.white));
                        religion.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.white));
                        religion.setTextColor(getContext().getColor(R.color.blue_bg));
                        lgbt.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        lgbt.setTextColor(getContext().getColor(R.color.white));
                        positive.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        positive.setTextColor(getContext().getColor(R.color.white));
                        pregnancy.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        pregnancy.setTextColor(getContext().getColor(R.color.white));
                        mentalHealth.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        mentalHealth.setTextColor(getContext().getColor(R.color.white));
                        selfCare.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfCare.setTextColor(getContext().getColor(R.color.white));
                        addiction.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        addiction.setTextColor(getContext().getColor(R.color.white));
                        grief.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        grief.setTextColor(getContext().getColor(R.color.white));
                        anxiety.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        anxiety.setTextColor(getContext().getColor(R.color.white));
                        disabilities.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        disabilities.setTextColor(getContext().getColor(R.color.white));
                        depression.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        depression.setTextColor(getContext().getColor(R.color.white));
                        others.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        others.setTextColor(getContext().getColor(R.color.white));
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            PostModel postModel= dataSnapshot.getValue(PostModel.class);
                            postModel.setPostId(dataSnapshot.getKey());
                            if (!postModel.getPostedBy().equals(FirebaseAuth.getInstance().getUid())){
                                postList.add(postModel);
                            }
                        }
                        Collections.reverse(postList);
                        postAdapter.notifyDataSetChanged();
                        if(postList.isEmpty()){
                            constraintLayout.setVisibility(View.VISIBLE);
                        }else{
                            constraintLayout.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        lgbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query= database.getReference().child("Posts").orderByChild("type").equalTo(lgbt.getText().toString());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        homeRV.setVisibility(View.VISIBLE);
                        postList.clear();
                        all.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        all.setTextColor(getContext().getColor(R.color.white));
                        family.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        family.setTextColor(getContext().getColor(R.color.white));
                        selfHarm.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfHarm.setTextColor(getContext().getColor(R.color.white));
                        friends.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        friends.setTextColor(getContext().getColor(R.color.white));
                        hopes.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        hopes.setTextColor(getContext().getColor(R.color.white));
                        bullying.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        bullying.setTextColor(getContext().getColor(R.color.white));
                        health.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        health.setTextColor(getContext().getColor(R.color.white));
                        work.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        work.setTextColor(getContext().getColor(R.color.white));
                        parenting.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        parenting.setTextColor(getContext().getColor(R.color.white));
                        education.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        education.setTextColor(getContext().getColor(R.color.white));
                        religion.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        religion.setTextColor(getContext().getColor(R.color.white));
                        lgbt.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.white));
                        lgbt.setTextColor(getContext().getColor(R.color.blue_bg));
                        positive.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        positive.setTextColor(getContext().getColor(R.color.white));
                        pregnancy.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        pregnancy.setTextColor(getContext().getColor(R.color.white));
                        mentalHealth.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        mentalHealth.setTextColor(getContext().getColor(R.color.white));
                        selfCare.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfCare.setTextColor(getContext().getColor(R.color.white));
                        addiction.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        addiction.setTextColor(getContext().getColor(R.color.white));
                        grief.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        grief.setTextColor(getContext().getColor(R.color.white));
                        anxiety.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        anxiety.setTextColor(getContext().getColor(R.color.white));
                        disabilities.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        disabilities.setTextColor(getContext().getColor(R.color.white));
                        depression.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        depression.setTextColor(getContext().getColor(R.color.white));
                        others.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        others.setTextColor(getContext().getColor(R.color.white));
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            PostModel postModel= dataSnapshot.getValue(PostModel.class);
                            postModel.setPostId(dataSnapshot.getKey());
                            if (!postModel.getPostedBy().equals(FirebaseAuth.getInstance().getUid())){
                                postList.add(postModel);
                            }
                        }
                        Collections.reverse(postList);
                        postAdapter.notifyDataSetChanged();
                        if(postList.isEmpty()){
                            constraintLayout.setVisibility(View.VISIBLE);
                        }else{
                            constraintLayout.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query= database.getReference().child("Posts").orderByChild("type").equalTo(positive.getText().toString());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        homeRV.setVisibility(View.VISIBLE);
                        postList.clear();
                        all.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        all.setTextColor(getContext().getColor(R.color.white));
                        family.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        family.setTextColor(getContext().getColor(R.color.white));
                        selfHarm.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfHarm.setTextColor(getContext().getColor(R.color.white));
                        friends.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        friends.setTextColor(getContext().getColor(R.color.white));
                        hopes.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        hopes.setTextColor(getContext().getColor(R.color.white));
                        bullying.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        bullying.setTextColor(getContext().getColor(R.color.white));
                        health.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        health.setTextColor(getContext().getColor(R.color.white));
                        work.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        work.setTextColor(getContext().getColor(R.color.white));
                        parenting.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        parenting.setTextColor(getContext().getColor(R.color.white));
                        education.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        education.setTextColor(getContext().getColor(R.color.white));
                        religion.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        religion.setTextColor(getContext().getColor(R.color.white));
                        lgbt.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        lgbt.setTextColor(getContext().getColor(R.color.white));
                        positive.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.white));
                        positive.setTextColor(getContext().getColor(R.color.blue_bg));
                        pregnancy.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        pregnancy.setTextColor(getContext().getColor(R.color.white));
                        mentalHealth.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        mentalHealth.setTextColor(getContext().getColor(R.color.white));
                        selfCare.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfCare.setTextColor(getContext().getColor(R.color.white));
                        addiction.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        addiction.setTextColor(getContext().getColor(R.color.white));
                        grief.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        grief.setTextColor(getContext().getColor(R.color.white));
                        anxiety.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        anxiety.setTextColor(getContext().getColor(R.color.white));
                        disabilities.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        disabilities.setTextColor(getContext().getColor(R.color.white));
                        depression.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        depression.setTextColor(getContext().getColor(R.color.white));
                        others.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        others.setTextColor(getContext().getColor(R.color.white));
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            PostModel postModel= dataSnapshot.getValue(PostModel.class);
                            postModel.setPostId(dataSnapshot.getKey());
                            if (!postModel.getPostedBy().equals(FirebaseAuth.getInstance().getUid())){
                                postList.add(postModel);
                            }
                        }
                        Collections.reverse(postList);
                        postAdapter.notifyDataSetChanged();
                        if(postList.isEmpty()){
                            constraintLayout.setVisibility(View.VISIBLE);
                        }else{
                            constraintLayout.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        pregnancy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query= database.getReference().child("Posts").orderByChild("type").equalTo(pregnancy.getText().toString());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        homeRV.setVisibility(View.VISIBLE);
                        postList.clear();
                        all.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        all.setTextColor(getContext().getColor(R.color.white));
                        family.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        family.setTextColor(getContext().getColor(R.color.white));
                        selfHarm.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfHarm.setTextColor(getContext().getColor(R.color.white));
                        friends.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        friends.setTextColor(getContext().getColor(R.color.white));
                        hopes.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        hopes.setTextColor(getContext().getColor(R.color.white));
                        bullying.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        bullying.setTextColor(getContext().getColor(R.color.white));
                        health.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        health.setTextColor(getContext().getColor(R.color.white));
                        work.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        work.setTextColor(getContext().getColor(R.color.white));
                        parenting.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        parenting.setTextColor(getContext().getColor(R.color.white));
                        education.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        education.setTextColor(getContext().getColor(R.color.white));
                        religion.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        religion.setTextColor(getContext().getColor(R.color.white));
                        lgbt.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        lgbt.setTextColor(getContext().getColor(R.color.white));
                        positive.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        positive.setTextColor(getContext().getColor(R.color.white));
                        pregnancy.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.white));
                        pregnancy.setTextColor(getContext().getColor(R.color.blue_bg));
                        mentalHealth.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        mentalHealth.setTextColor(getContext().getColor(R.color.white));
                        selfCare.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfCare.setTextColor(getContext().getColor(R.color.white));
                        addiction.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        addiction.setTextColor(getContext().getColor(R.color.white));
                        grief.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        grief.setTextColor(getContext().getColor(R.color.white));
                        anxiety.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        anxiety.setTextColor(getContext().getColor(R.color.white));
                        disabilities.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        disabilities.setTextColor(getContext().getColor(R.color.white));
                        depression.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        depression.setTextColor(getContext().getColor(R.color.white));
                        others.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        others.setTextColor(getContext().getColor(R.color.white));
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            PostModel postModel= dataSnapshot.getValue(PostModel.class);
                            postModel.setPostId(dataSnapshot.getKey());
                            if (!postModel.getPostedBy().equals(FirebaseAuth.getInstance().getUid())){
                                postList.add(postModel);
                            }
                        }
                        Collections.reverse(postList);
                        postAdapter.notifyDataSetChanged();
                        if(postList.isEmpty()){
                            constraintLayout.setVisibility(View.VISIBLE);
                        }else{
                            constraintLayout.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        mentalHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query= database.getReference().child("Posts").orderByChild("type").equalTo(mentalHealth.getText().toString());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        homeRV.setVisibility(View.VISIBLE);
                        postList.clear();
                        all.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        all.setTextColor(getContext().getColor(R.color.white));
                        family.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        family.setTextColor(getContext().getColor(R.color.white));
                        selfHarm.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfHarm.setTextColor(getContext().getColor(R.color.white));
                        friends.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        friends.setTextColor(getContext().getColor(R.color.white));
                        hopes.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        hopes.setTextColor(getContext().getColor(R.color.white));
                        bullying.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        bullying.setTextColor(getContext().getColor(R.color.white));
                        health.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        health.setTextColor(getContext().getColor(R.color.white));
                        work.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        work.setTextColor(getContext().getColor(R.color.white));
                        parenting.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        parenting.setTextColor(getContext().getColor(R.color.white));
                        education.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        education.setTextColor(getContext().getColor(R.color.white));
                        religion.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        religion.setTextColor(getContext().getColor(R.color.white));
                        lgbt.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        lgbt.setTextColor(getContext().getColor(R.color.white));
                        positive.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        positive.setTextColor(getContext().getColor(R.color.white));
                        pregnancy.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        pregnancy.setTextColor(getContext().getColor(R.color.white));
                        mentalHealth.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.white));
                        mentalHealth.setTextColor(getContext().getColor(R.color.blue_bg));
                        selfCare.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfCare.setTextColor(getContext().getColor(R.color.white));
                        addiction.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        addiction.setTextColor(getContext().getColor(R.color.white));
                        grief.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        grief.setTextColor(getContext().getColor(R.color.white));
                        anxiety.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        anxiety.setTextColor(getContext().getColor(R.color.white));
                        disabilities.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        disabilities.setTextColor(getContext().getColor(R.color.white));
                        depression.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        depression.setTextColor(getContext().getColor(R.color.white));
                        others.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        others.setTextColor(getContext().getColor(R.color.white));
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            PostModel postModel= dataSnapshot.getValue(PostModel.class);
                            postModel.setPostId(dataSnapshot.getKey());
                            if (!postModel.getPostedBy().equals(FirebaseAuth.getInstance().getUid())){
                                postList.add(postModel);
                            }
                        }
                        Collections.reverse(postList);
                        postAdapter.notifyDataSetChanged();
                        if(postList.isEmpty()){
                            constraintLayout.setVisibility(View.VISIBLE);
                        }else{
                            constraintLayout.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        addiction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query= database.getReference().child("Posts").orderByChild("type").equalTo(addiction.getText().toString());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        homeRV.setVisibility(View.VISIBLE);
                        postList.clear();
                        all.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        all.setTextColor(getContext().getColor(R.color.white));
                        family.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        family.setTextColor(getContext().getColor(R.color.white));
                        selfHarm.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfHarm.setTextColor(getContext().getColor(R.color.white));
                        friends.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        friends.setTextColor(getContext().getColor(R.color.white));
                        hopes.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        hopes.setTextColor(getContext().getColor(R.color.white));
                        bullying.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        bullying.setTextColor(getContext().getColor(R.color.white));
                        health.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        health.setTextColor(getContext().getColor(R.color.white));
                        work.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        work.setTextColor(getContext().getColor(R.color.white));
                        parenting.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        parenting.setTextColor(getContext().getColor(R.color.white));
                        education.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        education.setTextColor(getContext().getColor(R.color.white));
                        religion.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        religion.setTextColor(getContext().getColor(R.color.white));
                        lgbt.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        lgbt.setTextColor(getContext().getColor(R.color.white));
                        positive.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        positive.setTextColor(getContext().getColor(R.color.white));
                        pregnancy.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        pregnancy.setTextColor(getContext().getColor(R.color.white));
                        mentalHealth.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        mentalHealth.setTextColor(getContext().getColor(R.color.white));
                        selfCare.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfCare.setTextColor(getContext().getColor(R.color.white));
                        addiction.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.white));
                        addiction.setTextColor(getContext().getColor(R.color.blue_bg));
                        grief.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        grief.setTextColor(getContext().getColor(R.color.white));
                        anxiety.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        anxiety.setTextColor(getContext().getColor(R.color.white));
                        disabilities.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        disabilities.setTextColor(getContext().getColor(R.color.white));
                        depression.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        depression.setTextColor(getContext().getColor(R.color.white));
                        others.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        others.setTextColor(getContext().getColor(R.color.white));
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            PostModel postModel= dataSnapshot.getValue(PostModel.class);
                            postModel.setPostId(dataSnapshot.getKey());
                            if (!postModel.getPostedBy().equals(FirebaseAuth.getInstance().getUid())){
                                postList.add(postModel);
                            }
                        }
                        Collections.reverse(postList);
                        postAdapter.notifyDataSetChanged();
                        if(postList.isEmpty()){
                            constraintLayout.setVisibility(View.VISIBLE);
                        }else{
                            constraintLayout.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        selfCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query= database.getReference().child("Posts").orderByChild("type").equalTo(selfCare.getText().toString());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        homeRV.setVisibility(View.VISIBLE);
                        postList.clear();
                        all.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        all.setTextColor(getContext().getColor(R.color.white));
                        family.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        family.setTextColor(getContext().getColor(R.color.white));
                        selfHarm.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfHarm.setTextColor(getContext().getColor(R.color.white));
                        friends.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        friends.setTextColor(getContext().getColor(R.color.white));
                        hopes.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        hopes.setTextColor(getContext().getColor(R.color.white));
                        bullying.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        bullying.setTextColor(getContext().getColor(R.color.white));
                        health.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        health.setTextColor(getContext().getColor(R.color.white));
                        work.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        work.setTextColor(getContext().getColor(R.color.white));
                        parenting.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        parenting.setTextColor(getContext().getColor(R.color.white));
                        education.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        education.setTextColor(getContext().getColor(R.color.white));
                        religion.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        religion.setTextColor(getContext().getColor(R.color.white));
                        lgbt.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        lgbt.setTextColor(getContext().getColor(R.color.white));
                        positive.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        positive.setTextColor(getContext().getColor(R.color.white));
                        pregnancy.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        pregnancy.setTextColor(getContext().getColor(R.color.white));
                        mentalHealth.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        mentalHealth.setTextColor(getContext().getColor(R.color.white));
                        selfCare.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.white));
                        selfCare.setTextColor(getContext().getColor(R.color.blue_bg));
                        addiction.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        addiction.setTextColor(getContext().getColor(R.color.white));
                        grief.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        grief.setTextColor(getContext().getColor(R.color.white));
                        anxiety.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        anxiety.setTextColor(getContext().getColor(R.color.white));
                        disabilities.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        disabilities.setTextColor(getContext().getColor(R.color.white));
                        depression.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        depression.setTextColor(getContext().getColor(R.color.white));
                        others.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        others.setTextColor(getContext().getColor(R.color.white));
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            PostModel postModel= dataSnapshot.getValue(PostModel.class);
                            postModel.setPostId(dataSnapshot.getKey());
                            if (!postModel.getPostedBy().equals(FirebaseAuth.getInstance().getUid())){
                                postList.add(postModel);
                            }
                        }
                        Collections.reverse(postList);
                        postAdapter.notifyDataSetChanged();
                        if(postList.isEmpty()){
                            constraintLayout.setVisibility(View.VISIBLE);
                        }else{
                            constraintLayout.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        grief.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query= database.getReference().child("Posts").orderByChild("type").equalTo(grief.getText().toString());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        homeRV.setVisibility(View.VISIBLE);
                        postList.clear();
                        all.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        all.setTextColor(getContext().getColor(R.color.white));
                        family.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        family.setTextColor(getContext().getColor(R.color.white));
                        selfHarm.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfHarm.setTextColor(getContext().getColor(R.color.white));
                        friends.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        friends.setTextColor(getContext().getColor(R.color.white));
                        hopes.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        hopes.setTextColor(getContext().getColor(R.color.white));
                        bullying.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        bullying.setTextColor(getContext().getColor(R.color.white));
                        health.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        health.setTextColor(getContext().getColor(R.color.white));
                        work.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        work.setTextColor(getContext().getColor(R.color.white));
                        parenting.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        parenting.setTextColor(getContext().getColor(R.color.white));
                        education.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        education.setTextColor(getContext().getColor(R.color.white));
                        religion.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        religion.setTextColor(getContext().getColor(R.color.white));
                        lgbt.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        lgbt.setTextColor(getContext().getColor(R.color.white));
                        positive.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        positive.setTextColor(getContext().getColor(R.color.white));
                        pregnancy.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        pregnancy.setTextColor(getContext().getColor(R.color.white));
                        mentalHealth.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        mentalHealth.setTextColor(getContext().getColor(R.color.white));
                        selfCare.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfCare.setTextColor(getContext().getColor(R.color.white));
                        addiction.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        addiction.setTextColor(getContext().getColor(R.color.white));
                        grief.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.white));
                        grief.setTextColor(getContext().getColor(R.color.blue_bg));
                        anxiety.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        anxiety.setTextColor(getContext().getColor(R.color.white));
                        disabilities.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        disabilities.setTextColor(getContext().getColor(R.color.white));
                        depression.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        depression.setTextColor(getContext().getColor(R.color.white));
                        others.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        others.setTextColor(getContext().getColor(R.color.white));
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            PostModel postModel= dataSnapshot.getValue(PostModel.class);
                            postModel.setPostId(dataSnapshot.getKey());
                            if (!postModel.getPostedBy().equals(FirebaseAuth.getInstance().getUid())){
                                postList.add(postModel);
                            }
                        }
                        Collections.reverse(postList);
                        postAdapter.notifyDataSetChanged();
                        if(postList.isEmpty()){
                            constraintLayout.setVisibility(View.VISIBLE);
                        }else{
                            constraintLayout.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        anxiety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query= database.getReference().child("Posts").orderByChild("type").equalTo(anxiety.getText().toString());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        homeRV.setVisibility(View.VISIBLE);
                        postList.clear();
                        all.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        all.setTextColor(getContext().getColor(R.color.white));
                        family.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        family.setTextColor(getContext().getColor(R.color.white));
                        selfHarm.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfHarm.setTextColor(getContext().getColor(R.color.white));
                        friends.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        friends.setTextColor(getContext().getColor(R.color.white));
                        hopes.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        hopes.setTextColor(getContext().getColor(R.color.white));
                        bullying.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        bullying.setTextColor(getContext().getColor(R.color.white));
                        health.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        health.setTextColor(getContext().getColor(R.color.white));
                        work.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        work.setTextColor(getContext().getColor(R.color.white));
                        parenting.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        parenting.setTextColor(getContext().getColor(R.color.white));
                        education.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        education.setTextColor(getContext().getColor(R.color.white));
                        religion.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        religion.setTextColor(getContext().getColor(R.color.white));
                        lgbt.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        lgbt.setTextColor(getContext().getColor(R.color.white));
                        positive.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        positive.setTextColor(getContext().getColor(R.color.white));
                        pregnancy.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        pregnancy.setTextColor(getContext().getColor(R.color.white));
                        mentalHealth.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        mentalHealth.setTextColor(getContext().getColor(R.color.white));
                        selfCare.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfCare.setTextColor(getContext().getColor(R.color.white));
                        addiction.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        addiction.setTextColor(getContext().getColor(R.color.white));
                        grief.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        grief.setTextColor(getContext().getColor(R.color.white));
                        anxiety.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.white));
                        anxiety.setTextColor(getContext().getColor(R.color.blue_bg));
                        disabilities.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        disabilities.setTextColor(getContext().getColor(R.color.white));
                        depression.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        depression.setTextColor(getContext().getColor(R.color.white));
                        others.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        others.setTextColor(getContext().getColor(R.color.white));
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            PostModel postModel= dataSnapshot.getValue(PostModel.class);
                            postModel.setPostId(dataSnapshot.getKey());
                            if (!postModel.getPostedBy().equals(FirebaseAuth.getInstance().getUid())){
                                postList.add(postModel);
                            }
                        }
                        Collections.reverse(postList);
                        postAdapter.notifyDataSetChanged();
                        if(postList.isEmpty()){
                            constraintLayout.setVisibility(View.VISIBLE);
                        }else{
                            constraintLayout.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        disabilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query= database.getReference().child("Posts").orderByChild("type").equalTo(disabilities.getText().toString());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        homeRV.setVisibility(View.VISIBLE);
                        postList.clear();
                        all.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        all.setTextColor(getContext().getColor(R.color.white));
                        family.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        family.setTextColor(getContext().getColor(R.color.white));
                        selfHarm.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfHarm.setTextColor(getContext().getColor(R.color.white));
                        friends.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        friends.setTextColor(getContext().getColor(R.color.white));
                        hopes.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        hopes.setTextColor(getContext().getColor(R.color.white));
                        bullying.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        bullying.setTextColor(getContext().getColor(R.color.white));
                        health.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        health.setTextColor(getContext().getColor(R.color.white));
                        work.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        work.setTextColor(getContext().getColor(R.color.white));
                        parenting.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        parenting.setTextColor(getContext().getColor(R.color.white));
                        education.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        education.setTextColor(getContext().getColor(R.color.white));
                        religion.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        religion.setTextColor(getContext().getColor(R.color.white));
                        lgbt.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        lgbt.setTextColor(getContext().getColor(R.color.white));
                        positive.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        positive.setTextColor(getContext().getColor(R.color.white));
                        pregnancy.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        pregnancy.setTextColor(getContext().getColor(R.color.white));
                        mentalHealth.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        mentalHealth.setTextColor(getContext().getColor(R.color.white));
                        selfCare.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfCare.setTextColor(getContext().getColor(R.color.white));
                        addiction.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        addiction.setTextColor(getContext().getColor(R.color.white));
                        grief.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        grief.setTextColor(getContext().getColor(R.color.white));
                        anxiety.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        anxiety.setTextColor(getContext().getColor(R.color.white));
                        disabilities.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.white));
                        disabilities.setTextColor(getContext().getColor(R.color.blue_bg));
                        depression.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        depression.setTextColor(getContext().getColor(R.color.white));
                        others.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        others.setTextColor(getContext().getColor(R.color.white));
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            PostModel postModel= dataSnapshot.getValue(PostModel.class);
                            postModel.setPostId(dataSnapshot.getKey());
                            if (!postModel.getPostedBy().equals(FirebaseAuth.getInstance().getUid())){
                                postList.add(postModel);
                            }
                        }
                        Collections.reverse(postList);
                        postAdapter.notifyDataSetChanged();
                        if(postList.isEmpty()){
                            constraintLayout.setVisibility(View.VISIBLE);
                        }else{
                            constraintLayout.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        depression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query= database.getReference().child("Posts").orderByChild("type").equalTo(depression.getText().toString());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        homeRV.setVisibility(View.VISIBLE);
                        postList.clear();
                        all.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        all.setTextColor(getContext().getColor(R.color.white));
                        family.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        family.setTextColor(getContext().getColor(R.color.white));
                        selfHarm.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfHarm.setTextColor(getContext().getColor(R.color.white));
                        friends.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        friends.setTextColor(getContext().getColor(R.color.white));
                        hopes.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        hopes.setTextColor(getContext().getColor(R.color.white));
                        bullying.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        bullying.setTextColor(getContext().getColor(R.color.white));
                        health.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        health.setTextColor(getContext().getColor(R.color.white));
                        work.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        work.setTextColor(getContext().getColor(R.color.white));
                        parenting.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        parenting.setTextColor(getContext().getColor(R.color.white));
                        education.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        education.setTextColor(getContext().getColor(R.color.white));
                        religion.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        religion.setTextColor(getContext().getColor(R.color.white));
                        lgbt.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        lgbt.setTextColor(getContext().getColor(R.color.white));
                        positive.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        positive.setTextColor(getContext().getColor(R.color.white));
                        pregnancy.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        pregnancy.setTextColor(getContext().getColor(R.color.white));
                        mentalHealth.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        mentalHealth.setTextColor(getContext().getColor(R.color.white));
                        selfCare.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfCare.setTextColor(getContext().getColor(R.color.white));
                        addiction.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        addiction.setTextColor(getContext().getColor(R.color.white));
                        grief.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        grief.setTextColor(getContext().getColor(R.color.white));
                        anxiety.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        anxiety.setTextColor(getContext().getColor(R.color.white));
                        disabilities.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        disabilities.setTextColor(getContext().getColor(R.color.white));
                        depression.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.white));
                        depression.setTextColor(getContext().getColor(R.color.blue_bg));
                        others.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        others.setTextColor(getContext().getColor(R.color.white));
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            PostModel postModel= dataSnapshot.getValue(PostModel.class);
                            postModel.setPostId(dataSnapshot.getKey());
                            if (!postModel.getPostedBy().equals(FirebaseAuth.getInstance().getUid())){
                                postList.add(postModel);
                            }
                        }
                        Collections.reverse(postList);
                        postAdapter.notifyDataSetChanged();
                        if(postList.isEmpty()){
                            constraintLayout.setVisibility(View.VISIBLE);
                        }else{
                            constraintLayout.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query= database.getReference().child("Posts").orderByChild("type").equalTo(others.getText().toString());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        homeRV.setVisibility(View.VISIBLE);
                        postList.clear();
                        all.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        all.setTextColor(getContext().getColor(R.color.white));
                        family.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        family.setTextColor(getContext().getColor(R.color.white));
                        selfHarm.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfHarm.setTextColor(getContext().getColor(R.color.white));
                        friends.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        friends.setTextColor(getContext().getColor(R.color.white));
                        hopes.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        hopes.setTextColor(getContext().getColor(R.color.white));
                        bullying.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        bullying.setTextColor(getContext().getColor(R.color.white));
                        health.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        health.setTextColor(getContext().getColor(R.color.white));
                        work.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        work.setTextColor(getContext().getColor(R.color.white));
                        parenting.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        parenting.setTextColor(getContext().getColor(R.color.white));
                        education.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        education.setTextColor(getContext().getColor(R.color.white));
                        religion.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        religion.setTextColor(getContext().getColor(R.color.white));
                        lgbt.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        lgbt.setTextColor(getContext().getColor(R.color.white));
                        positive.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        positive.setTextColor(getContext().getColor(R.color.white));
                        pregnancy.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        pregnancy.setTextColor(getContext().getColor(R.color.white));
                        mentalHealth.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        mentalHealth.setTextColor(getContext().getColor(R.color.white));
                        selfCare.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfCare.setTextColor(getContext().getColor(R.color.white));
                        addiction.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        addiction.setTextColor(getContext().getColor(R.color.white));
                        grief.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        grief.setTextColor(getContext().getColor(R.color.white));
                        anxiety.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        anxiety.setTextColor(getContext().getColor(R.color.white));
                        disabilities.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        disabilities.setTextColor(getContext().getColor(R.color.white));
                        depression.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        depression.setTextColor(getContext().getColor(R.color.white));
                        others.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.white));
                        others.setTextColor(getContext().getColor(R.color.blue_bg));
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            PostModel postModel= dataSnapshot.getValue(PostModel.class);
                            postModel.setPostId(dataSnapshot.getKey());
                            if (!postModel.getPostedBy().equals(FirebaseAuth.getInstance().getUid())){
                                postList.add(postModel);
                            }
                        }
                        Collections.reverse(postList);
                        postAdapter.notifyDataSetChanged();
                        if(postList.isEmpty()){
                            constraintLayout.setVisibility(View.VISIBLE);
                        }else{
                            constraintLayout.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.getReference().child("Posts").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        homeRV.setVisibility(View.VISIBLE);
                        postList.clear();
                        all.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.white));
                        all.setTextColor(getContext().getColor(R.color.blue_bg));
                        family.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        family.setTextColor(getContext().getColor(R.color.white));
                        selfHarm.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfHarm.setTextColor(getContext().getColor(R.color.white));
                        friends.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        friends.setTextColor(getContext().getColor(R.color.white));
                        hopes.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        hopes.setTextColor(getContext().getColor(R.color.white));
                        bullying.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        bullying.setTextColor(getContext().getColor(R.color.white));
                        health.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        health.setTextColor(getContext().getColor(R.color.white));
                        work.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        work.setTextColor(getContext().getColor(R.color.white));
                        parenting.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        parenting.setTextColor(getContext().getColor(R.color.white));
                        education.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        education.setTextColor(getContext().getColor(R.color.white));
                        religion.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        religion.setTextColor(getContext().getColor(R.color.white));
                        lgbt.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        lgbt.setTextColor(getContext().getColor(R.color.white));
                        positive.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        positive.setTextColor(getContext().getColor(R.color.white));
                        pregnancy.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        pregnancy.setTextColor(getContext().getColor(R.color.white));
                        mentalHealth.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        mentalHealth.setTextColor(getContext().getColor(R.color.white));
                        selfCare.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        selfCare.setTextColor(getContext().getColor(R.color.white));
                        addiction.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        addiction.setTextColor(getContext().getColor(R.color.white));
                        grief.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        grief.setTextColor(getContext().getColor(R.color.white));
                        anxiety.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        anxiety.setTextColor(getContext().getColor(R.color.white));
                        disabilities.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        disabilities.setTextColor(getContext().getColor(R.color.white));
                        depression.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        depression.setTextColor(getContext().getColor(R.color.white));
                        others.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                        others.setTextColor(getContext().getColor(R.color.white));
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            PostModel postModel= dataSnapshot.getValue(PostModel.class);
                            postModel.setPostId(dataSnapshot.getKey());
                            if (!postModel.getPostedBy().equals(FirebaseAuth.getInstance().getUid())){
                                postList.add(postModel);
                            }
                        }
                        Collections.reverse(postList);
                        postAdapter.notifyDataSetChanged();
                        if(postList.isEmpty()){
                            constraintLayout.setVisibility(View.VISIBLE);
                        }else{
                            constraintLayout.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        return view;
    }
}