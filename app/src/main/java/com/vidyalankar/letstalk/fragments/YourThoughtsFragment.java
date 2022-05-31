package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.model.FeedbackModel;
import com.vidyalankar.letstalk.model.UserModel;

import java.util.Date;

public class YourThoughtsFragment extends Fragment {

    EditText thoughts_et;
    Button submit_btn;

    TextView username1, username2, username3;
    TextView feedback1, feedback2, feedback3;
    ImageView image1, image2, image3;
    TextView more;
    String feedback = "";

    int i = 0;
    int j = 0;

    String[] feedbacks = new String[5];
    String[] feedbackBy = new String[5];

    public YourThoughtsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_your_thoughts, container, false);

        thoughts_et = view.findViewById(R.id.thoughts_et);
        submit_btn = view.findViewById(R.id.submit_thoughts);
        username1 = view.findViewById(R.id.username_card1);
        username2 = view.findViewById(R.id.username_card2);
        username3 = view.findViewById(R.id.username_card3);
        feedback1 = view.findViewById(R.id.feedback_card1);
        feedback2 = view.findViewById(R.id.feedback_card2);
        feedback3 = view.findViewById(R.id.feedback_card3);
        image1 = view.findViewById(R.id.user_profile_card1);
        image2 = view.findViewById(R.id.user_profile_card2);
        image3 = view.findViewById(R.id.user_profile_card3);
        more = view.findViewById(R.id.more_text);

        FirebaseDatabase.getInstance()
                .getReference()
                .child("Feedback")
                .orderByKey()
                .limitToLast(3)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            FeedbackModel feedbackModel = dataSnapshot.getValue(FeedbackModel.class);
                            feedbacks[i] = feedbackModel.getFeedback();
                            feedbackBy[i] = feedbackModel.getFeedbackBy();
                            i++;
                        }

                        feedback1.setText(feedbacks[0]);
                        feedback2.setText(feedbacks[1]);
                        feedback3.setText(feedbacks[2]);

                        FirebaseDatabase.getInstance()
                                .getReference()
                                .child("Users")
                                .child(feedbackBy[0])
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        UserModel userModel = snapshot.getValue(UserModel.class);
                                        Picasso.get()
                                                .load(userModel.getProfilePic())
                                                .into(image1);
                                        username1.setText(userModel.getUsername());
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                        FirebaseDatabase.getInstance()
                                .getReference()
                                .child("Users")
                                .child(feedbackBy[1])
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        UserModel userModel = snapshot.getValue(UserModel.class);
                                        Picasso.get()
                                                .load(userModel.getProfilePic())
                                                .into(image2);
                                        username2.setText(userModel.getUsername());
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                        FirebaseDatabase.getInstance()
                                .getReference()
                                .child("Users")
                                .child(feedbackBy[2])
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        UserModel userModel = snapshot.getValue(UserModel.class);
                                        Picasso.get()
                                                .load(userModel.getProfilePic())
                                                .into(image3);
                                        username3.setText(userModel.getUsername());
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        FirebaseDatabase.getInstance()
                .getReference()
                .child("Feedback")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            j++;
                        }
                        j = j - 3;
                        more.setText("+" + j);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String thoughts = thoughts_et.getText().toString();
                FeedbackModel feedbackModel = new FeedbackModel();
                feedbackModel.setFeedback(thoughts);
                feedbackModel.setFeedbackAt(new Date().getTime());
                feedbackModel.setFeedbackBy(FirebaseAuth.getInstance().getUid());
                FirebaseDatabase.getInstance().getReference()
                        .child("Feedback")
                        .push()
                        .setValue(feedbackModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getContext(), "We received your feedback!", Toast.LENGTH_SHORT).show();
                            }
                        });
                thoughts_et.setText("");
            }

        });

        return view;
    }
}