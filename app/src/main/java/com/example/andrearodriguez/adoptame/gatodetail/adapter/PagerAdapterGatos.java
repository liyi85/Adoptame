package com.example.andrearodriguez.adoptame.gatodetail.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.andrearodriguez.adoptame.gatodetail.GatoInfoFragment.GatoInfoFragment;
import com.example.andrearodriguez.adoptame.gatodetail.fundacionFragmentgato.FundacionGatoFragment;

/**
 * Created by andrearodriguez on 12/23/16.
 */

public class PagerAdapterGatos extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PagerAdapterGatos(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                GatoInfoFragment tab1 = new GatoInfoFragment();
                return tab1;
            case 1:
                FundacionGatoFragment tab2 = new FundacionGatoFragment();
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
