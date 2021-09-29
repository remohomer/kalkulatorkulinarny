package com.example.lesiogotuje.ui.calculator;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.lesiogotuje.R;
import com.example.lesiogotuje.ui.calculator.blg.*;
import com.example.lesiogotuje.ui.calculator.culinary.*;
import com.example.lesiogotuje.ui.calculator.dilute.*;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_calc_1, R.string.tab_text_calc_2, R.string.tab_text_calc_3};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        switch (position) {
            case 0: {
                return new CulinaryFragment();
            }
            case 1: {
                return new DiluteFragment();
            }
            case 2: {
                return new BlgFragment();
            }
            default: {
                return PlaceholderFragment.newInstance(position + 1);
            }
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}