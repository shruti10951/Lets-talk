package com.vidyalankar.letstalk.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.vidyalankar.letstalk.R;

import java.util.concurrent.TimeUnit;

public class PlayMelodies2 extends AppCompatActivity {

    TextView playerPosition1, playerDuration1;
    ImageView play1, pause1;
    SeekBar seekBar1;
    Intent intent;
    String song;

    MediaPlayer mediaPlayer;
    Handler handler = new Handler();
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_melodies2);

        playerPosition1 = findViewById(R.id.player_position1);
        playerDuration1 = findViewById(R.id.player_duration1);
        play1 = findViewById(R.id.play1);
        pause1 = findViewById(R.id.pause1);
        seekBar1 = findViewById(R.id.seek_bar1);

        intent= getIntent();
        song= intent.getStringExtra("songName");

        switch (song){
            case "Calm Mind":
                mediaPlayer=MediaPlayer.create(this, R.raw.calm_mind);
                break;
            case "Morning Motivator":
                mediaPlayer=MediaPlayer.create(this, R.raw.morning_motivator);
                break;
            case "Alpha Waves":
                mediaPlayer=MediaPlayer.create(this, R.raw.alpha_waves );
                break;
            case "Beta Swirl":
                mediaPlayer=MediaPlayer.create(this, R.raw.beta_swirl);
                break;
        }

        runnable = new Runnable() {
            @Override
            public void run() {

                seekBar1.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this, 400);

            }
        };

        int duration = mediaPlayer.getDuration();

        String sDuration = convertFormat(duration);
        playerDuration1.setText(sDuration);

        play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play1.setVisibility(View.GONE);

                pause1.setVisibility(View.VISIBLE);

                mediaPlayer.start();
                seekBar1.setMax(mediaPlayer.getDuration());
                handler.postDelayed(runnable, 0);

            }
        });

        pause1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause1.setVisibility(View.GONE);
                play1.setVisibility(View.VISIBLE);
                mediaPlayer.pause();
                handler.removeCallbacks(runnable);
            }
        });

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
                playerPosition1.setText(convertFormat(mediaPlayer.getCurrentPosition()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                pause1.setVisibility(View.GONE);
                play1.setVisibility(View.VISIBLE);
                mediaPlayer.seekTo(0);
            }
        });

    }

    @Override
    public void onBackPressed() {
        pause1.setVisibility(View.GONE);
        play1.setVisibility(View.VISIBLE);
        mediaPlayer.pause();
        handler.removeCallbacks(runnable);
        super.onBackPressed();
    }

    @SuppressLint("DefaultLocale")
    private String convertFormat(int duration) {
        return String.format("%02d:%02d"
                , TimeUnit.MILLISECONDS.toMinutes(duration)
                , TimeUnit.MILLISECONDS.toSeconds(duration) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));

    }


    ///beat2

//    @SuppressLint("DefaultLocale")
//    private String convertFormat(int duration2) {
//        return String.format("%02d:%02d"
//                , TimeUnit.MILLISECONDS.toMinutes(duration2)
//                , TimeUnit.MILLISECONDS.toSeconds(duration2) -
//                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration2)));
//
//
//    }

}