package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vidyalankar.letstalk.R;

public class YogaFourFragment extends Fragment {

    ImageView imageView;


    public YogaFourFragment() {
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
        View view= inflater.inflate(R.layout.fragment_yoga_four, container, false);

        imageView= view.findViewById(R.id.imageView3);

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Images%2Fyoga_four_pose.png?alt=media&token=df97cc4c-8bc7-43b7-93ff-e1f57bda7919")
                .placeholder(R.drawable.loading_img)
                .into(imageView);

        return view;
    }
}