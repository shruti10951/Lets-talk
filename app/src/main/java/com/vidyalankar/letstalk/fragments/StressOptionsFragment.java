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

public class StressOptionsFragment extends Fragment {
    View stress1, stress2, stress3;
    ImageView option1, option2, option3;

    public StressOptionsFragment() {
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
        View view= inflater.inflate(R.layout.fragment_stress_options, container, false);

        stress1= view.findViewById(R.id.stress_view1);
        stress2= view.findViewById(R.id.stress_view2);
        stress3= view.findViewById(R.id.stress_view3);
        option1= view.findViewById(R.id.stress_imageView4);
        option2= view.findViewById(R.id.stress_imageView1);
        option3= view.findViewById(R.id.stress_imageView3);

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Images%2Fstress1.jpeg?alt=media&token=3088accc-13a4-4d44-899a-cbdd670e8fa7")
                .placeholder(R.drawable.loading_img)
                .into(option1);

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Images%2Fstress2.jpg?alt=media&token=7508aa5f-1792-45bc-a1b2-ad2990499c99")
                .placeholder(R.drawable.loading_img)
                .into(option2);

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Images%2Fstress3.jpeg?alt=media&token=1f733139-01ca-4387-81c9-d3d1cb0763e0")
                .placeholder(R.drawable.loading_img)
                .into(option3);

        stress1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent httpIntent= new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://adi2003rajchavan.wixsite.com/letstalkapp/stress-1"));
                startActivity(httpIntent);
            }
        });

        stress2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent httpIntent= new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://adi2003rajchavan.wixsite.com/letstalkapp/copy-of-stress"));
                startActivity(httpIntent);
            }
        });

        stress3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent httpIntent= new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://adi2003rajchavan.wixsite.com/letstalkapp/stress-3"));
                startActivity(httpIntent);
            }
        });


        return view;
    }
}