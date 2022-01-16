package com.vidyalankar.letstalk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;

public class  RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView login_btn;
    private EditText email_et, username_et, password_et, confirm_pass_et;
    private Button sign_btn;
    private ProgressBar progressbar;

    private FirebaseAuth mAuth;
    FirebaseUser currentUser;

    LinearLayout mLoginContainer;
    AnimationDrawable mAnimationDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        mLoginContainer = (LinearLayout) findViewById(R.id.login_container);
        mAnimationDrawable = (AnimationDrawable) mLoginContainer.getBackground();
        mAnimationDrawable.setEnterFadeDuration(5000);
        mAnimationDrawable.setExitFadeDuration(2000);

        sign_btn = (Button) findViewById(R.id.sign_btn);
        sign_btn.setOnClickListener(this);

        login_btn = (TextView) findViewById(R.id.to_login_btn);
        login_btn.setOnClickListener(this);

        email_et = (EditText) findViewById(R.id.user_mail);
        username_et = (EditText) findViewById(R.id.user_name);
        password_et = (EditText) findViewById(R.id.user_password);
        confirm_pass_et = (EditText) findViewById(R.id.user_password_confirm);

//        progressbar= new ProgressBar(this);
//
//        if (mAuth != null) {
//            currentUser = mAuth.getCurrentUser();
//        }

        progressbar=(ProgressBar) findViewById(R.id.progressbar);
//        forgot_pass_btn= (TextView) findViewById(R.id.forgot_pas_btn);

    }


    @Override
    protected void onResume() {
        super.onResume();

        if (mAnimationDrawable != null && !mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mAnimationDrawable != null && mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.to_login_btn:
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                break;
            case R.id.sign_btn:
                sign_btn();
                break;

        }
    }

    private void sign_btn() {
        String email = email_et.getText().toString();
        String username = username_et.getText().toString();
        String password = password_et.getText().toString();
        String confirm_pass = confirm_pass_et.getText().toString();

        if (TextUtils.isEmpty(username)) {
            username_et.setError("Please enter username!");
            username_et.requestFocus();
            return;
        }

            if (TextUtils.isEmpty(email)) {
                email_et.setError("Please enter email!");
                email_et.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                email_et.setError("Please provide valid email address");
                email_et.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                password_et.setError("Please enter password!");
                password_et.requestFocus();
                return;
            }

            if (password.length() < 6) {
                password_et.setError("Password length should be 6 characters");
                password_et.requestFocus();
                return;
            }


            if (TextUtils.isEmpty(confirm_pass)) {
                confirm_pass_et.setError("Please confirm your password");
                confirm_pass_et.requestFocus();
                return;
            }


            mAuth.signInWithEmailAndPassword(email, password);


//
//        if (username.isEmpty()){
//            editTextUsername.setError("Username is required");
//            editTextUsername.requestFocus();
//            return;
//        }
//
//        if (email.isEmpty()){
//            editTextEmail.setError("Email is required");
//            editTextEmail.requestFocus();
//            return;
//        }

//
//        if (password.isEmpty()){
//            editTextPassword.setError("Password is required");
//            editTextPassword.requestFocus();
//            return;
//        }

        progressbar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            String email = user.getEmail();
                            String username = user.getUid();
                            User user=new User(username,email);


                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>()

//
//                            if(task.isSuccessful()){
//                            FirebaseUser user = mAuth.getCurrentUser();
//
//                            HashMap<Object, String> hashMap = new HashMap<>();
//                            hashMap.put("email", email_et.getText().toString());
//                            hashMap.put("name", username_et.getText().toString());
//
//                            FirebaseDatabase.getInstance().getReference("Users")
//                                    .child(email_et.getText().toString())
//                                    .setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>()
                            {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(RegistrationActivity.this,"User has been registered successfully!",Toast.LENGTH_LONG).show();
                                        progressbar.setVisibility(View.VISIBLE);
                                    }else{
                                        Toast.makeText(RegistrationActivity.this,"Failed to register! Try again!",Toast.LENGTH_LONG).show();
                                        progressbar.setVisibility(View.VISIBLE);
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(RegistrationActivity.this,"Failed to register! Try again!",Toast.LENGTH_LONG).show();
                            progressbar.setVisibility(View.VISIBLE);
                        }
                    }
                });


    }
}