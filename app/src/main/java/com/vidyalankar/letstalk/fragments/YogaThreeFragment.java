package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vidyalankar.letstalk.R;

public class YogaThreeFragment extends Fragment {

    ImageView imageView1;

    public YogaThreeFragment() {
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
        View view= inflater.inflate(R.layout.fragment_yoga_three, container, false);

        imageView1= view.findViewById(R.id.imageView3);

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Images%2Fyoga_three_pose.png?alt=media&token=79a33ab8-8904-4f53-81c9-9179f785f5c0")
                .placeholder(R.drawable.loading_img)
                .into(imageView1);

        return view;
    }
}