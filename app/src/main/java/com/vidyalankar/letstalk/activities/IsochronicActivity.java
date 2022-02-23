package com.vidyalankar.letstalk.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.vidyalankar.letstalk.R;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class IsochronicActivity extends AppCompatActivity {

    String songName;
    TextView song1, song2, song3, song4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isochronic);

        View.OnClickListener listener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.song1:
                        songName= "song 1";
                        forward();
                        break;
                    case R.id.song2:
                        songName= "song 2";
                        forward();
                        break;
                    case R.id.song3:
                        songName= "song 3";
                        forward();
                        break;
                    case R.id.song4:
                        songName= "song 4";
                        forward();
                        break;
                }
            }
        };
        findViewById(R.id.song1).setOnClickListener(listener);
        findViewById(R.id.song2).setOnClickListener(listener);
        findViewById(R.id.song3).setOnClickListener(listener);
        findViewById(R.id.song4).setOnClickListener(listener);


    }


    private void forward() {

        Intent intent= new Intent(IsochronicActivity.this, PlayMelodies.class);
        intent.putExtra("songName", songName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}
