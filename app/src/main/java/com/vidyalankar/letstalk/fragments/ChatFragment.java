package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.adapter.ChatListAdapter;
import com.vidyalankar.letstalk.model.FollowingModel;
import com.vidyalankar.letstalk.model.FriendsModel;

import java.util.ArrayList;
import java.util.Collections;

public class ChatFragment extends Fragment {

    RecyclerView chatRV;
    ArrayList<FollowingModel> friendList;
    FirebaseDatabase database;
    FirebaseAuth auth;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_chat, container, false);

        chatRV= view.findViewById(R.id.chat_rv);
        friendList= new ArrayList<>();
        database= FirebaseDatabase.getInstance();
        auth= FirebaseAuth.getInstance();

        ChatListAdapter chatListAdapter = new ChatListAdapter(friendList, getContext());
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        chatRV.setLayoutManager(layoutManager);
        chatRV.setNestedScrollingEnabled(false);
        chatRV.setAdapter(chatListAdapter);

        database.getReference()
                .child("Users")
                .child(FirebaseAuth.getInstance().getUid())
                .child("Following")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        friendList.clear();
                        for(DataSnapshot dataSnapshot: snapshot.getChildren())
                        {
                            FollowingModel user= dataSnapshot.getValue(FollowingModel.class);
                            friendList.add(user);
                        }
                        Collections.reverse(friendList);
                        chatListAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        return view;
    }
}