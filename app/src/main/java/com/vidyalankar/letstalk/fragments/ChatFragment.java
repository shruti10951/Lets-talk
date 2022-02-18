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
import com.vidyalankar.letstalk.adapter.ChatAdapter;
import com.vidyalankar.letstalk.model.ChatModel;

import java.util.ArrayList;

public class ChatFragment extends Fragment {

    RecyclerView chatRV;
    ArrayList<ChatModel> chatList;
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
        chatList= new ArrayList<>();
        database= FirebaseDatabase.getInstance();
        auth= FirebaseAuth.getInstance();

//        chatList.add(new ChatModel(R.drawable.user_profile_default,
//                "Manali2004", "bye", "just now"));
//        chatList.add(new ChatModel(R.drawable.user_profile_default,
//                "Amrita2004", "hello", "just now"));
//        chatList.add(new ChatModel(R.drawable.user_profile_default,
//                "Manali2004", "bye", "just now"));
//        chatList.add(new ChatModel(R.drawable.user_profile_default,
//                "Aditi2003", "bye", "just now"));
//        chatList.add(new ChatModel(R.drawable.user_profile_default,
//                "Manali2004", "bye", "just now"));
//        chatList.add(new ChatModel(R.drawable.user_profile_default,
//                "abc1234", "bye", "just now"));
//        chatList.add(new ChatModel(R.drawable.user_profile_default,
//                "Manali2004", "bye", "just now"));
//        chatList.add(new ChatModel(R.drawable.user_profile_default,
//                "Manali2004", "bye", "just now"));
//        chatList.add(new ChatModel(R.drawable.user_profile_default,
//                "Manali2004", "bye", "just now"));
//        chatList.add(new ChatModel(R.drawable.user_profile_default,
//                "Manali2004", "bye", "just now"));
//        chatList.add(new ChatModel(R.drawable.user_profile_default,
//                "Manali2004", "bye", "just now"));
//        chatList.add(new ChatModel(R.drawable.user_profile_default,
//                "Manali2004", "bye", "just now"));

        ChatAdapter chatAdapter= new ChatAdapter(chatList, getContext());
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        chatRV.setLayoutManager(layoutManager);
        chatRV.setNestedScrollingEnabled(false);
        chatRV.setAdapter(chatAdapter);

        database.getReference()
                .child("Users")
                .child(FirebaseAuth.getInstance().getUid())
                .child("followers")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot: snapshot.getChildren())
                        {
                            ChatModel user= dataSnapshot.getValue(ChatModel.class);
                            chatList.add(user);
                        }
                        chatAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        return view;
    }
}