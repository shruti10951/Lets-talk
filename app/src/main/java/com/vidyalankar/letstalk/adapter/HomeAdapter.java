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
import com.vidyalankar.letstalk.model.PostModel;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.viewHolder> {

    ArrayList<PostModel> list;
    Context context;

    public HomeAdapter(ArrayList<PostModel> list, Context context) {
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
//        holder.profile_image.setImageResource(postModel.getProfile());
//        holder.post.setText(postModel.getPostImage());
//        holder.save.setImageResource(postModel.getSave());
//        holder.username.setText(postModel.getName());
//        holder.like.setText(postModel.getLike());
//        holder.comment.setText(postModel.getComment());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView profile_image, save;
        TextView username, like, comment, post;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profile_image= itemView.findViewById(R.id.profile_user);
            post= itemView.findViewById(R.id.rv_post);
            save= itemView.findViewById(R.id.rv_save_post);
            username= itemView.findViewById(R.id.rv_user_name);
            like= itemView.findViewById(R.id.like);
            comment= itemView.findViewById(R.id.comment);

        }
    }

}
