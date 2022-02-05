package com.vidyalankar.letstalk;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileFragment extends Fragment {

    ImageView profilePic;
    TextView userNameTextView, userEmailTextView;
    Button editProfile;

    FirebaseUser user;
    DatabaseReference reference;

    String userID;



    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profilePic= (ImageView) view.findViewById(R.id.profile_pic);
        userNameTextView= (TextView) view.findViewById(R.id.profile_username);
        userEmailTextView= (TextView) view.findViewById(R.id.profile_email);
        editProfile= (Button) view.findViewById(R.id.edit_profile);

        user= FirebaseAuth.getInstance().getCurrentUser();
        userID= user.getUid();
        reference= FirebaseDatabase.getInstance().getReference("Users");

        reference.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile= snapshot.getValue(User.class);
                if(userProfile!=null)
                {
                    String userName= userProfile.username;
                    String userEmail= userProfile.email;

                    userNameTextView.setText(userName);
                    userEmailTextView.setText(userEmail);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView2, new EditProfile());
                fragmentTransaction.addToBackStack(null);
                Toast.makeText(getActivity(), "Edit your profile...", Toast.LENGTH_LONG).show();
                fragmentTransaction.commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}
