package com.vidyalankar.letstalk.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.adapter.ChatAdapter;
import com.vidyalankar.letstalk.model.ChatModel;
import com.vidyalankar.letstalk.model.UserModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    TextView username;
    ImageView profile, back, send;
    EditText message;
    Intent intent;
    String followedTo;
    RecyclerView chatRv;
    FirebaseDatabase database;
    FirebaseAuth auth;
    String receiverId;
    String senderID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        username= findViewById(R.id.username_chat_activity);
        profile= findViewById(R.id.user_profile_chat_activity);
        message= findViewById(R.id.enter_message);
        chatRv= findViewById(R.id.chat_activity_rv);
        back= findViewById(R.id.back_btn_chat_detailed);
        send= findViewById(R.id.send_message);

        intent= getIntent();
        followedTo= intent.getStringExtra("followedTo");

        database= FirebaseDatabase.getInstance();
        auth= FirebaseAuth.getInstance();
        senderID= auth.getUid();
        receiverId= followedTo;

        database.getReference()
                .child("Users")
                .child(followedTo)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel= snapshot.getValue(UserModel.class);
                        Picasso.get().load(userModel.getProfilePic())
                                .placeholder(R.drawable.user_profile_default)
                                .into(profile);
                        username.setText(userModel.getUsername());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatActivity.super.onBackPressed();
            }
        });

        final ArrayList<ChatModel> chatModels= new ArrayList<>();

        final ChatAdapter chatAdapter= new ChatAdapter(chatModels, this);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        chatRv.setLayoutManager(layoutManager);
        chatRv.setNestedScrollingEnabled(false);
        chatRv.setAdapter(chatAdapter);

        final String senderRoom= senderID + receiverId;
        final String receiverRoom= receiverId + senderID;

        database.getReference().child("Chats")
                .child(senderRoom)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        chatModels.clear();
                        for(DataSnapshot snapshot1 : snapshot.getChildren()){
                            ChatModel chatModel= snapshot1.getValue(ChatModel.class);
                            chatModels.add(chatModel);
                        }
                        chatAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date= new Date();


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!message.equals(null)) {
                    String msg = message.getText().toString();
                    ChatModel chatModel = new ChatModel(senderID, msg);
//                chatModel.setTime(formatter.format(date));
                    chatModel.setTime(new Date().getTime());
                    message.setText("");

                    FirebaseDatabase.getInstance().getReference()
                            .child("Chats")
                            .child(senderRoom)
                            .push()
                            .setValue(chatModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            database.getReference().child("Chats")
                                    .child(receiverRoom)
                                    .push()
                                    .setValue(chatModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                }
                            });
                        }
                    });
                }else{
                    Toast.makeText(ChatActivity.this, "Enter something...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}