package com.vidyalankar.letstalk.activities;

import android.annotation.SuppressLint;
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
    TextView playerPosition1, playerDuration1, playerPosition2, playerDuration2;
    ImageView play1, pause1, play2, pause2;
    SeekBar seekBar1, seekBar2;

    MediaPlayer mediaPlayer;
    MediaPlayer mediaPlayer2;
    Handler handler = new Handler();
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binaural);
//        mediaPlayer=MediaPlayer.create();

        playerPosition1 = findViewById(R.id.player_position1);
        playerDuration1 = findViewById(R.id.player_duration1);
        play1 = findViewById(R.id.play1);
        pause1 = findViewById(R.id.pause1);
        seekBar1 = findViewById(R.id.seek_bar1);

        playerPosition2 = findViewById(R.id.player_position2);
        playerDuration2 = findViewById(R.id.player_duration2);
        play2 = findViewById(R.id.play2);
        pause2 = findViewById(R.id.pause2);
        seekBar2 = findViewById(R.id.seek_bar2);


        mediaPlayer = MediaPlayer.create(this, R.raw.beat1);
        mediaPlayer2 = MediaPlayer.create(this, R.raw.beat2);

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

///beat2 controls


        runnable = new Runnable() {
            @Override
            public void run() {

                seekBar2.setProgress(mediaPlayer2.getCurrentPosition());
                handler.postDelayed(this, 400);

            }
        };

        int duration2 = mediaPlayer2.getDuration();

        String sDuration2 = convertFormat(duration2);
        playerDuration2.setText(sDuration2);

        play2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play2.setVisibility(View.GONE);

                pause2.setVisibility(View.VISIBLE);

                mediaPlayer2.start();
                seekBar2.setMax(mediaPlayer2.getDuration());
                handler.postDelayed(runnable, 0);

            }
        });

        pause2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause2.setVisibility(View.GONE);
                play2.setVisibility(View.VISIBLE);
                mediaPlayer2.pause();
                handler.removeCallbacks(runnable);
            }
        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer2.seekTo(progress);
                }
                playerPosition2.setText(convertFormat(mediaPlayer2.getCurrentPosition()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mediaPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                pause2.setVisibility(View.GONE);
                play2.setVisibility(View.VISIBLE);
                mediaPlayer2.seekTo(0);
            }
        });

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
