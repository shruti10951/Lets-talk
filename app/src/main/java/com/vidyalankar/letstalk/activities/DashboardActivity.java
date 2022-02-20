package com.vidyalankar.letstalk.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.vidyalankar.letstalk.fragments.AboutLetsTalkFragment;
import com.vidyalankar.letstalk.fragments.AddPostFragment;
import com.vidyalankar.letstalk.fragments.CalmMelodiesFragment;
import com.vidyalankar.letstalk.fragments.NotificationFragment;
import com.vidyalankar.letstalk.fragments.HelpMeCalmDownFragment;
import com.vidyalankar.letstalk.fragments.HomeFragment;
import com.vidyalankar.letstalk.fragments.INeedHelpFragment;
import com.vidyalankar.letstalk.fragments.ProfileFragment;
import com.vidyalankar.letstalk.R;
import com.vidyalankar.letstalk.fragments.LetsTalkCommunityFragment;
import com.vidyalankar.letstalk.fragments.UsersFragment;
import com.vidyalankar.letstalk.fragments.WellnessCenterFragment;
import com.vidyalankar.letstalk.model.UserModel;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout dashboard_drawer;

    FirebaseUser user;
    DatabaseReference reference;

    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar dashboard_toolbar= findViewById(R.id.dashboard_toolbar);
        setSupportActionBar(dashboard_toolbar);

        dashboard_drawer= findViewById(R.id.dashboard_layout);

        user= FirebaseAuth.getInstance().getCurrentUser();
        userID= user.getUid();
        reference= FirebaseDatabase.getInstance().getReference("Users");

        ActionBarDrawerToggle dashboard_toggle= new ActionBarDrawerToggle(this, dashboard_drawer, dashboard_toolbar,
                R.string.open,R.string.close);
        dashboard_drawer.addDrawerListener(dashboard_toggle);
        dashboard_toggle.syncState();

        BottomNavigationView dashboardBottomNavigation = findViewById(R.id.bottomNavigationView);
        dashboardBottomNavigation.setOnItemSelectedListener(navListener);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView2, new HomeFragment()).commit();
        }

        NavigationView dashboardNavigation = findViewById(R.id.dashboard_nav_view);
        View hView= dashboardNavigation.getHeaderView(0);
        TextView userNameDashNav= (TextView) hView.findViewById(R.id.user_name_dash_nav);
        TextView userEmailDashNav= (TextView) hView.findViewById(R.id.user_mail_dash_nav);
        ImageView userProfilePic= (ImageView) hView.findViewById(R.id.user_profile_dash_nav);
        reference.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userModelProfile = snapshot.getValue(UserModel.class);
                if(userModelProfile !=null)
                {
                    String userName= userModelProfile.username;
                    String userEmail= userModelProfile.email;

                    Picasso.get().load(userModelProfile.getProfilePic())
                            .placeholder(R.drawable.user_profile_default)
                            .into(userProfilePic);

                    userNameDashNav.setText(userName);
                    userEmailDashNav.setText(userEmail);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dashboardNavigation.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.letsTalkCommunityFragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new LetsTalkCommunityFragment()).addToBackStack(null).commit();
                break;
            case R.id.wellnessCenterFragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new WellnessCenterFragment()).addToBackStack(null).commit();
                break;
            case R.id.calmMelodiesFragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new CalmMelodiesFragment()).addToBackStack(null).commit();
                break;
            case R.id.helpMeCalmDownFragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new HelpMeCalmDownFragment()).addToBackStack(null).commit();
                break;
            case R.id.INeedHelpFragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new INeedHelpFragment()).addToBackStack(null).commit();
                break;
            case R.id.aboutLetsTalkFragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, new AboutLetsTalkFragment()).addToBackStack(null).commit();
                break;
            case R.id.logout:
                logoutUser();
                break;
        }
        dashboard_drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void logoutUser()
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(DashboardActivity.this);

        builder.setMessage("Are you sure you want to log out?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(DashboardActivity.this, "Logging out successful...", Toast.LENGTH_LONG).show();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
            }
        }).setNegativeButton("No",null);
        AlertDialog alert= builder.create();
        alert.show();
    }

//INCOMPLETE!!!! --->>>

    @Override
    public void onBackPressed() {
        android.app.Fragment test= getFragmentManager().findFragmentById(R.id.fragmentContainerView2);
        if(dashboard_drawer.isDrawerOpen(GravityCompat.START))
        {
            dashboard_drawer.closeDrawer(GravityCompat.START);
        }
        else {
            AlertDialog.Builder builder= new AlertDialog.Builder(DashboardActivity.this);

            builder.setTitle("Exit?").setMessage("Are you sure?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            DashboardActivity.super.onBackPressed();
                        }
                    }).setNegativeButton("No",null);
            AlertDialog alert= builder.create();
            alert.show();
        }
    }

    //SOLVE THIS!!! <<<----

    private NavigationBarView.OnItemSelectedListener navListener= new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment= null;

            switch (item.getItemId())
            {
                case R.id.homeFragment:
                    selectedFragment= new HomeFragment();
                    break;
                case R.id.usersFragment:
                    selectedFragment= new UsersFragment();
                    break;
                case R.id.addPostFragment:
                    selectedFragment= new AddPostFragment();
                    break;
                case R.id.notificationFragment:
                    selectedFragment= new NotificationFragment();
                    break;
                case R.id.profileFragment:
                    selectedFragment= new ProfileFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView2, selectedFragment).commit();
            return true;
        }
    };


}