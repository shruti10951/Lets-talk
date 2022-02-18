package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.adapter.NotificationAdapter;
import com.vidyalankar.letstalk.model.NotificationModel;

import java.util.ArrayList;

public class AllNotificationFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<NotificationModel> list;
    FirebaseDatabase database;

    public AllNotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_all_notification, container, false);

        recyclerView= view.findViewById(R.id.notification_recycler);
        database= FirebaseDatabase.getInstance();
        list= new ArrayList<>();

        NotificationAdapter adapter= new NotificationAdapter(list, getContext());
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        database.getReference()
                .child("Notification")
                .child(FirebaseAuth.getInstance().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                            NotificationModel notificationModel= dataSnapshot.getValue(NotificationModel.class);
                            notificationModel.setNotificationId(dataSnapshot.getKey());
                            list.add(notificationModel);
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