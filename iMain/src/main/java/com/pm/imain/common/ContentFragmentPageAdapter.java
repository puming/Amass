package com.pm.imain.common;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * @author pmcho
 */
public class ContentFragmentPageAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;

    public ContentFragmentPageAdapter(FragmentManager fragmentManager
            , List<Fragment> fragments) {
        super(fragmentManager);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return isEmpty() ? null : mFragments.get(position);
    }

    @Override
    public int getCount() {
        return isEmpty() ? 0 : mFragments.size();
    }

    public boolean isEmpty() {
        return mFragments == null;
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    public List<Fragment> getFragments() {
        return mFragments;
    }
}
