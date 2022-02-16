package com.vidyalankar.letstalk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.model.FriendsModel;
import com.vidyalankar.letstalk.model.User;

import java.util.ArrayList;
import java.util.Date;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.viewHolder> {

    Context context;
    ArrayList<User> list;

    public UserAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.users_recycler_view, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        User user= list.get(position);
        Picasso.get()
                .load(user.getProfilePic())
                .placeholder(R.drawable.user_profile_default)
                .into(holder.profile_image);
        holder.username.setText(user.getUsername());

        holder.follow_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FriendsModel friendsModel = new FriendsModel();
                friendsModel.setFollowedBy(FirebaseAuth.getInstance().getUid());
                friendsModel.setFollowedAt(new Date().getTime());

                FirebaseDatabase.getInstance().getReference()
                        .child("Users")
                        .child(user.getUserID())
                        .child("followers")
                        .child(FirebaseAuth.getInstance().getUid())
                        .setValue(friendsModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        FirebaseDatabase.getInstance().getReference()
                                .child("Users")
                                .child(user.getUserID())
                                .child("followerCount")
                                .setValue(user.getFollowerCount()+1)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(context, "You followed "+ user.getUsername() + list.size() , Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
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
        Button follow_btn;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profile_image= itemView.findViewById(R.id.user_profile_post);
            username= itemView.findViewById(R.id.username_post);
            follow_btn= itemView.findViewById(R.id.follow_btn_user_rv);

        }
    }

}
