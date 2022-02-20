package com.vidyalankar.letstalk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.model.ChatModel;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter{

    ArrayList<ChatModel> list;
    Context context;

    int SENDER_VIEW_TYPE=1;
    int RECEIVER_VIEW_TYPE=2;

    public ChatAdapter(ArrayList<ChatModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType== SENDER_VIEW_TYPE){
            View view= LayoutInflater.from(context).inflate(R.layout.sender_recycler_view, parent, false);
            return new SenderViewHolder(view);
        }
        else{
            View view= LayoutInflater.from(context).inflate(R.layout.receiver_recycler_view, parent, false);
            return new ReceiverViewHolder(view);
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
        if(holder.getClass()== SenderViewHolder.class){
            ((SenderViewHolder)holder).senderMsg.setText(chatModel.getMessage());
        }else{
            ((ReceiverViewHolder)holder).receiverMsg.setText(chatModel.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder{

        TextView receiverMsg, receiverTime;

        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            receiverMsg= itemView.findViewById(R.id.receiver_text);
            receiverTime= itemView.findViewById(R.id.receiver_time);
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
