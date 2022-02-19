package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vidyalankar.letstalk.R;

public class StressOptionsFragment extends Fragment {
    View stress1, stress2, stress3;

    public StressOptionsFragment() {
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
        View view= inflater.inflate(R.layout.fragment_stress_options, container, false);

        stress1= view.findViewById(R.id.stress_view1);
        stress2= view.findViewById(R.id.stress_view2);
        stress3= view.findViewById(R.id.stress_view3);

        stress1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new StressWellnessCenterFragment()).addToBackStack(null).commit();
            }
        });

//        yogaTwo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new YogaTwoFragment()).addToBackStack(null).commit();
//            }
//        });
//
//        yogaThree.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new YogaThreeFragment()).addToBackStack(null).commit();
//            }
//        });
//
//        yogaFour.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new YogaFourFragment()).addToBackStack(null).commit();
//            }
//        });

        return view;
    }
}