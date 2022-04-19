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

public class SleepOptionsFragment extends Fragment {
    View sleep1, sleep2, sleep3;
    ImageView option1, option2, option3;

    public SleepOptionsFragment() {
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
        View view= inflater.inflate(R.layout.fragment_sleep_options, container, false);

        sleep1= view.findViewById(R.id.sleep_view1);
        sleep2= view.findViewById(R.id.sleep_view2);
        sleep3= view.findViewById(R.id.sleep_view3);
        option1= view.findViewById(R.id.sleep_imageView4);
        option2= view.findViewById(R.id.sleep_imageView1);
        option3= view.findViewById(R.id.sleep_imageView3);

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Images%2Fsleep1.jpeg?alt=media&token=27de8ac5-9120-42e7-8b08-9b85876cc553")
                .placeholder(R.drawable.loading_img)
                .into(option1);

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Images%2Fsleep2.jpeg?alt=media&token=846ee189-7d91-4608-85dc-147afafe2e2a")
                .placeholder(R.drawable.loading_img)
                .into(option2);

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Images%2Fsleep3.jpg?alt=media&token=29336e2a-ce0d-43b7-b763-a70784ef7677")
                .placeholder(R.drawable.loading_img)
                .into(option3);

        sleep1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent httpIntent= new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://adi2003rajchavan.wixsite.com/letstalkapp/copy-of-sleep"));
                startActivity(httpIntent);
            }
        });

        sleep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent httpIntent= new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://adi2003rajchavan.wixsite.com/letstalkapp/sleep2"));
                startActivity(httpIntent);
            }
        });

        sleep3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent httpIntent= new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://adi2003rajchavan.wixsite.com/letstalkapp/the-problem-they-caused-and-the-possible"));
                startActivity(httpIntent);
            }
        });

        return view;
    }
}