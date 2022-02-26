package com.vidyalankar.letstalk.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.activities.Binaural1Activity;
import com.vidyalankar.letstalk.activities.IsochronicActivity;

public class CalmMelodiesFragment extends Fragment {

    View binaural, isochronic;

    public CalmMelodiesFragment() {
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
        View view= inflater.inflate(R.layout.fragment_calm_melodies, container, false);

        binaural= view.findViewById(R.id.melodies_one_option);
        isochronic= view.findViewById(R.id.melodies_two_option);


        binaural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), Binaural1Activity.class);
                startActivity(intent);
            }
        });

        isochronic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), IsochronicActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }
}