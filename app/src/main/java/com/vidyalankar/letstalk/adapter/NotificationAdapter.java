package com.vidyalankar.letstalk.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.activities.CommentActivity;
import com.vidyalankar.letstalk.model.NotificationModel;
import com.vidyalankar.letstalk.model.UserModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.viewHolder>{

    ArrayList<NotificationModel> list;
    Context context;

    public NotificationAdapter(ArrayList<NotificationModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView profile;
        TextView notificationMessage, time;
        ConstraintLayout notificationOpen;

        public viewHolder(@NonNull View itemView) {

            super(itemView);

            profile= itemView.findViewById(R.id.notification_profile_user);
            notificationMessage= itemView.findViewById(R.id.notification_message);
            time= itemView.findViewById(R.id.notification_time);
            notificationOpen= itemView.findViewById(R.id.notification_open);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        NotificationModel notificationModel= list.get(position);

        String type= notificationModel.getType();

        FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(notificationModel.getNotificationBy())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel= snapshot.getValue(UserModel.class);
                        Picasso.get().load(userModel.getProfilePic())
                                .placeholder(R.drawable.user_profile_default)
                                .into(holder.profile);

                        Date date= new Date(notificationModel.getNotificationAt());
                        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                        holder.time.setText(formatter.format(date));

                        if (notificationModel.getType().equals("like")){
                            holder.notificationMessage.setText(Html.fromHtml("<b>" +userModel.getUsername()+"</b>" + " liked your post"));
                        }else if(notificationModel.getType().equals("comment")){
                            holder.notificationMessage.setText(Html.fromHtml("<b>" +userModel.getUsername()+"</b>" + " commented on your post"));
                        }else {
                            holder.notificationMessage.setText(Html.fromHtml("<b>" +userModel.getUsername()+"</b>" + " started following you"));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        holder.notificationOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!type.equals("follow")) {
                    FirebaseDatabase.getInstance().getReference()
                            .child("Notification")
                            .child(notificationModel.getPostedBy())
                            .child(notificationModel.getNotificationId())
                            .child("checkOpen")
                            .setValue(true);
                    holder.notificationOpen.setBackgroundDrawable(ContextCompat.getDrawable(context, R.color.white));
                    Intent intent = new Intent(context, CommentActivity.class);
                    intent.putExtra("postId", notificationModel.getPostId());
                    intent.putExtra("postedBy", notificationModel.getPostedBy());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }else{
                    FirebaseDatabase.getInstance().getReference()
                            .child("Notification")
                            .child(FirebaseAuth.getInstance().getUid())
                            .child(notificationModel.getNotificationId())
                            .child("checkOpen")
                            .setValue(true);
                    holder.notificationOpen.setBackgroundDrawable(ContextCompat.getDrawable(context, R.color.white));
                }
            }
        });

        Boolean checkOpen= notificationModel.isCheckOpen();
        if(checkOpen== true)
        {
            holder.notificationOpen.setBackgroundDrawable(ContextCompat.getDrawable(context, R.color.white));
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.notification_recycler_view, parent, false);
        return new viewHolder(view);
    }

}
