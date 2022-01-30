package com.vidyalankar.letstalk;

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
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout dashboard_drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar dashboard_toolbar= findViewById(R.id.dashboard_toolbar);
        setSupportActionBar(dashboard_toolbar);

        dashboard_drawer= findViewById(R.id.dashboard_layout);

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
        dashboardNavigation.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.settingFragment:
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.dashboard_fragment_container,new SettingFragment()).addToBackStack(null).commit();
                break;
            case R.id.wellnessCenterFragment:
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.dashboard_fragment_container,new WellnessCenterFragment()).addToBackStack(null).commit();
                break;
            case R.id.calmMelodiesFragment:
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.dashboard_fragment_container,new CalmMelodiesFragment()).addToBackStack(null).commit();
                break;
            case R.id.helpMeCalmDownFragment:
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.dashboard_fragment_container,new HelpMeCalmDownFragment()).addToBackStack(null).commit();
                break;
            case R.id.INeedHelpFragment:
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.dashboard_fragment_container,new INeedHelpFragment()).addToBackStack(null).commit();
                break;
            case R.id.aboutLetsTalkFragment:
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.dashboard_fragment_container,new AboutLetsTalkFragment()).addToBackStack(null).commit();
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


    @Override
    public void onBackPressed() {
        if(dashboard_drawer.isDrawerOpen(GravityCompat.START))
        {
            dashboard_drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

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
                case R.id.chatListFragment:
                    selectedFragment= new ChatListFragment();
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