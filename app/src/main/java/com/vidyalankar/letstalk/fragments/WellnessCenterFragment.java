package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.diagonallayout.DiagonalLayout;
import com.vidyalankar.letstalk.R;

public class WellnessCenterFragment extends Fragment {

    DiagonalLayout sleep, nutrition, stress, mindfulness;

    public WellnessCenterFragment() {
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
        View view= inflater.inflate(R.layout.fragment_wellness_center, container, false);

        sleep= view.findViewById(R.id.sleep_diagonal_layout);
        stress= view.findViewById(R.id.stress_diagonal_layout);
        nutrition= view.findViewById(R.id.nutrition_diagonal_layout);
        mindfulness= view.findViewById(R.id.mindfulness_diagonal_layout);

        sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new SleepOptionsFragment()).addToBackStack(null).commit();
            }
        });
        stress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new StressOptionsFragment()).addToBackStack(null).commit();
            }
        });
        mindfulness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new MindfulnessOptionsFragment()).addToBackStack(null).commit();
            }
        });
        nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new NutritionOptionsFragment()).addToBackStack(null).commit();
            }
        });

        return view;
    }
}