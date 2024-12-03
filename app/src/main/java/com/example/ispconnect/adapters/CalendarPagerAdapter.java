package com.example.ispconnect.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ispconnect.fragments.DailyViewFragment;
import com.example.ispconnect.fragments.MonthlyViewFragment;
import com.example.ispconnect.fragments.WeeklyViewFragment;

public class CalendarPagerAdapter extends FragmentStateAdapter {
    public CalendarPagerAdapter(FragmentActivity fa) {
        super(fa);
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new DailyViewFragment();
            case 1:
                return new WeeklyViewFragment();
            case 2:
                return new MonthlyViewFragment();
            default:
                return new DailyViewFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Daily, Weekly, Monthly
    }
}

