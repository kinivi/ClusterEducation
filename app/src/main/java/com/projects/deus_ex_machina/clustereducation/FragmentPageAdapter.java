package com.projects.deus_ex_machina.clustereducation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Nikita Kiselov on 13-Nov-17.
 * ClusterEducation
 */

public class FragmentPageAdapter extends FragmentPagerAdapter {


    public FragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    //return fragment to adapter
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new DashboardFragment();
            case 1:
                return new SubjectsFragment();
            case 2:
                return new SuggestionsFragment();
            default:
                return new DashboardFragment();
        }
    }

    //return count of fragments
    @Override
    public int getCount() {
        return 3;
    }


}
