package com.adoptame.andrearodriguez.adoptame.otrosdetail.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.adoptame.andrearodriguez.adoptame.otrosdetail.fundacion.FundacionOtroFragment;
import com.adoptame.andrearodriguez.adoptame.otrosdetail.otrosfragment.OtrosFragment;

/**
 * Created by andrearodriguez on 2/17/17.
 */
public class PagerAdapterOtros extends FragmentStatePagerAdapter{

    int mNumOfTabs;

    public PagerAdapterOtros(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                OtrosFragment tab1 = new OtrosFragment();
                return tab1;
            case 1:
                FundacionOtroFragment tab2 = new FundacionOtroFragment();
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