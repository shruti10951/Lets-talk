package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vidyalankar.letstalk.R;

public class PostSuccessfulFragment extends Fragment {


    public PostSuccessfulFragment() {
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
        View view= inflater.inflate(R.layout.fragment_post_successful, container, false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               getFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new HomeFragment()).commit();
            }
        }, 5000);

        return view;
    }
}