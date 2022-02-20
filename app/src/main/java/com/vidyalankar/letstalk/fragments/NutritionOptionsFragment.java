package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vidyalankar.letstalk.R;

public class NutritionOptionsFragment extends Fragment {
    View nutrition1, nutrition2, nutrition3;

    public NutritionOptionsFragment() {
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
        View view= inflater.inflate(R.layout.fragment_nutrition_options, container, false);

        nutrition1= view.findViewById(R.id.nutrition_view1);
        nutrition2= view.findViewById(R.id.nutrition_view2);
        nutrition3= view.findViewById(R.id.nutrition_view3);

        nutrition1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new NutritionWellnessCenterFragment()).addToBackStack(null).commit();
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