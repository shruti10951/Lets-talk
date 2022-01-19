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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout mLoginContainer;
    AnimationDrawable mAnimationDrawable;

    EditText user_email_login, user_password_login;
    Button login_btn;
    TextView sign_up_text, forgot_pass_text;
    ProgressBar mProgressBar;

    private FirebaseAuth mAuth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginContainer= (LinearLayout) findViewById(R.id.login_container);
        mAnimationDrawable= (AnimationDrawable) mLoginContainer.getBackground();
        mAnimationDrawable.setEnterFadeDuration(2000);
        mAnimationDrawable.setExitFadeDuration(2000);

        mAuth = FirebaseAuth.getInstance();

        user_email_login= (EditText) findViewById(R.id.user_email_login);
        login_btn= (Button) findViewById(R.id.login_btn);
        user_password_login= (EditText) findViewById(R.id.user_password_login);
        sign_up_text= (TextView) findViewById(R.id.sign_up_text);
        forgot_pass_text= (TextView) findViewById(R.id.forgot_pas_text);

        mProgressBar= (ProgressBar) findViewById(R.id.progress_bar_login);

        if (mAuth != null) {
            currentUser = mAuth.getCurrentUser();
        }

        sign_up_text.setOnClickListener(this);
        login_btn.setOnClickListener(this);
        forgot_pass_text.setOnClickListener(this);

    }

    private void login()
    {
        String email= user_email_login.getText().toString();
        String password= user_password_login.getText().toString();

        if(TextUtils.isEmpty(email))
        {
            user_email_login.setError("Please enter username!");
            user_email_login.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            user_password_login.setError("Please enter password!");
            user_password_login.requestFocus();
            return;
        }



    }


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

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.sign_up_text:
                startActivity(new Intent(this, RegistrationActivity.class));
                break;
        }
    }
}