package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.vidyalankar.letstalk.R;

public class BrainwaveCalmMelodiesFragment extends Fragment {
    View binaural, isochronic;

    public BrainwaveCalmMelodiesFragment() {
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
        View view= inflater.inflate(R.layout.fragment_brainwave_calm_melodies, container, false);

        binaural= view.findViewById(R.id.melodies_one_option);
        isochronic= view.findViewById(R.id.melodies_two_option);


        binaural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new BinauralCalmMelodiesFragment()).addToBackStack(null).commit();
            }
        });

        isochronic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new IsochronicCalmMelodiesFragment()).addToBackStack(null).commit();
            }
        });

        return view;
    }
}

