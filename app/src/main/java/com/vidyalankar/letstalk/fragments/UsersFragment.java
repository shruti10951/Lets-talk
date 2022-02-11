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
import com.vidyalankar.letstalk.adapter.UserAdapter;
import com.vidyalankar.letstalk.model.User;

import java.util.ArrayList;

public class UsersFragment extends Fragment {

    ArrayList<User> list= new ArrayList<User>();
    FirebaseAuth auth;
    FirebaseDatabase database;
    RecyclerView userRv;

    public UsersFragment() {
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
        View view= inflater.inflate(R.layout.fragment_users, container, false);

        userRv= view.findViewById(R.id.users_show_rv);

        UserAdapter adapter= new UserAdapter(getContext(), list);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext());
        userRv.setLayoutManager(layoutManager);
        userRv.setNestedScrollingEnabled(false);
        userRv.setAdapter(adapter);

        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    User user= dataSnapshot.getValue(User.class);
                    user.setUserID(dataSnapshot.getKey());
                    list.add(user);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}