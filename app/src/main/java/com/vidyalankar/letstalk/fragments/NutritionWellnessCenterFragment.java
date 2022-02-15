package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vidyalankar.letstalk.R;

import java.io.IOException;
import java.io.InputStream;

public class NutritionWellnessCenterFragment extends Fragment {

    TextView nutrition_text;

    public NutritionWellnessCenterFragment() {
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
        View view= inflater.inflate(R.layout.fragment_nutrition_wellness_center, container, false);

        nutrition_text= view.findViewById(R.id.nutrition_article_text);

        String text= "";
        try {
            InputStream is= getResources().getAssets().open("mindfulness.txt");
            int size= is.available();
            byte[] buffer= new byte[size];
            is.read(buffer);
            is.close();
            text= new String(buffer);

        }catch (IOException e)
        {
            e.printStackTrace();
        }
        nutrition_text.setText(text);

        return view;
    }
}