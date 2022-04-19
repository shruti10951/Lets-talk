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

public class NutritionOptionsFragment extends Fragment {
    View nutrition1, nutrition2, nutrition3;
    ImageView option1, option2, option3;

    public NutritionOptionsFragment() {
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
        View view= inflater.inflate(R.layout.fragment_nutrition_options, container, false);

        nutrition1= view.findViewById(R.id.nutrition_view1);
        nutrition2= view.findViewById(R.id.nutrition_view2);
        nutrition3= view.findViewById(R.id.nutrition_view3);
        option1= view.findViewById(R.id.nutrition_imageView4);
        option2= view.findViewById(R.id.nutrition_imageView1);
        option3= view.findViewById(R.id.nutrition_imageView3);

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Images%2Fnutrition1.jpeg?alt=media&token=87ce2387-a31b-47b1-ba36-fddb7bb0d165")
                .placeholder(R.drawable.loading_img)
                .into(option1);

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Images%2Fnutrition2.jpeg?alt=media&token=83ac3972-9d1c-41aa-a4f8-2ef080ebf83e")
                .placeholder(R.drawable.loading_img)
                .into(option2);

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Images%2Fnutrition3.jpg?alt=media&token=8c5651dc-2587-4c75-91bb-5fa99e35a56d")
                .placeholder(R.drawable.loading_img)
                .into(option3);

        nutrition1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent httpIntent= new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://adi2003rajchavan.wixsite.com/letstalkapp/general-3"));
                startActivity(httpIntent);
            }
        });

        nutrition2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent httpIntent= new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://adi2003rajchavan.wixsite.com/letstalkapp/team-3"));
                startActivity(httpIntent);
            }
        });

        nutrition3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent httpIntent= new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://adi2003rajchavan.wixsite.com/letstalkapp/services-4"));
                startActivity(httpIntent);
            }
        });

        return view;
    }
}