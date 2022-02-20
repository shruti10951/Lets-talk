package com.vidyalankar.letstalk.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.activities.GroupChatActivity;

public class LetsTalkCommunityFragment extends Fragment {
    public LetsTalkCommunityFragment() {
        // Required empty public constructor
    }


    TextView grp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lets_talk_community, container, false);
        grp= view.findViewById(R.id.grp);

        grp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getContext(), GroupChatActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}