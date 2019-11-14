package com.example.capstonmaster.metoring;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.capstonmaster.board.advice_board.Advicefragment;
import com.example.capstonmaster.board.department_board.Departmentfragment;
import com.example.capstonmaster.board.free_board.Freefragment;
import com.example.capstonmaster.board.promote_board.PromoteFragment;
import com.example.capstonmaster.board.used_board.UsedFragment;
import com.example.capstonmaster.metoring.MentoFragment;

public class MentoPageAdapter extends FragmentStatePagerAdapter {
    public MentoPageAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MentoFragment();

            default:
                return new MentoFragment();
        }
    }

    @Override
    public int getCount() {
        return 1;
    }
}
