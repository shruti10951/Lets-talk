package com.vidyalankar.letstalk.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.activities.ChatActivity;
import com.vidyalankar.letstalk.model.FriendsModel;
import com.vidyalankar.letstalk.model.UserModel;

import java.util.ArrayList;

public class ChatListAdapter extends  RecyclerView.Adapter<ChatListAdapter.viewHolder> {

    ArrayList<FriendsModel> list;
    Context context;

    public ChatListAdapter(ArrayList<FriendsModel> list, Context context) {
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

        FriendsModel friendsModel= list.get(position);
        FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(friendsModel.getFollowedBy())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel= snapshot.getValue(UserModel.class);
                        Picasso.get().load(userModel.getProfilePic())
                                .placeholder(R.drawable.user_profile_default)
                                .into(holder.profile_image);
                        holder.username.setText(userModel.getUsername());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, ChatActivity.class);
                intent.putExtra("followedBy", friendsModel.getFollowedBy());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView profile_image;
        TextView username;
        ConstraintLayout constraintLayout;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profile_image= itemView.findViewById(R.id.chat_user_rv_profile);
            username= itemView.findViewById(R.id.chat_user_rv_name);
            constraintLayout= itemView.findViewById(R.id.chat_recycler_view);
        }
    }

}
