package com.vidyalankar.letstalk.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.vidyalankar.letstalk.R;

public class BreathNowActivity extends AppCompatActivity {

    TextView relax, relax1, relax2;
    Button start;
    ImageView imageView;
    Intent intent;
//    MediaPlayer mediaPlayer;
    Handler handler = new Handler();
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breath_now);

        relax = findViewById(R.id.relax);
        relax1 = findViewById(R.id.relax1);
        relax2 = findViewById(R.id.relax2);
        start = findViewById(R.id.start);
        imageView = findViewById(R.id.imageView);

//        mediaPlayer = MediaPlayer.create(this, R.raw.healing_aura);

        intent = getIntent();

        runnable = new Runnable() {
            @Override
            public void run() {

//                seekBar1.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this, 400);

            }
        };
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(start.getText().equals("start")){
                    relax.setVisibility(View.GONE);
                    relax1.setVisibility(View.GONE);
                    relax2.setVisibility(View.GONE);
//                    mediaPlayer.start();
                    handler.postDelayed(runnable, 0);
                    Glide.with(BreathNowActivity.this)
                            .asGif()
                            .load("https://media.giphy.com/avatars/breathwrk/TSoeGvteuG0O.gif")
                            .centerCrop()
                            .into(imageView);
                    imageView.setVisibility(View.VISIBLE);
                    start.setText("stop");
                }else{
                    relax.setVisibility(View.VISIBLE);
                    relax1.setVisibility(View.VISIBLE);
                    relax2.setVisibility(View.VISIBLE);
//                    mediaPlayer.stop();
                    handler.removeCallbacks(runnable);
                    imageView.setVisibility(View.GONE);
//                    handler.removeCallbacks(runnable);
                    start.setText("start");
                }
            }
        });

//        start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                start.setVisibility(View.GONE);
//                stop.setVisibility(View.VISIBLE);
//                imageView.setVisibility(View.VISIBLE);
//
//                relax.setVisibility(View.GONE);
//                relax1.setVisibility(View.GONE);
//                relax2.setVisibility(View.GONE);
//
//                mediaPlayer.start();
//                handler.postDelayed(runnable, 0);
//                Glide.with(BreathNowActivity.this)
//                        .asGif()
//                        .load("https://cdn.dribbble.com/users/1137657/screenshots/11205934/media/a443c887178b8b40acba4bd2a726d767.gif")
////                               "https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Gifs%2Fbreathing.gif?alt=media&token=9165b113-a74b-4bf1-a00b-a091d78e2f5f")
//                        .centerCrop()
//                        .into(imageView);
//
//
//            }
//        });
//
//        stop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                stop.setVisibility(View.GONE);
//                start.setVisibility(View.VISIBLE);
//                mediaPlayer.pause();
//                handler.removeCallbacks(runnable);
//                imageView.setVisibility(View.GONE);
//                relax.setVisibility(View.VISIBLE);
//                relax1.setVisibility(View.VISIBLE);
//                relax2.setVisibility(View.VISIBLE);
////                Glide.with(BreathNowActivity.this)
////                        .asGif()
////                        .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Gifs%2Fflowing_water_gif.gif?alt=media&token=78cdf82c-1018-4a6d-ba9d-c3df9f2c58dd")
////                        .centerCrop()
////                        .into(imageView);
//
//
////                handler.removeCallbacks(runnable);
//            }
//        });

//        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                start.setVisibility(View.VISIBLE);
////                mediaPlayer.seekTo(0);
//            }
//        });


    }

    @Override
    public void onBackPressed() {
        start.setVisibility(View.VISIBLE);
//        mediaPlayer.pause();
        handler.removeCallbacks(runnable);
        super.onBackPressed();
    }
}