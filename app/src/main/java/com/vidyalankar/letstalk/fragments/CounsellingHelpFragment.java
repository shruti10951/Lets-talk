package com.vidyalankar.letstalk.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vidyalankar.letstalk.R;


public class CounsellingHelpFragment extends Fragment {

    TextView number1,site1,site2,site3,site4,site5;


    public CounsellingHelpFragment() {
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
        View view =inflater.inflate(R.layout.fragment_counselling_help, container, false);

        number1=view.findViewById(R.id.website_text5);
        site1=view.findViewById(R.id.website_text7);
        site2=view.findViewById(R.id.website_text11);
        site3=view.findViewById(R.id.website_text13);
        site4=view.findViewById(R.id.website_text17);
        site5=view.findViewById(R.id.website_text21);

        number1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: +91 8686 139 139"));
                startActivity(intent);
            }
        });
        site1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent httpIntent= new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://www.manntalks.org/"));
                startActivity(httpIntent);
            }
        });

        site2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent httpIntent= new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://linktr.ee/therapize"));
                startActivity(httpIntent);
            }
        });

        site3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent httpIntent= new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://www.therapizeindia.com/"));
                startActivity(httpIntent);
            }
        });

        site4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent httpIntent= new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://cimhs.com/bliss-free-online-therapy-for-depression.html"));
                startActivity(httpIntent);
            }
        });

        site5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent httpIntent= new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://www.betterhelp.com/"));
                startActivity(httpIntent);
            }
        });

        return view;
    }
}