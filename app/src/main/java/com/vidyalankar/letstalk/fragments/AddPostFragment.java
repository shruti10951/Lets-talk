package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.model.PostModel;

import java.util.Date;

public class AddPostFragment extends Fragment {

    EditText textMessage;
    Button post_btn;
    ProgressBar progressBar;

    FirebaseAuth auth;
    FirebaseDatabase database;

    public AddPostFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth= FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_post, container, false);

        post_btn= view.findViewById(R.id.post_btn);
        textMessage= view.findViewById(R.id.user_text_post);
        progressBar= view.findViewById(R.id.progressBar);

//        database.getReference()
//                .child("Users")
//                .child(FirebaseAuth.getInstance().getUid())
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists())
//                {
//                    User user= snapshot.getValue(User.class);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);

                PostModel postModel= new PostModel();
                postModel.setPostedBy(FirebaseAuth.getInstance().getUid());
                postModel.setPost(textMessage.getText().toString());
                postModel.setPostedAt(new Date().getTime());

                database.getReference()
                        .child("Posts")
                        .push()
                        .setValue(postModel)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "Posted Successfully!", Toast.LENGTH_SHORT).show();
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragmentContainerView2, new PostSuccessfulFragment())
                                .addToBackStack(null)
                                .commit();
                    }
                });
            }
        });

        textMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text= textMessage.getText().toString();
                if(!text.isEmpty())
                {
                    post_btn.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.black));
                    post_btn.setTextColor(getContext().getResources().getColor(R.color.white));
                    post_btn.setEnabled(true);
                }else{
                    post_btn.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.greyTrans));
                    post_btn.setTextColor(getContext().getResources().getColor(R.color.white));
                    post_btn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        return view;
    }
}