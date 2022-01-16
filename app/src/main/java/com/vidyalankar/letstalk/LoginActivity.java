package com.vidyalankar.letstalk;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    LinearLayout mLoginContainer;
    AnimationDrawable mAnimationDrawable;

    EditText email_et, password_et;
    Button login_btn;
    TextView sign_up_btn, forgot_pass_btn;
    ProgressBar mProgressBar;

    private FirebaseAuth mAuth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("Login to Account");
//        actionBar.setDisplayShowHomeEnabled(true);
//        actionBar.setDisplayHomeAsUpEnabled(true);

        mLoginContainer= (LinearLayout) findViewById(R.id.login_container);
        mAnimationDrawable= (AnimationDrawable) mLoginContainer.getBackground();
        mAnimationDrawable.setEnterFadeDuration(2000);
        mAnimationDrawable.setExitFadeDuration(2000);

        mAuth = FirebaseAuth.getInstance();
        email_et= (EditText) findViewById(R.id.email);
        login_btn= (Button) findViewById(R.id.login_btn);
        password_et= (EditText) findViewById(R.id.user_password);
        sign_up_btn= (TextView) findViewById(R.id.sign_up_btn);
        forgot_pass_btn= (TextView) findViewById(R.id.forgot_pas_btn);

        mProgressBar= new ProgressBar(this);

        if (mAuth != null) {
            currentUser = mAuth.getCurrentUser();
        }

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();
            }
        });

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent signUpIntent= new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(signUpIntent);

            }
        });

        forgot_pass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void login()
    {
        String email= email_et.getText().toString();
        String password= password_et.getText().toString();

        if(TextUtils.isEmpty(email))
        {
            email_et.setError("Please enter username!");
            email_et.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            password_et.setError("Please enter password!");
            password_et.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password);


    }

//    StringRequest stringRequest = new StringRequest(Request.Method.POST, URLS.login_api,
//            new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//
//                }
//            },
//            new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//
//                }
//            }
//
//    );//end of the string


    @Override
    protected void onResume() {
        super.onResume();

        if(mAnimationDrawable != null && !mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(mAnimationDrawable != null && mAnimationDrawable.isRunning())
        {
            mAnimationDrawable.stop();
        }
    }
}