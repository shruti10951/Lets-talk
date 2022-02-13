package com.vidyalankar.letstalk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.model.ChatModel;

import java.util.ArrayList;

public class ChatAdapter extends  RecyclerView.Adapter<ChatAdapter.viewHolder> {

    ArrayList<ChatModel> list;
    Context context;

    public ChatAdapter(ArrayList<ChatModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.chat_users_recycler_view, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        ChatModel chatModel= list.get(position);
        holder.profile_image.setImageResource(chatModel.getProfile());
        holder.username.setText(chatModel.getName());
        holder.last_message.setText(chatModel.getLastMessage());
        holder.time.setText(chatModel.getTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView profile_image;
        TextView username, last_message, time;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profile_image= itemView.findViewById(R.id.chat_user_rv_profile);
            username= itemView.findViewById(R.id.chat_user_rv_name);
            last_message= itemView.findViewById(R.id.chat_user_rv_last_msg);
            time= itemView.findViewById(R.id.chat_user_rv_time);
        }
    }

}
