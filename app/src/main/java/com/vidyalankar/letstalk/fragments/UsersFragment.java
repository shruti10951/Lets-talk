package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.adapter.UserAdapter;
import com.vidyalankar.letstalk.model.UserModel;

import java.util.ArrayList;
import java.util.Locale;

public class UsersFragment extends Fragment {

    ArrayList<UserModel> list= new ArrayList<UserModel>();
    FirebaseAuth auth;
    FirebaseDatabase database;
    RecyclerView userRv;
    EditText search_user;

    public UsersFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth= FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_users, container, false);

        userRv= view.findViewById(R.id.users_show_rv);
        search_user= view.findViewById(R.id.search_user_et);

        UserAdapter adapter= new UserAdapter(getContext(), list);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext());
        userRv.setLayoutManager(layoutManager);
        userRv.setNestedScrollingEnabled(false);
        userRv.setAdapter(adapter);

        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    UserModel userModel = dataSnapshot.getValue(UserModel.class);
                    userModel.setUserID(dataSnapshot.getKey());
                    if(!dataSnapshot.getKey().equals(FirebaseAuth.getInstance().getUid())){
                        list.add(userModel);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        search_user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Query query= FirebaseDatabase.getInstance().getReference().child("Users").orderByChild("username")
                        .startAt(charSequence.toString().toLowerCase(Locale.ROOT))
                        .endAt(charSequence.toString().toLowerCase(Locale.ROOT)+"\uf8ff");

                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                            UserModel userModel= dataSnapshot.getValue(UserModel.class);
                            userModel.setUserID(dataSnapshot.getKey());
                            if(!dataSnapshot.getKey().equals(FirebaseAuth.getInstance().getUid())){
                                list.add(userModel);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }

}