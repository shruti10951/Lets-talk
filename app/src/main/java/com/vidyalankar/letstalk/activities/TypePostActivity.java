package com.vidyalankar.letstalk.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.model.PostModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TypePostActivity extends AppCompatActivity {

    EditText textMessage;
    Button post_btn;
    TextView typeSelected, typeSelected1;
    ProgressBar progressBar;

    Intent intent;

    FirebaseAuth auth;
    FirebaseDatabase database;
    String type= "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_post);

        auth= FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();

        post_btn= findViewById(R.id.post_btn);
        textMessage= findViewById(R.id.user_text_post);
        progressBar= findViewById(R.id.progressBar);
        typeSelected= findViewById(R.id.typeSelected);
        typeSelected1= findViewById(R.id.typeSelected1);

        intent= getIntent();
        type= intent.getStringExtra("type");
        typeSelected1.setText(type);

        post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);

                PostModel postModel= new PostModel();
                postModel.setPostedBy(FirebaseAuth.getInstance().getUid());
                postModel.setPost(textMessage.getText().toString());
                postModel.setPostedAt(new Date().getTime());
                postModel.setType(type);

//                postModel.setPostedAt(formatter.format(date));

                database.getReference()
                        .child("Posts")
                        .push()
                        .setValue(postModel)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(TypePostActivity.this, "Posted Successfully!", Toast.LENGTH_SHORT).show();
                                TypePostActivity.super.onBackPressed();

                            }
                        });
            }
        });

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date= new Date();

        textMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text= textMessage.getText().toString();
                if(!text.isEmpty())
                {
                    post_btn.setBackgroundDrawable(ContextCompat.getDrawable(TypePostActivity.this, R.color.black));
                    post_btn.setTextColor(getResources().getColor(R.color.white));
                    post_btn.setEnabled(true);
                }else{
                    post_btn.setBackgroundDrawable(ContextCompat.getDrawable(TypePostActivity.this, R.color.white));
                    post_btn.setTextColor(getResources().getColor(R.color.black));
                    post_btn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


}