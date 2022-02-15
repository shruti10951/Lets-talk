package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.vidyalankar.letstalk.R;

import java.io.IOException;
import java.io.InputStream;

public class SleepWellnessCenterFragment extends Fragment {

    TextView articleText;

    StorageReference reference;

    public SleepWellnessCenterFragment() {
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
        View view=  inflater.inflate(R.layout.fragment_sleep_wellness_center, container, false);

        articleText= view.findViewById(R.id.sleep_article_text);
        reference= FirebaseStorage.getInstance().getReference().child("Articles");

        String text= "";
        try {
            InputStream is= getResources().getAssets().open("mindfulness.txt");
            int size= is.available();
            byte[] buffer= new byte[size];
            is.read(buffer);
            is.close();
            text= new String(buffer);

        }catch (IOException e)
        {
            e.printStackTrace();
        }
        articleText.setText(text);

        return view;
    }
}