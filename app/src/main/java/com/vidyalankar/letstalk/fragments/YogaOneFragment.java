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

public class YogaOneFragment extends Fragment {

    TextView pointOne, pointTwo, pointThree, pointFour, pointFive;
    ImageView imageView1,imageView2;

    public YogaOneFragment() {
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
        View view= inflater.inflate(R.layout.fragment_yoga_one, container, false);

        pointOne= view.findViewById(R.id.yoga_one_point_1);
        pointTwo= view.findViewById(R.id.yoga_one_point_2);
        pointThree= view.findViewById(R.id.yoga_one_point_3);
        pointFour= view.findViewById(R.id.yoga_one_point_4);
        pointFive= view.findViewById(R.id.yoga_one_point_5);
        imageView1= view.findViewById(R.id.yoga_one_image_1);
        imageView2= view.findViewById(R.id.yoga_one_image_2);

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Images%2Fyoga_one_pose1.png?alt=media&token=2e8c1461-8114-4c4f-aed2-700483360b20")
                .placeholder(R.drawable.loading_img)
                .into(imageView1);

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Images%2Fyoga_one_pose2.png?alt=media&token=3ca8f30f-c091-49f4-9867-93ff5c800300")
                .placeholder(R.drawable.loading_img)
                .into(imageView2);

        pointOne.setText("1. Begin in a tabletop position.");
        pointTwo.setText("2. Place your wrists underneath your shoulders and your knees underneath your hips.");
        pointThree.setText("3. As you inhale, turn your gaze toward the ceiling and allow your belly to move toward your mat, arching your back. This is Cow Pose.");
        pointFour.setText("4. As you exhale, draw your chin in toward your chest and bend your spine toward the ceiling, like a cat.");
        pointFive.setText("5. Continue to flow between these two positions for 1 minute.");

        return view;
    }
}