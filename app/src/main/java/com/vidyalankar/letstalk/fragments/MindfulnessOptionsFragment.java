package com.vidyalankar.letstalk.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vidyalankar.letstalk.R;

public class MindfulnessOptionsFragment extends Fragment {

    View mindfulness1, mindfulness2, mindfulness3;
    ImageView option1, option2, option3;

    public MindfulnessOptionsFragment() {
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
        View view= inflater.inflate(R.layout.fragment_mindfulness_options, container, false);

        mindfulness1= view.findViewById(R.id.mindfulness_view1);
        mindfulness2= view.findViewById(R.id.mindfulness_view2);
        mindfulness3= view.findViewById(R.id.mindfulness_view3);
        option1= view.findViewById(R.id.mindfulness_imageView4);
        option2= view.findViewById(R.id.mindfulness_imageView1);
        option3= view.findViewById(R.id.mindfulness_imageView3);

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Images%2Fmindfulness1.jpg?alt=media&token=eeb61dc2-482f-42e4-9b3c-383fb81f1ab7")
                .placeholder(R.drawable.loading_img)
                .into(option1);

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Images%2Fmindfulness2.jpeg?alt=media&token=9abc382a-8ec1-46ac-ac5f-5e3fefebb956")
                .placeholder(R.drawable.loading_img)
                .into(option2);

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Images%2Fmindfulness3.jpg?alt=media&token=5fd0190d-d638-41dd-8830-354753fc9c4b")
                .placeholder(R.drawable.loading_img)
                .into(option3);

        mindfulness1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent httpIntent= new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://adi2003rajchavan.wixsite.com/letstalkapp/general-8"));
                startActivity(httpIntent);
            }
        });

        mindfulness2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent httpIntent= new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://adi2003rajchavan.wixsite.com/letstalkapp/general-7"));
                startActivity(httpIntent);

            }
        });

        mindfulness3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent httpIntent= new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://adi2003rajchavan.wixsite.com/letstalkapp/basic-01"));
                startActivity(httpIntent);
            }
        });

        return view;
    }

}