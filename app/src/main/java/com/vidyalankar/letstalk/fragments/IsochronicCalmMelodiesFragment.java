package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vidyalankar.letstalk.R;

public class IsochronicCalmMelodiesFragment extends Fragment {

    View isochronic1;

    public IsochronicCalmMelodiesFragment() {
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
        View view= inflater.inflate(R.layout.fragment_isochronic_calm_melodies, container, false);

        isochronic1= view.findViewById(R.id.isochronic_option_one);
        ;


        isochronic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new IsochronicCalmMelodiesFragment()).addToBackStack(null).commit();
            }
        });


        return view;
    }

}