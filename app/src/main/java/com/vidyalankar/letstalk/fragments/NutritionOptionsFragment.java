package com.vidyalankar.letstalk.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vidyalankar.letstalk.R;

public class NutritionOptionsFragment extends Fragment {
    View nutrition1, nutrition2, nutrition3;

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