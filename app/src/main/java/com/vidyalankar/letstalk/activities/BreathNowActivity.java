package com.vidyalankar.letstalk.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vidyalankar.letstalk.R;

public class BreathNowActivity extends AppCompatActivity {

    TextView relax;
    Button start;
    Button stop;
    ImageView imageView;
    Intent intent;
    MediaPlayer mediaPlayer;
    Handler handler = new Handler();
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breath_now);

        relax=findViewById(R.id.relax);
         start=findViewById(R.id.start);
         stop=findViewById(R.id.stop);
         imageView=findViewById(R.id.imageView);

        mediaPlayer=MediaPlayer.create(this, R.raw.forest_ambience);

        intent= getIntent();

        runnable = new Runnable() {
            @Override
            public void run() {

//                seekBar1.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this, 400);

            }
        };

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    start.setVisibility(View.GONE);
                    stop.setVisibility(View.VISIBLE);

                    relax.setVisibility(View.GONE);
                mediaPlayer.start();
                handler.postDelayed(runnable, 0);
                    Glide.with(BreathNowActivity.this)
                        .asGif()
                       .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Gifs%2Fbreathing.gif?alt=media&token=9165b113-a74b-4bf1-a00b-a091d78e2f5f")
                        .centerCrop()
                        .into(imageView);


            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop.setVisibility(View.GONE);
                start.setVisibility(View.VISIBLE);
                relax.setVisibility(View.VISIBLE);
                mediaPlayer.pause();
                handler.removeCallbacks(runnable);
                Glide.with(BreathNowActivity.this)
                        .asGif()
                        .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Gifs%2Fflowing_water_gif.gif?alt=media&token=78cdf82c-1018-4a6d-ba9d-c3df9f2c58dd")
                        .centerCrop()
                        .into(imageView);

//                handler.removeCallbacks(runnable);
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stop.setVisibility(View.GONE);
                start.setVisibility(View.VISIBLE);
//                mediaPlayer.seekTo(0);
            }
        });



    }

    @Override
    public void onBackPressed() {
        stop.setVisibility(View.GONE);
        start.setVisibility(View.VISIBLE);
        mediaPlayer.pause();
        handler.removeCallbacks(runnable);
        super.onBackPressed();
    }
}