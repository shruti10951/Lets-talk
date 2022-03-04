package com.vidyalankar.letstalk.activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.model.UserModel;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

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

        Query usernameQuery= FirebaseDatabase.getInstance().getReference().child("Users").orderByChild("username").equalTo(username);

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
        if(!password.equals(password_confirm))
        {
            user_reg_password_confirm.setError("Password did not match!");
            user_reg_password_confirm.requestFocus();
            return;
        }

        mProgressBar.setVisibility(View.VISIBLE);

        usernameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getChildrenCount()>0)
                {
                    Toast.makeText(RegistrationActivity.this, "choose a different username", Toast.LENGTH_LONG).show();
                    mProgressBar.setVisibility(View.GONE);
                }
                else{

//                    mAuth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void unused) {
//                            new SweetAlertDialog(RegistrationActivity.this)
//                                    .setTitleText("Verify email")
//                                    .setContentText("Verification email has been sent. Please Check")
//                                    .show();
//                            if(mAuth.getCurrentUser().isEmailVerified()){
//                                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<AuthResult> task) {
//                                       if(task.isSuccessful()){
//                                           UserModel userModel = new UserModel(username, email);
//
//                                           FirebaseDatabase.getInstance()
//                                                   .getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                                   .setValue(userModel).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                               @Override
//                                               public void onSuccess(Void unused) {
//                                                   if(task.isSuccessful())
//                                                   {
//                                                       Toast.makeText(RegistrationActivity.this,"User has been registered successfully!", Toast.LENGTH_LONG).show();
//                                                       mProgressBar.setVisibility(View.GONE);
//                                                       startActivity(new Intent(RegistrationActivity.this, DashboardActivity.class));
//                                                   }
//                                               }
//                                           });
//                                       }
//                                    }
//                                });
//                            }
//                        }
//                    });

                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                UserModel userModel = new UserModel(username, email);

                                //send verification link

//                                FirebaseUser user= mAuth.getCurrentUser();
                                FirebaseAuth.getInstance().getCurrentUser()
                                .sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        //Toast.makeText(RegistrationActivity.this,"Verification Email has been sent.",Toast.LENGTH_LONG).show();
                                        new SweetAlertDialog(RegistrationActivity.this)
                                                .setTitleText("Verify email")
                                                .setContentText("Verification email has been sent. Please Check")
                                                .show();

                                        //if verified
//                                        if(mAuth.getCurrentUser().isEmailVerified()){
                                                FirebaseDatabase.getInstance()
                                                        .getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .setValue(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
//                                                    if(FirebaseAuth.getInstance().getCurrentUser().isEmailVerified())
//                                                if(FirebaseAuth.getInstance().getCurrentUser().isEmailVerified())
//                                                    {
                                                            startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                                                            Toast.makeText(RegistrationActivity.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                                        } else {
                                                            Toast.makeText(RegistrationActivity.this, "cfcfgvvbnbnbnmmnmnFailed to register!", Toast.LENGTH_LONG).show();
                                                        }
                                                        mProgressBar.setVisibility(View.GONE);
//                                                }
//                                                else
//                                                {
//                                                    Toast.makeText(RegistrationActivity.this, "Failed to register!", Toast.LENGTH_LONG).show();
//                                                }
//                                                mProgressBar.setVisibility(View.GONE);
                                                    }
                                                });
                                        }
                                    //}

                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG,"onFailure:Email not sent"+e.getMessage());
                                    }
                                });

                            }
//                                else{
//                                Toast.makeText(RegistrationActivity.this, "tfgbnbnnmmnmnFailed to register user!", Toast.LENGTH_LONG).show();
//                                mProgressBar.setVisibility(View.GONE);
//                            }
                        }
                    });

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RegistrationActivity.this, "Failed to register database!", Toast.LENGTH_LONG).show();
            }
        });

    }
}