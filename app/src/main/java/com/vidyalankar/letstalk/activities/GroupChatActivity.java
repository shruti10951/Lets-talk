package com.vidyalankar.letstalk.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.adapter.GroupChatAdapter;
import com.vidyalankar.letstalk.model.ChatModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class GroupChatActivity extends AppCompatActivity {

    ImageView back_btn, send_msg;
    TextView grpName, exitChat;
    EditText message;
    RecyclerView grpChatRv;
    String senderId;
//    String receiverId;
    Intent intent;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        back_btn= findViewById(R.id.back_btn_grp_chat);
        send_msg= findViewById(R.id.send_message_grp);
        grpName= findViewById(R.id.name_grp_chat_activity);
        message= findViewById(R.id.enter_message_grp);
        grpChatRv= findViewById(R.id.grp_chat_activity_rv);
        exitChat= findViewById(R.id.exit_chat);

        auth= FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();

        intent= getIntent();
        String groupName= intent.getStringExtra("grpId");
        grpName.setText(groupName);

        senderId= auth.getUid();

        final ArrayList<ChatModel> chatModels= new ArrayList<>();
        final GroupChatAdapter grpChatAdapter= new GroupChatAdapter(chatModels, this);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        grpChatRv.setLayoutManager(layoutManager);
        grpChatRv.setNestedScrollingEnabled(false);
        grpChatRv.setAdapter(grpChatAdapter);

        database.getReference().child("Group chat")
                .child(groupName)
                .child("chats")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        chatModels.clear();
                        for(DataSnapshot snapshot1 : snapshot.getChildren()){
                            ChatModel chatModel= snapshot1.getValue(ChatModel.class);
                            chatModels.add(chatModel);
                        }
                        grpChatAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GroupChatActivity.super.onBackPressed();
            }
        });

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date= new Date();

        send_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String msg= message.getText().toString();
                ChatModel chatModel= new ChatModel(senderId,msg);
                chatModel.setTime(new Date().getTime());
//                chatModel.setTime(formatter.format(date));
                message.setText("");

                FirebaseDatabase.getInstance().getReference()
                        .child("Group chat")
                        .child(groupName)
                        .child("chats")
                        .push()
                        .setValue(chatModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                });
            }
        });

        exitChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(GroupChatActivity.this, SweetAlertDialog.BUTTON_CONFIRM)
                        .setTitleText("Exit?")
                        .setContentText("Are you sure yo want to exit?")
                        .setConfirmText("Yes!")
                        .setCustomImage(R.drawable.info_icon)
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {

                                DatabaseReference reference= FirebaseDatabase.getInstance().getReference()
                                        .child("Group chat")
                                        .child(groupName)
                                        .child("group members")
                                        .child(FirebaseAuth.getInstance().getUid());
                                reference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(GroupChatActivity.this, "Exit successful...", Toast.LENGTH_SHORT).show();
                                        GroupChatActivity.super.onBackPressed();
                                        sweetAlertDialog.dismissWithAnimation();
                                    }
                                });
                            }
                        }).setCancelButton("No!", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                }).show();
            }
        });

    }
}