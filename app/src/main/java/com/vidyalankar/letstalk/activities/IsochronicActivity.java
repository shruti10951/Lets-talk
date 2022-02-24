package com.vidyalankar.letstalk.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import com.vidyalankar.letstalk.R;

public class IsochronicActivity extends AppCompatActivity {

    String songName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isochronic);

        View.OnClickListener listener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.isochronic1:
                        songName= "Calm Mind";
                        forward();
                        break;
                    case R.id.isochronic2:
                        songName= "Morning Motivator";
                        forward();
                        break;
                    case R.id.isochronic3:
                        songName= "Alpha Waves";
                        forward();
                        break;
                    case R.id.isochronic4:
                        songName= "Beta Swirl";
                        forward();
                        break;
                }
            }
        };
        findViewById(R.id.isochronic1).setOnClickListener(listener);
        findViewById(R.id.isochronic2).setOnClickListener(listener);
        findViewById(R.id.isochronic3).setOnClickListener(listener);
        findViewById(R.id.isochronic4).setOnClickListener(listener);


    }


    private void forward() {

        Intent intent= new Intent(IsochronicActivity.this, PlayMelodies2.class);
        intent.putExtra("songName", songName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}
