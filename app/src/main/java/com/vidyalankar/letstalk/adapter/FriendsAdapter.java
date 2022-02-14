package com.vidyalankar.letstalk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.model.FriendsModel;

import java.util.ArrayList;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.viewHolder> {

    Context context;
    ArrayList<FriendsModel> list;

    public FriendsAdapter(Context context, ArrayList<FriendsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.friends_recycler_view, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        FriendsModel friendsModel = list.get(position);
//        Picasso.get()
//                .load(friendsModel.getProfilepic())
//                .placeholder(R.drawable.profile_icon)
//                .into(holder.profile_image);
//        holder.username.setText(user.getUsername());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView profile_image;
        TextView username;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            profile_image= itemView.findViewById(R.id.friends_rv_profile);
            username= itemView.findViewById(R.id.friend_rv_username);
        }
    }



}
