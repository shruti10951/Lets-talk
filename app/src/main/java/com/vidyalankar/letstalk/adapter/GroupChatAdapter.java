package com.vidyalankar.letstalk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.model.ChatModel;
import com.vidyalankar.letstalk.model.UserModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GroupChatAdapter extends RecyclerView.Adapter {
    ArrayList<ChatModel> list;
    Context context;

    int SENDER_VIEW_TYPE=1;
    int RECEIVER_VIEW_TYPE=2;

    public GroupChatAdapter(ArrayList<ChatModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType== SENDER_VIEW_TYPE){
            View view= LayoutInflater.from(context).inflate(R.layout.sender_recycler_view, parent, false);
            return new GroupChatAdapter.SenderViewHolder(view);
        }
        else{
            View view= LayoutInflater.from(context).inflate(R.layout.grp_receiver_recycler_view, parent, false);
            return new GroupChatAdapter.ReceiverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {

        if(list.get(position).getUserId().equals(FirebaseAuth.getInstance().getUid())){
            return SENDER_VIEW_TYPE;
        }else {
            return RECEIVER_VIEW_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ChatModel chatModel= list.get(position);
        if(holder.getClass()== GroupChatAdapter.SenderViewHolder.class){
            ((GroupChatAdapter.SenderViewHolder)holder).senderMsg.setText(chatModel.getMessage());
            Date date= new Date(chatModel.getTime());
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            ((SenderViewHolder)holder).senderTime.setText(formatter.format(date));
        }else{
            FirebaseDatabase.getInstance().getReference()
                    .child("Users")
                    .child(chatModel.getUserId())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            UserModel userModel= snapshot.getValue(UserModel.class);
                            ((GroupChatAdapter.ReceiverViewHolder)holder).receiverName.setText(userModel.getUsername());
                            Date date= new Date(chatModel.getTime());
                            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                            ((ReceiverViewHolder)holder).receiverTime.setText(formatter.format(date));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
            ((GroupChatAdapter.ReceiverViewHolder)holder).receiverMsg.setText(chatModel.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder{

        TextView receiverMsg, receiverTime, receiverName;

        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            receiverMsg= itemView.findViewById(R.id.receiver_text_grp);
            receiverTime= itemView.findViewById(R.id.receiver_time_grp);
            receiverName= itemView.findViewById(R.id.username_grp);
        }
    }

    public class SenderViewHolder extends RecyclerView.ViewHolder{

        TextView senderMsg, senderTime;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            senderMsg= itemView.findViewById(R.id.sender_text);
            senderTime= itemView.findViewById(R.id.sender_time);
        }
    }
}
