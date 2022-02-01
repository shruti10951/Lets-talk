package com.vidyalankar.letstalk;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link ProfileFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class ProfileFragment extends Fragment {

    private ImageView profilePic;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
    public ProfileFragment() {
        // Required empty public constructor
    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment ProfileFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static ProfileFragment newInstance(String param1, String param2) {
//        ProfileFragment fragment = new ProfileFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

//    private FirebaseUser user;
//    private DatabaseReference reference;
//    private FirebaseAuth mAuth;
//
//    private Uri imageUri;
//    private String myUri= "";
//    private StorageTask uploadTask;
//    private StorageReference storageProfileReference;
//
//    private String userID;
//    private Button changeProfilePic, saveProfilePic;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_profile, container, false);

        profilePic= view.findViewById(R.id.profile_pic);

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePicture();
            }
        });

//        user= FirebaseAuth.getInstance().getCurrentUser();
//        reference= FirebaseDatabase.getInstance().getReference("Users");
//        userID= user.getUid();
//        storageProfileReference= FirebaseStorage.getInstance().getReference().child("Profile pic");
//
//        final TextView userNameTextView= (TextView) view.findViewById(R.id.profile_username);
//        final TextView userEmailTextView= (TextView) view.findViewById(R.id.profile_email);
//        profileCircleImageView= (CircleImageView) view.findViewById(R.id.profile_image);
//        changeProfilePic= (Button) view.findViewById(R.id.change_profile_pic);
//
//
//        changeProfilePic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Bundle bundle= new Bundle();
//               // bundle.putString("Profile", );
//
//            }
//        });




//        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                User userProfile= snapshot.getValue(User.class);
//                if(userProfile!= null)
//                {
//                    String userName = userProfile.username;
//                    String userEmail = userProfile.email;
//
//                    userNameTextView.setText(userName);
//                    userEmailTextView.setText(userEmail);
//                }
//            }

//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_LONG).show();
//            }
//        });

        // Inflate the layout for this fragment
        return view;
    }

    private void choosePicture() {

        Intent galleryIntent= new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");


    }




}