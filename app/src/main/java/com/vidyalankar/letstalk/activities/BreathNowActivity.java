package com.vidyalankar.letstalk.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.vidyalankar.letstalk.R;

public class BreathNowActivity extends AppCompatActivity {

    TextView relax, relax1, relax2;
    Button start;
    ImageView imageView;
    Intent intent;
    View view1;
    ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breath_now);

        relax = findViewById(R.id.relax);
        relax1 = findViewById(R.id.relax1);
        relax2 = findViewById(R.id.relax2);
        imageView = findViewById(R.id.imageView);
        view1 = findViewById(R.id.view1);
        toggleButton = findViewById(R.id.start);

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggleButton.isChecked()) {
                    relax.setVisibility(View.GONE);
                    relax1.setVisibility(View.GONE);
                    relax2.setVisibility(View.GONE);
                    view1.setVisibility(View.GONE);

                    Glide.with(BreathNowActivity.this)
                            .asGif()
                            .load("https://firebasestorage.googleapis.com/v0/b/let-s-talk-51904.appspot.com/o/Gifs%2Fbreathing.gif?alt=media&token=17e97841-9b59-458d-b34a-ea2df188773d")
                            .centerCrop()
                            .into(imageView);
                    imageView.setVisibility(View.VISIBLE);

                } else {
                    relax.setVisibility(View.VISIBLE);
                    relax1.setVisibility(View.VISIBLE);
                    relax2.setVisibility(View.VISIBLE);
                    view1.setVisibility(View.VISIBLE);

                    Glide.with(BreathNowActivity.this)
                            .asGif()
                            .load("https://1.bp.blogspot.com/-r0IC5MUtPQg/X58h06D6T0I/AAAAAAAABRU/ePnoketrkG4O_tvR4BGursFbbbXbbzmTACLcBGAsYHQ/s16000/agifcolossalsphere2.gif")
                            .centerCrop()
                            .into(imageView);
                    imageView.setVisibility(View.GONE);
                }
            }
        });
    }
}