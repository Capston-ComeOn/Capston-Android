package com.example.capstonmaster;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.capstonmaster.board.department_board.Departmentfragment;
import com.example.capstonmaster.board.free_board.Freefragment;
import com.example.capstonmaster.board.advice_board.Advicefragment;
import com.example.capstonmaster.board.promote_board.PromoteFragment;
import com.example.capstonmaster.board.used_board.UsedFragment;

public class SectionsPageAdapter extends FragmentStatePagerAdapter {
    public SectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Departmentfragment();
            case 1:
                return new Freefragment();
            case 2:
                return new Advicefragment();
            case 3:
                return new UsedFragment();
            case 4:
                return new PromoteFragment();
            default:
                return new Departmentfragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
