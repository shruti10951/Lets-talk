package com.vidyalankar.letstalk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DashboardActivity extends AppCompatActivity {

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

        BottomNavigationView dashboardNavigation = findViewById(R.id.bottomNavigationView);
        dashboardNavigation.setOnItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView2, new HomeFragment()).commit();

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