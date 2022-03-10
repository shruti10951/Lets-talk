package com.vidyalankar.letstalk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.model.PostModel;
import com.vidyalankar.letstalk.model.UserModel;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.viewHolder> {

    ArrayList<PostModel> list;
    Context context;

    public ProfileAdapter(ArrayList<PostModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.dashboard_recycler_view,parent,false);
        return new ProfileAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        PostModel postModel = list.get(position);

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
                holder.type.setText(postModel.getType());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(view.getContext(), SweetAlertDialog.BUTTON_CONFIRM)
                        .setTitleText("Delete?")
                        .setContentText("Are you sure you want to delete this post?")
                        .setConfirmText("Yes")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
                                Query deletePostQuery= reference.child("Posts").orderByChild("postedAt").equalTo(postModel.getPostedAt());
                                deletePostQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                                            dataSnapshot.getRef().removeValue();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Toast.makeText(context, "Something went wrong...", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        }).setCancelButton("No", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                }).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView profile_image, delete;
        TextView username, like, comment, post, type;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profile_image= itemView.findViewById(R.id.user_profile_comment);
            post= itemView.findViewById(R.id.user_post_comment);

            username= itemView.findViewById(R.id.username_comment);
            like= itemView.findViewById(R.id.likes_comment);
            comment= itemView.findViewById(R.id.user_comments_comment);
            delete= itemView.findViewById(R.id.delete_post);
            type= itemView.findViewById(R.id.type_post);

            delete.setVisibility(View.VISIBLE);
        }
    }
}
