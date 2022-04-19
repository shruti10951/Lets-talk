package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vidyalankar.letstalk.R;

public class BreathingOneFragment extends Fragment {

    public BreathingOneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_breathing_one, container, false);

        ImageView pose1= view.findViewById(R.id.yoga_one_image_1);
        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Images%2Fbreathing_one_pose1.jpg?alt=media&token=2fc7e5df-c272-4556-858b-fd435107ac0f")
                .placeholder(R.drawable.loading_img)
                .into(pose1);

        return view;
    }
}