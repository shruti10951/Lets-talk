package com.vidyalankar.letstalk.fragments;

<<<<<<< HEAD
=======
import android.content.Intent;
>>>>>>> fca3ef4d7fd6cd5ea86fdd30d99fd37873736910
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD

import com.vidyalankar.letstalk.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LetsTalkCommunityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LetsTalkCommunityFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

=======
import android.widget.TextView;

import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.activities.GroupChatActivity;

public class LetsTalkCommunityFragment extends Fragment {
>>>>>>> fca3ef4d7fd6cd5ea86fdd30d99fd37873736910
    public LetsTalkCommunityFragment() {
        // Required empty public constructor
    }

<<<<<<< HEAD
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LetsTalkCommunityFragment newInstance(String param1, String param2) {
        LetsTalkCommunityFragment fragment = new LetsTalkCommunityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
=======

    TextView grp;
>>>>>>> fca3ef4d7fd6cd5ea86fdd30d99fd37873736910

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
=======
>>>>>>> fca3ef4d7fd6cd5ea86fdd30d99fd37873736910
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
<<<<<<< HEAD
        return inflater.inflate(R.layout.fragment_lets_talk_community, container, false);
=======
        View view = inflater.inflate(R.layout.fragment_lets_talk_community, container, false);
        grp= view.findViewById(R.id.grp);

        grp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getContext(), GroupChatActivity.class);
                startActivity(intent);
            }
        });

        return view;
>>>>>>> fca3ef4d7fd6cd5ea86fdd30d99fd37873736910
    }
}