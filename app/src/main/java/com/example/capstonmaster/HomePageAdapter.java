package com.example.capstonmaster;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.capstonmaster.home.HomeFragment;
import com.example.capstonmaster.metoring.MentoFragment;

public class HomePageAdapter extends FragmentStatePagerAdapter {
    public HomePageAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();

            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 1;
    }
}
