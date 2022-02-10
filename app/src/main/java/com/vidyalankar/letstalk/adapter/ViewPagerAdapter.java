package com.vidyalankar.letstalk.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.vidyalankar.letstalk.fragments.AllNotificationFragment;
import com.vidyalankar.letstalk.fragments.ChatFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new AllNotificationFragment();
            case 1:
                return new ChatFragment();
            default:
                return new AllNotificationFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title= null;
        if(position==0)
        {
            title= "NOTIFICATION";
        }else if(position==1)
        {
            title= "CHAT";
        }
        return title;
    }
}
