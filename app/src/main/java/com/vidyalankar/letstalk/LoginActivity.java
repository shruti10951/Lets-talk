package com.vidyalankar.letstalk;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.internal.zzx;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

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
        String email= user_email_login.getText().toString().trim();
        String password= user_password_login.getText().toString().trim();

        if(email.isEmpty())
        {
            user_email_login.setError("Email is required!");
            user_email_login.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            user_email_login.setError("Please enter valid email!");
            user_email_login.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            user_password_login.setError("Please enter password!");
            user_password_login.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            user_password_login.setError("Min password length is 6 characters!");
            user_password_login.requestFocus();
            return;
        }
        mProgressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

//                    FirebaseUser user= mAuth.getCurrentUser();
//
//                    if(task.getResult().getAdditionalUserInfo().isNewUser())
//                    {
//                        String email= user.getEmail();
//                        String uid= user.getUid();
//                        HashMap<Object, String> hashMap = new HashMap<>();
//                        hashMap.put("email", email);
//                        hashMap.put("uid", uid);
//                        hashMap.put("username", "");
//                        hashMap.put("onlineStatus", "online");
//                        hashMap.put("typingTo", "noOne");
//                        hashMap.put("phone", "");
//                        hashMap.put("image", "");
//
//                        FirebaseDatabase database= FirebaseDatabase.getInstance();
//
//                        //storing the value in Database in "Users" node
//                        DatabaseReference reference= database.getReference("Users");
//
//                        //storing the value in firebase
//                        reference.child(uid).setValue(hashMap);
//                    }

                    Toast.makeText(LoginActivity.this, "Login successfull!", Toast.LENGTH_LONG).show();

                    //redirect to dashboard
                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Failed to Login! Please check your credentials!", Toast.LENGTH_LONG).show();
                }
                mProgressBar.setVisibility(View.GONE);
            }
        });

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
            case R.id.login_btn:
                login();
                break;
            case R.id.forgot_pas_text:
                startActivity(new Intent(this, ForgotPassword.class));
                break;
        }
    }
}