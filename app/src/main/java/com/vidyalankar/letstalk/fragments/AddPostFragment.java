package com.vidyalankar.letstalk.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.activities.TypePostActivity;


public class AddPostFragment extends Fragment implements View.OnClickListener {
    TextView family, selfHarm, friends,
            hopes, bullying, health, work, parenting,
            education, religion, lgbt, positive,
            pregnancy, mentalHealth, addiction,
            selfCare, grief, anxiety, disabilities,
            depression, others;
    TextView typeSelected;
    Button okay;

    FirebaseAuth auth;
    FirebaseDatabase database;
    String type = "Others";

    public AddPostFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_post, container, false);

        family = (TextView) view.findViewById(R.id.familyType);
        selfHarm = (TextView) view.findViewById(R.id.selfHarmType);
        friends = (TextView) view.findViewById(R.id.friendsType);
        hopes = (TextView) view.findViewById(R.id.hopesType);
        bullying = (TextView) view.findViewById(R.id.bullyingType);
        health = (TextView) view.findViewById(R.id.healthType);
        work = (TextView) view.findViewById(R.id.workType);
        parenting = (TextView) view.findViewById(R.id.parentingType);
        education = (TextView) view.findViewById(R.id.educationType);
        religion = (TextView) view.findViewById(R.id.religionType);
        lgbt = (TextView) view.findViewById(R.id.lgbtType);
        positive = (TextView) view.findViewById(R.id.positiveType);
        pregnancy = (TextView) view.findViewById(R.id.pregnancyType);
        mentalHealth = (TextView) view.findViewById(R.id.mentalHealthType);
        selfCare = (TextView) view.findViewById(R.id.selfCareType);
        addiction = (TextView) view.findViewById(R.id.addictionType);
        grief = (TextView) view.findViewById(R.id.griefType);
        anxiety = (TextView) view.findViewById(R.id.anxietyType);
        disabilities = (TextView) view.findViewById(R.id.disabilitiesType);
        depression = (TextView) view.findViewById(R.id.depressionType);
        others = (TextView) view.findViewById(R.id.othersType);

        typeSelected = view.findViewById(R.id.type_selected);
        okay = view.findViewById(R.id.okay_selected);

        family.setOnClickListener(this);
        selfHarm.setOnClickListener(this);
        friends.setOnClickListener(this);
        hopes.setOnClickListener(this);
        bullying.setOnClickListener(this);
        health.setOnClickListener(this);
        work.setOnClickListener(this);
        parenting.setOnClickListener(this);
        education.setOnClickListener(this);
        religion.setOnClickListener(this);
        lgbt.setOnClickListener(this);
        positive.setOnClickListener(this);
        pregnancy.setOnClickListener(this);
        mentalHealth.setOnClickListener(this);
        selfCare.setOnClickListener(this);
        addiction.setOnClickListener(this);
        grief.setOnClickListener(this);
        anxiety.setOnClickListener(this);
        disabilities.setOnClickListener(this);
        depression.setOnClickListener(this);
        others.setOnClickListener(this);

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), TypePostActivity.class);
                intent.putExtra("type", type);
                startActivity(intent);

            }
        });
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.familyType:
                type = "Family";
                break;
            case R.id.selfHarmType:
                type = "Self Harm";
                break;
            case R.id.friendsType:
                type = "Friends";
                break;
            case R.id.hopesType:
                type = "Hopes";
                break;
            case R.id.bullyingType:
                type = "Bullying";
                break;
            case R.id.healthType:
                type = "Health";
                break;
            case R.id.workType:
                type = "Work";
                break;
            case R.id.parentingType:
                type = "Parenting";
                break;
            case R.id.educationType:
                type = "Education";
                break;
            case R.id.religionType:
                type = "Religion";
                break;
            case R.id.lgbtType:
                type = "LGBT";
                break;
            case R.id.positiveType:
                type = "Positive";
                break;
            case R.id.pregnancyType:
                type = "Pregnancy";
                break;
            case R.id.mentalHealthType:
                type = "Mental Health";
                break;
            case R.id.selfCareType:
                type = "Self-care";
                break;
            case R.id.addictionType:
                type = "Addiction";
                break;
            case R.id.griefType:
                type = "Grief";
                break;
            case R.id.anxietyType:
                type = "Anxiety";
                break;
            case R.id.disabilitiesType:
                type = "Disabilities";
                break;
            case R.id.depressionType:
                type = "Depression";
                break;
            case R.id.othersType:
                type = "Others";
                break;
        }
        typeSelected.setText(type);
    }
}
