package com.vidyalankar.letstalk.fragments;

import android.content.Intent;
import android.net.Uri;
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
                Intent httpIntent= new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://adi2003rajchavan.wixsite.com/letstalkapp/stress-1"));
                startActivity(httpIntent);
            }
        });

        stress2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent httpIntent= new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://adi2003rajchavan.wixsite.com/letstalkapp/copy-of-stress"));
                startActivity(httpIntent);
            }
        });

        stress3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent httpIntent= new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://adi2003rajchavan.wixsite.com/letstalkapp/stress-3"));
                startActivity(httpIntent);
            }
        });


        return view;
    }
}