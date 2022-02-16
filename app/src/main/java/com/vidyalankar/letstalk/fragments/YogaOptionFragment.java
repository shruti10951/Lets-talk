package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vidyalankar.letstalk.R;

public class YogaOptionFragment extends Fragment {

    View yogaOne, yogaTwo, yogaThree, yogaFour;

    public YogaOptionFragment() {
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
        View view= inflater.inflate(R.layout.fragment_yoga_option, container, false);

        yogaOne= view.findViewById(R.id.yoga_one_option);
        yogaTwo= view.findViewById(R.id.yoga_two_option);
        yogaThree= view.findViewById(R.id.yoga_three_option);
        yogaFour= view.findViewById(R.id.yoga_four_option);

        yogaOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new YogaOneFragment()).addToBackStack(null).commit();
            }
        });

        yogaTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new YogaTwoFragment()).addToBackStack(null).commit();
            }
        });

        yogaThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new YogaThreeFragment()).addToBackStack(null).commit();
            }
        });

        yogaFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new YogaFourFragment()).addToBackStack(null).commit();
            }
        });

        return view;
    }
}