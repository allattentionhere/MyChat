package com.allattentionhere.mychat.Helper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;

    public SectionsPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.titles.get(position);
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}

