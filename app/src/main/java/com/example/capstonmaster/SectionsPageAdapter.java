package com.example.capstonmaster;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.capstonmaster.board.free_board.Freefragment;
import com.example.capstonmaster.board.login.Loginfragment;
import com.example.capstonmaster.board.notice_board.Noticefragment;

public class SectionsPageAdapter extends FragmentPagerAdapter {
    public SectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Loginfragment();
            case 1:
                return new Freefragment();
            case 2:
                return new Noticefragment();
            default:
                return new Freefragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
