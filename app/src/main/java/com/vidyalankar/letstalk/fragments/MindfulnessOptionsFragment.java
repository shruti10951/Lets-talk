package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vidyalankar.letstalk.R;

public class MindfulnessOptionsFragment extends Fragment {

    View mindfulness1, mindfulness2, mindfulness3;

    public MindfulnessOptionsFragment() {
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
        View view= inflater.inflate(R.layout.fragment_mindfulness_options, container, false);

        mindfulness1= view.findViewById(R.id.mindfulness_view1);
        mindfulness2= view.findViewById(R.id.mindfulness_view2);
        mindfulness3= view.findViewById(R.id.mindfulness_view3);

        mindfulness1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new MindfulnessWellnessCenterFragment()).addToBackStack(null).commit();
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