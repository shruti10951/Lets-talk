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

public class StressWellnessCenterFragment extends Fragment {

    TextView stress_text;

    public StressWellnessCenterFragment() {
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
        View view= inflater.inflate(R.layout.fragment_stress_wellness_center, container, false);

        stress_text= view.findViewById(R.id.stress_article_text);

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
        stress_text.setText(text);

        return view;
    }
}