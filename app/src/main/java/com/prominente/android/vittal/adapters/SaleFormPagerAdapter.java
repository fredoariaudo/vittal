package com.prominente.android.vittal.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.prominente.android.vittal.constants.ExtraKeys;

import java.util.ArrayList;

public class SaleFormPagerAdapter extends FragmentPagerAdapter
{
    ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    public SaleFormPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> fragments)
    {
        super(fragmentManager);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position)
    {
        return fragments.get(position);
    }

    @Override
    public int getCount()
    {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return fragments.get(position).getArguments().getString(ExtraKeys.FORM_TAB_TITLE);
    }
}
