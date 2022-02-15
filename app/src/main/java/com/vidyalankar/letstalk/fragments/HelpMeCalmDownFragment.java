package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vidyalankar.letstalk.R;

public class HelpMeCalmDownFragment extends Fragment {

    View breathing_option, yoga_option;

    public HelpMeCalmDownFragment() {
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
        View view= inflater.inflate(R.layout.fragment_help_me_calm_down, container, false);

        breathing_option= view.findViewById(R.id.breathing_option);
        yoga_option= view.findViewById(R.id.yoga_option);

        breathing_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new BreathingOptionFragment()).commit();
            }
        });

        yoga_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new YogaOptionFragment()).commit();
            }
        });

        return view;
    }
}