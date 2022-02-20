package com.vidyalankar.letstalk.activities;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.vidyalankar.letstalk.R;

import java.io.IOException;

public class Binaural1Activity extends AppCompatActivity {
    ImageView play_audio;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binaural1);

        play_audio=findViewById(R.id.play);

        play_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                    play_audio.setImageResource(R.drawable.pause);
                }else{
                    mediaPlayer.pause();
                    play_audio.setImageResource(R.drawable.play);
                }
            }
        });

        mediaPlayer= new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Melodies%2Fbinaural%20beats%2Flife%20goes%20on.mpeg?alt=media&token=c0047a8e-15f0-4fb8-b565-d97207dc92a4");
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
                @Override
                public void onPrepared(MediaPlayer mp) {
//                    mp.start();
                    Toast.makeText(Binaural1Activity.this,"media buffering complete",Toast.LENGTH_LONG).show();
                }
            });

//            mediaPlayer.prepare();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
//    }


    }
//    private void play_song() {
//        mediaPlayer= new MediaPlayer();
////        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        try {
//            mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Melodies%2Fbinaural%20beats%2Flife%20goes%20on.mpeg?alt=media&token=c0047a8e-15f0-4fb8-b565-d97207dc92a4");
////            mediaPlayer.prepareAsync();
//            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    mp.start();
//                }
//            });
//
//            mediaPlayer.prepare();
//        }catch(IOException e)
//        {
//            e.printStackTrace();
//        }
//
//    }

//    public void play_song(View view) {


}
