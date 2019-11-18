package com.example.capstonmaster.history;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class HistoryPagerAdapter extends FragmentStatePagerAdapter {

  public HistoryPagerAdapter(@NonNull FragmentManager fm) {
    super(fm);
  }

  @NonNull
  @Override
  public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return new HistoryFragment();

      default:
        return new HistoryFragment();
    }
  }

  @Override
  public int getCount() {
    return 1;
  }
}
