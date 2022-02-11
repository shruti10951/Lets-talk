package com.vidyalankar.letstalk.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vidyalankar.letstalk.model.FollowersModel;

import java.util.ArrayList;

public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.viewHolder> {

    Context context;
    ArrayList<FollowersModel> list;

    public FollowersAdapter(Context context, ArrayList<FollowersModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        FollowersModel model= list.get(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder
    {
        public viewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

}
