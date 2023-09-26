package com.vidyalankar.letstalk.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.adapter.CommentAdapter;
import com.vidyalankar.letstalk.model.CommentModel;
import com.vidyalankar.letstalk.model.NotificationModel;
import com.vidyalankar.letstalk.model.PostModel;
import com.vidyalankar.letstalk.model.UserModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CommentActivity extends AppCompatActivity {

    TextView username, post, likes, comments;
    EditText comment;
    ImageView userProfile;
    ImageView postComment;
    Intent intent;
    String postId;
    String postedBy;
    FirebaseDatabase database;
    FirebaseAuth auth;
    ArrayList<CommentModel>list= new ArrayList<>();
    RecyclerView commentRV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        username= (TextView) findViewById(R.id.username_comment);
        userProfile= (ImageView) findViewById(R.id.user_profile_comment);
        post= (TextView) findViewById(R.id.user_post_comment);
        likes= (TextView) findViewById(R.id.likes_comment);
        comments= (TextView) findViewById(R.id.user_comments_comment);
        postComment= (ImageView) findViewById(R.id.post_comment_comment);
        comment= (EditText) findViewById(R.id.enter_comment_comment);
        commentRV= (RecyclerView) findViewById(R.id.comment_rv);

        intent= getIntent();

        postId= intent.getStringExtra("postId");
        postedBy= intent.getStringExtra("postedBy");

        database= FirebaseDatabase.getInstance();
        auth= FirebaseAuth.getInstance();

        database.getReference()
                .child("Posts")
                .child(postId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        PostModel postModel= snapshot.getValue(PostModel.class);
                        post.setText(postModel.getPost());
                        likes.setText(postModel.getPostLikes()+ "");
                        comments.setText(postModel.getCommentCount()+"");
                        FirebaseDatabase.getInstance().getReference()
                                .child("Posts")
                                .child(postId)
                                .child("likes")
                                .child(FirebaseAuth.getInstance().getUid())
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(snapshot.exists()){
                                            likes.setCompoundDrawablesWithIntrinsicBounds(R.drawable.liked_icon,0,0,0);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        database.getReference().child("Users")
                .child(postedBy)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel = snapshot.getValue(UserModel.class);
                        Picasso.get().load(userModel.getProfilePic())
                                .placeholder(R.drawable.user_profile_default)
                                .into(userProfile);
                        username.setText(userModel.getUsername());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date= new Date();

        postComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CommentModel commentModel= new CommentModel();
                commentModel.setComment(comment.getText().toString());
                commentModel.setCommentedAt(new Date().getTime());
//                commentModel.setCommentedAt(formatter.format(date));
                commentModel.setCommentedBy(FirebaseAuth.getInstance().getUid());

                database.getReference().child("Posts")
                        .child(postId)
                        .child("comments")
                        .push()
                        .setValue(commentModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        database.getReference().child("Posts")
                                .child(postId)
                                .child("commentCount")
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        int commentCount=0;
                                        if(snapshot.exists())
                                        {
                                            commentCount= snapshot.getValue(Integer.class);
                                        }
                                        database.getReference().child("Posts")
                                                .child(postId)
                                                .child("commentCount")
                                                .setValue(commentCount+1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                comment.setText("");
                                                Toast.makeText(CommentActivity.this, "Commented!", Toast.LENGTH_SHORT).show();

                                                NotificationModel notificationModel= new NotificationModel();
                                                notificationModel.setNotificationBy(FirebaseAuth.getInstance().getUid());
                                                notificationModel.setNotificationAt(new Date().getTime());
                                                notificationModel.setPostId(postId);
                                                notificationModel.setPostedBy(postedBy);
                                                notificationModel.setType("comment");

                                                FirebaseDatabase.getInstance().getReference()
                                                        .child("Notification")
                                                        .child(postedBy)
                                                        .push()
                                                        .setValue(notificationModel);
                                            }
                                        });
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                    }
                });
            }
        });

        CommentAdapter adapter= new CommentAdapter(this, list);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        commentRV.setLayoutManager(layoutManager);
        commentRV.setAdapter(adapter);

        database.getReference().child("Posts")
                .child(postId)
                .child("comments")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for(DataSnapshot dataSnapshot: snapshot.getChildren())
                        {
                            CommentModel commentModel= dataSnapshot.getValue(CommentModel.class);
                            list.add(commentModel);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }
}