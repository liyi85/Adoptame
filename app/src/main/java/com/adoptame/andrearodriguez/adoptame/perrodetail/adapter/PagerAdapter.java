package com.adoptame.andrearodriguez.adoptame.perrodetail.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.adoptame.andrearodriguez.adoptame.perrodetail.fundacionfragment.FundacionFragment;
import com.adoptame.andrearodriguez.adoptame.perrodetail.perrofragment.PerroFragment;

/**
 * Created by andrearodriguez on 12/20/16.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                PerroFragment tab1 = new PerroFragment();
                return tab1;
            case 1:
                FundacionFragment tab2 = new FundacionFragment();
                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
