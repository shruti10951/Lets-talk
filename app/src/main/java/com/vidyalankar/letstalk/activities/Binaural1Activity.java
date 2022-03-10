package com.vidyalankar.letstalk.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.vidyalankar.letstalk.R;

public class Binaural1Activity extends AppCompatActivity {

    String songName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binaural);

        View.OnClickListener listener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.binaural1:
                        songName= "Healing Aura";
                        forward();
                        break;
                    case R.id.binaural2:
                        songName= "Ground Air";
                        forward();
                        break;
                    case R.id.binaural3:
                        songName= "Deep Alpha";
                        forward();
                        break;
                    case R.id.binaural4:
                        songName= "Relaxing Waves";
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

        Intent intent= new Intent(Binaural1Activity.this, PlayMelodies1.class);
        intent.putExtra("songName", songName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}
