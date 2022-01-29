package com.vidyalankar.letstalk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DashboardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        BottomNavigationView dashboardNavigation = findViewById(R.id.bottomNavigationView);
        dashboardNavigation.setOnItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView2, new HomeFragment()).commit();

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