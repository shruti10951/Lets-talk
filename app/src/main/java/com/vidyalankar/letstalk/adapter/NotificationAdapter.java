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
import com.vidyalankar.letstalk.model.NotificationModel;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.viewHolder>{

    ArrayList<NotificationModel> list;
    Context context;

    public NotificationAdapter(ArrayList<NotificationModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.notification_recycler_view, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        NotificationModel notificationModel= list.get(position);
        holder.profile.setImageResource(notificationModel.getProfile());
        holder.notificationMessage.setText(notificationModel.getNotificationMessage());
        holder.time.setText(notificationModel.getTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView profile;
        TextView notificationMessage, time;

        public viewHolder(@NonNull View itemView) {

            super(itemView);

            profile= itemView.findViewById(R.id.notification_profile_user);
            notificationMessage= itemView.findViewById(R.id.notification_message);
            time= itemView.findViewById(R.id.notification_time);
        }
    }

}
