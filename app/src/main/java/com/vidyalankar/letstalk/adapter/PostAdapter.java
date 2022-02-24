package com.vidyalankar.letstalk.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.activities.CommentActivity;
import com.vidyalankar.letstalk.activities.DashboardActivity;
import com.vidyalankar.letstalk.fragments.UserProfileFragment;
import com.vidyalankar.letstalk.model.NotificationModel;
import com.vidyalankar.letstalk.model.PostModel;
import com.vidyalankar.letstalk.model.UserModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.viewHolder> {

    ArrayList<PostModel> list;
    Context context;

    public PostAdapter(ArrayList<PostModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.dashboard_recycler_view,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        PostModel postModel = list.get(position);

//        postModel.setPost(postModel.getPost());

        FirebaseDatabase.getInstance().getReference().child("Users")
                .child(postModel.getPostedBy()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userModel = snapshot.getValue(UserModel.class);
                Picasso.get()
                        .load(userModel.getProfilePic())
                        .placeholder(R.drawable.user_profile_default)
                        .into(holder.profile_image);
                holder.username.setText(userModel.getUsername());
                holder.post.setText(postModel.getPost());
                holder.like.setText(postModel.getPostLikes()+"");
                holder.comment.setText(postModel.getCommentCount()+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date= new Date();

        FirebaseDatabase.getInstance().getReference()
                .child("Posts")
                .child(postModel.getPostId())
                .child("likes")
                .child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                        {
                            holder.like.setCompoundDrawablesWithIntrinsicBounds(R.drawable.liked_icon, 0,0,0);
                        }else{
                            holder.like.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    FirebaseDatabase.getInstance().getReference()
                                            .child("Posts")
                                            .child(postModel.getPostId())
                                            .child("likes")
                                            .child(FirebaseAuth.getInstance().getUid())
                                            .setValue(true)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    FirebaseDatabase.getInstance().getReference()
                                                            .child("Posts")
                                                            .child(postModel.getPostId())
                                                            .child("postLikes")
                                                            .setValue(postModel.getPostLikes()+1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            holder.like.setCompoundDrawablesWithIntrinsicBounds(R.drawable.liked_icon, 0,0,0);
                                                            Toast.makeText(view.getContext(), "You liked this post!", Toast.LENGTH_SHORT).show();

                                                            NotificationModel notificationModel= new NotificationModel();

                                                            notificationModel.setNotificationBy(FirebaseAuth.getInstance().getUid());
                                                            notificationModel.setNotificationAt(new Date().getTime());
                                                            notificationModel.setPostId(postModel.getPostId());
                                                            notificationModel.setPostedBy(postModel.getPostedBy());
                                                            notificationModel.setType("like");

                                                            FirebaseDatabase.getInstance().getReference()
                                                                    .child("Notification")
                                                                    .child(postModel.getPostedBy())
                                                                    .push()
                                                                    .setValue(notificationModel);
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
                args.putString("userId", postModel.getPostedBy());
                userProfileFragment.setArguments(args);
                ((DashboardActivity)context)
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainerView2, userProfileFragment, "UserProfileFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, CommentActivity.class);
                intent.putExtra("postId", postModel.getPostId());
                intent.putExtra("postedBy", postModel.getPostedBy());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView profile_image;
        TextView username, like, comment, post;
        //Recycler define


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profile_image= itemView.findViewById(R.id.user_profile_comment);
            post= itemView.findViewById(R.id.user_post_comment);
            username= itemView.findViewById(R.id.username_comment);
            like= itemView.findViewById(R.id.likes_comment);
            comment= itemView.findViewById(R.id.user_comments_comment);
            //id

        }
    }

}
