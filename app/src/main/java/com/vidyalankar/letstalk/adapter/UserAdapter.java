package com.vidyalankar.letstalk.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.activities.DashboardActivity;
import com.vidyalankar.letstalk.fragments.UserProfileFragment;
import com.vidyalankar.letstalk.model.FollowingModel;
import com.vidyalankar.letstalk.model.FriendsModel;
import com.vidyalankar.letstalk.model.NotificationModel;
import com.vidyalankar.letstalk.model.UserModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.viewHolder> {

    Context context;
    ArrayList<UserModel> list;

    public UserAdapter(Context context, ArrayList<UserModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.users_recycler_view, parent, false);
        return new viewHolder(view);
    }

    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
    Date date= new Date();

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        UserModel userModel = list.get(position);
        Picasso.get()
                .load(userModel.getProfilePic())
                .placeholder(R.drawable.user_profile_default)
                .into(holder.profile_image);
        holder.username.setText(userModel.getUsername());

//        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
//        String userId= user.getUid();

        FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(FirebaseAuth.getInstance().getUid())
                .child("Following")
                .child(userModel.getUserID())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            holder.follow_btn.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.layout_border));
                            holder.follow_btn.setText("Following");
                            holder.follow_btn.setTextColor(context.getResources().getColor(R.color.black));
                            holder.follow_btn.setEnabled(false);
                        }else {
                            holder.follow_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    FriendsModel friendsModel = new FriendsModel();
                                    friendsModel.setFollowedBy(FirebaseAuth.getInstance().getUid());
                                    friendsModel.setFollowedAt(formatter.format(date));

                                    FollowingModel followingModel=new FollowingModel();
                                    followingModel.setFollowedAt(formatter.format(date));
                                    followingModel.setFollowedTo(userModel.getUserID());

                                    FirebaseDatabase.getInstance().getReference()
                                            .child("Users")
                                            .child(userModel.getUserID())
                                            .child("followers")
                                            .child(FirebaseAuth.getInstance().getUid())
                                            .setValue(friendsModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            FirebaseDatabase.getInstance().getReference()
                                                    .child("Users")
                                                    .child(userModel.getUserID())
                                                    .child("followerCount")
                                                    .setValue(userModel.getFollowerCount()+1)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {

                                                            FirebaseDatabase.getInstance().getReference()
                                                                    .child("Users")
                                                                    .child(FirebaseAuth.getInstance().getUid())
                                                                    .child("Following")
                                                                    .child(userModel.getUserID())
                                                                    .setValue(followingModel)
                                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void unused) {

                                                                            FirebaseDatabase.getInstance().getReference()
                                                                                    .child("Users")
                                                                                    .child(FirebaseAuth.getInstance().getUid())
                                                                                    .child("followingCount")
                                                                                    .setValue(userModel.getFollowingCount()+1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                @Override
                                                                                public void onSuccess(Void unused) {
                                                                                    holder.follow_btn.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.layout_border));
                                                                                    holder.follow_btn.setText("Following");
                                                                                    holder.follow_btn.setTextColor(context.getResources().getColor(R.color.black));
                                                                                    holder.follow_btn.setEnabled(false);
                                                                                    Toast.makeText(context, "You followed "+ userModel.getUsername() + list.size() , Toast.LENGTH_SHORT).show();

                                                                                    NotificationModel notificationModel= new NotificationModel();
                                                                                    notificationModel.setNotificationBy(FirebaseAuth.getInstance().getUid());
                                                                                    notificationModel.setNotificationAt(new Date().getTime());
                                                                                    notificationModel.setType("follow");

                                                                                    FirebaseDatabase.getInstance().getReference()
                                                                                            .child("Notification")
                                                                                            .child(userModel.getUserID())
                                                                                            .push()
                                                                                            .setValue(notificationModel);
                                                                                }
                                                                            });
                                                                        }
                                                                    });


                                                        }
                                                    });
                                        }
                                    });
                                }
                            });
                        }
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
                args.putString("userId", userModel.getUserID());
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
        Button follow_btn;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profile_image= itemView.findViewById(R.id.user_profile_post);
            username= itemView.findViewById(R.id.username_post);
            follow_btn= itemView.findViewById(R.id.follow_btn_user_rv);

        }
    }

}
