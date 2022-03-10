package com.vidyalankar.letstalk.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.activities.DashboardActivity;
import com.vidyalankar.letstalk.fragments.UserProfileFragment;
import com.vidyalankar.letstalk.model.FollowingModel;
import com.vidyalankar.letstalk.model.UserModel;

import java.util.ArrayList;

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.viewHolder>{

    Context context;
    ArrayList<FollowingModel> list;

    public FollowingAdapter(Context context, ArrayList<FollowingModel> list) {
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
        FollowingModel followingModel= list.get(position);
        FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(followingModel.getFollowedTo())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel = snapshot.getValue(UserModel.class);
                        Picasso.get().load(userModel.getProfilePic())
                                .placeholder(R.drawable.user_profile_default)
                                .into(holder.profile_image);
                        holder.username.setText(userModel.getUsername());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        holder.profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserProfileFragment userProfileFragment= new UserProfileFragment();
                Bundle args= new Bundle();
                args.putString("userId", followingModel.getFollowedTo());
                userProfileFragment.setArguments(args);
                ((DashboardActivity)context)
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainerView2, userProfileFragment, "UserProfileFragment")
                        .addToBackStack(null)
                        .commit();
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

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            profile_image= itemView.findViewById(R.id.friends_rv_profile);
            username= itemView.findViewById(R.id.friend_rv_username);
        }
    }

}
