package com.vidyalankar.letstalk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mActionDrawerToggle;

    NavigationView mNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //layout variables
        mDrawerLayout= findViewById(R.id.main_drawer_layout);
        mNavigationView= findViewById(R.id.main_nav_view);

        mActionDrawerToggle= new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mActionDrawerToggle);
        mActionDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //listeners for navigation view
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("WrongConstant")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.profile) {
                    Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_LONG).show();
                    mDrawerLayout.closeDrawer(Gravity.START);
                    return true;
                } else if (item.getItemId() == R.id.setting) {
                    Toast.makeText(MainActivity.this, "Setting", Toast.LENGTH_LONG).show();
                    return true;
                } else if (item.getItemId() == R.id.wellness_center) {
                    Toast.makeText(MainActivity.this, "Wellness Center", Toast.LENGTH_LONG).show();
                    return true;
                } else if (item.getItemId() == R.id.melodies) {
                    Toast.makeText(MainActivity.this, "Calm Melodies", Toast.LENGTH_LONG).show();
                    return true;
                } else if (item.getItemId() == R.id.calm_down) {
                    Toast.makeText(MainActivity.this, "Help Me Calm Down!", Toast.LENGTH_LONG).show();
                    return true;
                } else if (item.getItemId() == R.id.help) {
                    Toast.makeText(MainActivity.this, "I Need Help", Toast.LENGTH_LONG).show();
                    return true;
                } else if (item.getItemId() == R.id.about) {
                    Toast.makeText(MainActivity.this, "About Let's Talk", Toast.LENGTH_LONG).show();
                    return true;
                } else if (item.getItemId() == R.id.logout) {
                    Toast.makeText(MainActivity.this, "Log Out", Toast.LENGTH_LONG).show();
                    return true;
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mActionDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}