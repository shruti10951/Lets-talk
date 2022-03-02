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

import com.bumptech.glide.Glide;
import com.vidyalankar.letstalk.R;

import java.util.concurrent.TimeUnit;

public class PlayMelodies1 extends AppCompatActivity {

    TextView playerPosition1, playerDuration1;
    ImageView play1, pause1,imageView;
    SeekBar seekBar1;
    Intent intent;
    String song;

    MediaPlayer mediaPlayer, mediaPlayer1;
    Handler handler = new Handler();
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_melodies1);


        playerPosition1 = findViewById(R.id.player_position1);
        playerDuration1 = findViewById(R.id.player_duration1);
        play1 = findViewById(R.id.play1);
        pause1 = findViewById(R.id.pause1);
        seekBar1 = findViewById(R.id.seek_bar1);
        imageView= findViewById(R.id.imageView);

        intent= getIntent();
        song= intent.getStringExtra("songName");

        switch (song){
            case "Healing Aura":
                Glide.with(this)
                        .asGif()
                        .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Gifs%2Fcloud_gif.gif?alt=media&token=823913a6-0eb9-4055-a4d1-8c1aa79e9d45")
                        .centerCrop()
                        .into(imageView);
                mediaPlayer=MediaPlayer.create(this, R.raw.healing_aura);
                break;
            case "Ground Air":
                Glide.with(this)
                        .asGif()
                        .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Gifs%2Frain_on_leaf_gif.gif?alt=media&token=b833a5fd-78c6-4584-9c3f-de4019cdc4ab")
                        .centerCrop()
                        .into(imageView);
                mediaPlayer=MediaPlayer.create(this, R.raw.ground_air);
                break;
            case "Deep Alpha":
                Glide.with(this)
                        .asGif()
                        .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Gifs%2Fblue_gif.gif?alt=media&token=220876da-2bed-4d53-abd3-e764d5017475")
                        .centerCrop()
                        .into(imageView);
                mediaPlayer=MediaPlayer.create(this, R.raw.deep_alpha);
                break;
            case "Relaxing Waves":
                Glide.with(this)
                        .asGif()
                        .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Gifs%2Fflowing_water_gif.gif?alt=media&token=78cdf82c-1018-4a6d-ba9d-c3df9f2c58dd")
                        .centerCrop()
                        .into(imageView);
                mediaPlayer=MediaPlayer.create(this, R.raw.relaxing_waves);
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

}
