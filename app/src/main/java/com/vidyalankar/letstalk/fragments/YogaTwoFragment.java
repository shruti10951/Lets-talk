package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vidyalankar.letstalk.R;

public class YogaTwoFragment extends Fragment {

    TextView pointOne, pointTwo, pointThree, pointFour, pointFive, pointSix, pointSeven, pointEight;

    public YogaTwoFragment() {
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
        View view= inflater.inflate(R.layout.fragment_yoga_two, container, false);

        pointOne= view.findViewById(R.id.yoga_two_point_1);
        pointTwo= view.findViewById(R.id.yoga_two_point_2);
        pointThree= view.findViewById(R.id.yoga_two_point_3);
        pointFour= view.findViewById(R.id.yoga_two_point_4);
        pointFive= view.findViewById(R.id.yoga_two_point_5);
        pointSix= view.findViewById(R.id.yoga_two_point_6);
        pointSeven= view.findViewById(R.id.yoga_two_point_7);
        pointEight= view.findViewById(R.id.yoga_two_point_8);

        pointOne.setText("Child’s pose helps create an inward focus and restore energy. For more support, place a cushion under your forehead, torso, or thighs.");
        pointTwo.setText("1. From a kneeling position, place your knees together or slightly apart.");
        pointThree.setText("2.Sit back on your heels.");
        pointFour.setText("3.Hinge at your hips as you fold forward, resting your forehead on your mat.");
        pointFive.setText("4.Extend your arms in front of you or alongside your legs.");
        pointSix.setText("5.Allow your torso to sink into your thighs.");
        pointSeven.setText("6.Breathe deeply and focus on relaxing your body.");
        pointEight.setText("7.Hold this pose for up to 5 minutes.");

        return view;
    }
}