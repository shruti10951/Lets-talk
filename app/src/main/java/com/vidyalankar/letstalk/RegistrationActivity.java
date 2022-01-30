package com.vidyalankar.letstalk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
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


public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout mLoginContainer;
    AnimationDrawable mAnimationDrawable;

    EditText user_mail_reg, username_et, user_reg_password, user_reg_password_confirm;
    Button sign_up_reg_btn;
    TextView reg_to_login_btn;
    ProgressBar mProgressBar;

    private FirebaseAuth mAuth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mLoginContainer= (LinearLayout) findViewById(R.id.login_container);
        mAnimationDrawable= (AnimationDrawable) mLoginContainer.getBackground();
        mAnimationDrawable.setEnterFadeDuration(5000);
        mAnimationDrawable.setExitFadeDuration(2000);

        mAuth = FirebaseAuth.getInstance();

        user_mail_reg= (EditText) findViewById(R.id.user_reg_email);
        username_et= (EditText) findViewById(R.id.username_reg);
        sign_up_reg_btn= (Button) findViewById(R.id.sign_up_reg_btn);
        user_reg_password= (EditText) findViewById(R.id.user_reg_password);
        user_reg_password_confirm= (EditText) findViewById(R.id.user_reg_password_confirm);
        reg_to_login_btn= (TextView) findViewById(R.id.reg_to_login_btn);

        mProgressBar= (ProgressBar) findViewById(R.id.reg_progress_bar);

        if (mAuth != null) {
            currentUser = mAuth.getCurrentUser();
        }

        reg_to_login_btn.setOnClickListener(this);
        sign_up_reg_btn.setOnClickListener(this);

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
            case R.id.reg_to_login_btn:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.sign_up_reg_btn:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email= user_mail_reg.getText().toString().trim();
        String password= user_reg_password.getText().toString().trim();
        String username= username_et.getText().toString().trim();
        String password_confirm= user_reg_password_confirm.getText().toString().trim();

        if(email.isEmpty())
        {
            user_mail_reg.setError("Email is required!");
            user_mail_reg.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            user_mail_reg.setError("Please provide valid Email!");
            user_mail_reg.requestFocus();
            return;
        }
        if(username.isEmpty())
        {
            username_et.setError("Username is required!");
            username_et.requestFocus();
            return;
        }

        if(password.isEmpty())
        {
            user_reg_password.setError("Password is required!");
            user_reg_password.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            user_reg_password.setError("Min password length should be 6 characters!");
            user_reg_password.requestFocus();
            return;
        }

        mProgressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    User user= new User(username, email);

                    FirebaseDatabase.getInstance()
                            .getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(RegistrationActivity.this,"User has been registered successfully!", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegistrationActivity.this, DashboardActivity.class));
                            }else
                            {
                                Toast.makeText(RegistrationActivity.this, "Failed to register!", Toast.LENGTH_LONG).show();
                            }
                            mProgressBar.setVisibility(View.GONE);
                        }
                    });
                }else{
                    Toast.makeText(RegistrationActivity.this, "Failed to register user!", Toast.LENGTH_LONG).show();
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });

    }
}