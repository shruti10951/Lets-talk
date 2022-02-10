package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.adapter.NotificationAdapter;
import com.vidyalankar.letstalk.model.NotificationModel;

import java.util.ArrayList;

public class AllNotificationFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<NotificationModel> list;

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
        list= new ArrayList<>();
        list.add(new NotificationModel(R.drawable.woman,
                "Amrita2004 just commented on a post", "just now"));
        list.add(new NotificationModel(R.drawable.woman,
                "Manali2004 just like on a post", "2 hours ago"));
        list.add(new NotificationModel(R.drawable.woman,
                "Amrita2004 just commented on a post", "just now"));
        list.add(new NotificationModel(R.drawable.woman,
                "Manali2004 just commented on a post", "just now"));
        list.add(new NotificationModel(R.drawable.woman,
                "Aditi2003 just coomented on a post", "just now"));
        list.add(new NotificationModel(R.drawable.woman,
                "Amrita2004 just coomented on a post", "just now"));
        list.add(new NotificationModel(R.drawable.woman,
                "Amrita2004 just coomented on a post", "just now"));
        list.add(new NotificationModel(R.drawable.woman,
                "Amrita2004 just coomented on a post", "just now"));
        list.add(new NotificationModel(R.drawable.woman,
                "Amrita2004 just coomented on a post", "just now"));
        list.add(new NotificationModel(R.drawable.woman,
                "Amrita2004 just coomented on a post", "just now"));
        list.add(new NotificationModel(R.drawable.woman,
                "Amrita2004 just coomented on a post", "just now"));
        list.add(new NotificationModel(R.drawable.woman,
                "Amrita2004 just  qwdeasbhbdqwuasi on a post", "just now"));

        NotificationAdapter adapter= new NotificationAdapter(list, getContext());
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }
}