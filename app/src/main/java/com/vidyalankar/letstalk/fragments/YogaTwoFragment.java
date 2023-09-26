package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vidyalankar.letstalk.R;

public class YogaTwoFragment extends Fragment {

    TextView pointOne, pointTwo, pointThree, pointFour, pointFive, pointSix, pointSeven;
    ImageView imageView1;

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
        imageView1= view.findViewById(R.id.yoga_two_pose_1);

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Images%2Fyoga_two_pose.png?alt=media&token=9fe07c28-8e48-4b2e-886b-031aeadeca6e")
                .placeholder(R.drawable.loading_img)
                .into(imageView1);


        pointOne.setText("1. From a kneeling position, place your knees together or slightly apart.");
        pointTwo.setText("2.Sit back on your heels.");
        pointThree.setText("3.Hinge at your hips as you fold forward, resting your forehead on your mat.");
        pointFour.setText("4.Extend your arms in front of you or alongside your legs.");
        pointFive.setText("5.Allow your torso to sink into your thighs.");
        pointSix.setText("6.Breathe deeply and focus on relaxing your body.");
        pointSeven.setText("7.Hold this pose for up to 5 minutes.");

        return view;
    }
}