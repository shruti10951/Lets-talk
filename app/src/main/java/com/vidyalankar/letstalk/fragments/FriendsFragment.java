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
import com.vidyalankar.letstalk.adapter.FriendsAdapter;
import com.vidyalankar.letstalk.model.FriendsModel;

import java.util.ArrayList;


public class FriendsFragment extends Fragment {

    ArrayList<FriendsModel> list= new ArrayList<FriendsModel>();
    FirebaseAuth auth;
    FirebaseDatabase database;
    RecyclerView friendsRV;

    public FriendsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_friends, container, false);
        friendsRV= view.findViewById(R.id.friends_rv);

        list= new ArrayList<>();

//        list.add(new FriendsModel("Amrita2004", R.drawable.woman));
//        list.add(new FriendsModel("Amrita2004", R.drawable.woman));
//        list.add(new FriendsModel("Amrita2004", R.drawable.woman));
//        list.add(new FriendsModel("Amrita2004", R.drawable.woman));
//        list.add(new FriendsModel("Amrita2004", R.drawable.woman));
//        list.add(new FriendsModel("Amrita2004", R.drawable.woman));
//        list.add(new FriendsModel("Amrita2004", R.drawable.woman));
//        list.add(new FriendsModel("Amrita2004", R.drawable.woman));
//        list.add(new FriendsModel("Amrita2004", R.drawable.woman));
//        list.add(new FriendsModel("Amrita2004", R.drawable.woman));
//        list.add(new FriendsModel("Amrita2004", R.drawable.woman));
//        list.add(new FriendsModel("Amrita2004", R.drawable.woman));
//        list.add(new FriendsModel("Amrita2004", R.drawable.woman));
//        list.add(new FriendsModel("Amrita2004", R.drawable.woman));
//        list.add(new FriendsModel("Amrita2004", R.drawable.woman));
//        list.add(new FriendsModel("Amrita2004", R.drawable.woman));
//        list.add(new FriendsModel("Amrita2004", R.drawable.woman));


        FriendsAdapter adapter= new FriendsAdapter(getContext(), list);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext());
        friendsRV.setLayoutManager(layoutManager);
        friendsRV.setNestedScrollingEnabled(false);
        friendsRV.setAdapter(adapter);

        database.getReference()
                .child("Users")
                .child(auth.getUid())
                .child("followers")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for(DataSnapshot dataSnapshot: snapshot.getChildren())
                        {
                            FriendsModel user= dataSnapshot.getValue(FriendsModel.class);
                            list.add(user);
                        }
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