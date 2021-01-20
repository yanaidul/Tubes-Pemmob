package com.example.aplikasiperekamsuara.Adapters;



import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.aplikasiperekamsuara.Fragments.FileViewerFragment;
import com.example.aplikasiperekamsuara.Fragments.RecordFragment;

public class MyTabAdapter extends FragmentPagerAdapter
{

    String[] titles = {"Rekam","Simpan Rekaman"};

    public MyTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i)
        {
            case 0:
                return new RecordFragment();
            case 1:
                return new FileViewerFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        return titles[position];
    }
}
