package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vidyalankar.letstalk.R;

public class BreathingOptionFragment extends Fragment {

    View breathingOne, breathingTwo, breathingThree;

    public BreathingOptionFragment() {
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
        View view= inflater.inflate(R.layout.fragment_breathing_option, container, false);

        breathingOne= view.findViewById(R.id.breathing_one_option);
        breathingTwo= view.findViewById(R.id.breathing_two_option);
        breathingThree= view.findViewById(R.id.breathing_three_option);

        breathingOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new BreathingOneFragment()).addToBackStack(null).commit();
            }
        });

        breathingTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new BreathingTwoFragment()).addToBackStack(null).commit();
            }
        });

        breathingThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new BreathingThreeFragment()).addToBackStack(null).commit();
            }
        });

        return view;
    }
}