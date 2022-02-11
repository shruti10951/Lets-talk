package com.vidyalankar.letstalk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.model.User;

import java.util.ArrayList;

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
                .placeholder(R.drawable.woman)
                .into(holder.profile_image);

        holder.username.setText(user.getUsername());

        holder.follow_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

            profile_image= itemView.findViewById(R.id.user_rv_profile);
            username= itemView.findViewById(R.id.user_rv_name);
            follow_btn= itemView.findViewById(R.id.follow_btn_user_rv);

        }
    }

}
