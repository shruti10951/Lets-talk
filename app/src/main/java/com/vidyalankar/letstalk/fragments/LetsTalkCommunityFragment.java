package com.vidyalankar.letstalk.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.activities.GroupChatActivity;
import com.vidyalankar.letstalk.model.ChatModel;
import com.vidyalankar.letstalk.model.UserModel;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LetsTalkCommunityFragment extends Fragment {
    public LetsTalkCommunityFragment() {
        // Required empty public constructor
    }


    TextView group1, group2, group3, group4, group5;
    String groupName;
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
    Date date= new Date();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lets_talk_community, container, false);
//        group1= view.findViewById(R.id.grp1);
//        group2= view.findViewById(R.id.grp2);
//        group3= view.findViewById(R.id.grp3);
//        group4= view.findViewById(R.id.grp4);
//        group5= view.findViewById(R.id.grp5);

        View.OnClickListener mListener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.grp1:
                        groupName="Group 1";
                        openChat();
                        break;
                    case R.id.grp2:
                        groupName="Group 2";
                        openChat();
                        break;
                    case R.id.grp3:
                        groupName= "Group 3";
                        openChat();
                        break;
                    case R.id.grp4:
                        groupName="Group 4";
                        openChat();
                        break;
                    case R.id.grp5:
                        groupName= "Group 5";
                        openChat();
                        break;
                }
            }
        };

        view.findViewById(R.id.grp1).setOnClickListener(mListener);
        view.findViewById(R.id.grp2).setOnClickListener(mListener);
        view.findViewById(R.id.grp3).setOnClickListener(mListener);
        view.findViewById(R.id.grp4).setOnClickListener(mListener);
        view.findViewById(R.id.grp5).setOnClickListener(mListener);

        return view;
    }

    private void openChat() {
        FirebaseDatabase.getInstance().getReference()
                .child("Group chat")
                .child(groupName)
                .child("group members")
                .child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            Intent intent= new Intent(getContext(), GroupChatActivity.class);
                            intent.putExtra("grpId", groupName);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }else{
                            new SweetAlertDialog(getContext(), SweetAlertDialog.BUTTON_CONFIRM)
                                    .setTitleText("Are you sure you want to enter?")
                                    .setContentText("Too much mention of sensitive content like self harm, nudity, means of suicide or memes targeting groups of people can affect other users. Such messages will be removed and your account may be suspended. Please make sure you do not include those things! If you need immediate professional help please call the emergency number in your area or dial the number provided in Let's Talk!")
                                    .setConfirmText("Accept!")
                                    .setCustomImage(R.drawable.info_icon)
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            FirebaseDatabase.getInstance().getReference()
                                                    .child("Group chat")
                                                    .child(groupName)
                                                    .child("group members")
                                                    .child(FirebaseAuth.getInstance().getUid())
                                                    .setValue(true)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            sweetAlertDialog.dismissWithAnimation();
                                                            Intent intent= new Intent(getContext(), GroupChatActivity.class);
                                                            intent.putExtra("grpId", groupName);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                            startActivity(intent);
                                                        }
                                                    });
                                        }
                                    }).setCancelButton("Deny!", new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismissWithAnimation();
                                }
                            }).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}