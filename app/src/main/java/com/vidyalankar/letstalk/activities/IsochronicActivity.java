package com.vidyalankar.letstalk.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import com.vidyalankar.letstalk.R;

public class IsochronicActivity extends AppCompatActivity {

    String songName;
    TextView binaural1, binaural2, binaural3, binaural4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isochronic);

        View.OnClickListener listener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.binaural1:
                        songName= "song 1";
                        forward();
                        break;
                    case R.id.binaural2:
                        songName= "song 2";
                        forward();
                        break;
                    case R.id.binaural3:
                        songName= "song 3";
                        forward();
                        break;
                    case R.id.binaural4:
                        songName= "song 4";
                        forward();
                        break;
                }
            }
        };
        findViewById(R.id.binaural1).setOnClickListener(listener);
        findViewById(R.id.binaural2).setOnClickListener(listener);
        findViewById(R.id.binaural3).setOnClickListener(listener);
        findViewById(R.id.binaural4).setOnClickListener(listener);


    }


    private void forward() {

        Intent intent= new Intent(IsochronicActivity.this, PlayMelodies2.class);
        intent.putExtra("songName", songName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}
