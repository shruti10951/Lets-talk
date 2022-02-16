package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.adapter.HomeAdapter;
import com.vidyalankar.letstalk.model.PostModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView homeRV;
    ArrayList<PostModel> homeList;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        homeRV= view.findViewById(R.id.homeRV);
        homeList= new ArrayList<>();

//        homeList.add(new PostModel(R.drawable.woman,
//                "Hello how are you all?", R.drawable.bookmark_saved,"Shruti2004", "78","9"));
//        homeList.add(new PostModel(R.drawable.woman,
//                "Today was a very stressful day for me! anyone wanna talk?", R.drawable.bookmark, "Aditi2003", "67", "10"));
//        homeList.add(new PostModel(R.drawable.woman,
//                "what a beautiful day!", R.drawable.bookmark, "Amrita2004", "98", "23"));
//        homeList.add(new PostModel(R.drawable.woman,
//                "I am so sick of this fake love! fake love! I am so sorry but it is fake love!", R.drawable.bookmark_saved, "Manali2004", "56", "4"));
//        homeList.add(new PostModel(R.drawable.woman,
//                "Shining through the city with a little funk in soul!", R.drawable.bookmark, "abc1234", "89", "21"));
//
//        homeList.add(new PostModel(R.drawable.woman,
//                "Shining through the city with a little funk in soul!", R.drawable.bookmark, "abc1234", "89", "21"));


        HomeAdapter homeAdapter= new HomeAdapter(homeList, getContext());
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        homeRV.setLayoutManager(layoutManager);
        homeRV.setNestedScrollingEnabled(false);
        homeRV.setAdapter(homeAdapter);

        return view;
    }
}