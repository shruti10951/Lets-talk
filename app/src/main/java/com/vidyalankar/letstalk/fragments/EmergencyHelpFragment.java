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

public class EmergencyHelpFragment extends Fragment {

    TextView contact1,contact2,contact3,contact4,contact5,contact6,contact7,contact8,contact9,contact10,contact11,contact12;



    public EmergencyHelpFragment() {
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
        View view= inflater.inflate(R.layout.fragment_emergency_help, container, false);

        contact1=view.findViewById(R.id.Contact1);
        contact2=view.findViewById(R.id.Contact2);
        contact3=view.findViewById(R.id.Contact3);
        contact4=view.findViewById(R.id.Contact4);
        contact5=view.findViewById(R.id.Contact5);
        contact6=view.findViewById(R.id.Contact6);
        contact7=view.findViewById(R.id.Contact7);
        contact8=view.findViewById(R.id.Contact8);
        contact9=view.findViewById(R.id.Contact9);
        contact10=view.findViewById(R.id.Contact10);
        contact11=view.findViewById(R.id.Contact11);
        contact12=view.findViewById(R.id.Contact12);

        contact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: 911"));
                startActivity(intent);
            }
        });

        contact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: 1-800-273-TALK"));
                startActivity(intent);
            }
        });

        contact3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: 8255"));
                startActivity(intent);
            }
        });

        contact4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: 1800-599-0019"));
                startActivity(intent);
            }
        });

        contact5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: 1800-599-0019"));
                startActivity(intent);
            }
        });

        contact6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: +91 022 24131212"));
                startActivity(intent);
            }
        });

        contact7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: +91 9820466726"));
                startActivity(intent);
            }
        });

        contact8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: 8888817666"));
                startActivity(intent);
            }
        });

        contact9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: 1860-266-2345"));
                startActivity(intent);
            }
        });

        contact10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: 1800 233 3330"));
                startActivity(intent);
            }
        });

        contact11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: +91 9422627571"));
                startActivity(intent);
            }
        });

        contact12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: +91 8275038382"));
                startActivity(intent);
            }
        });

        return view;
    }
}