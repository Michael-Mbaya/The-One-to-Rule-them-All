package com.example.lotrcharacters.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.lotrcharacters.models.Doc;
import com.example.lotrcharacters.ui.CharDetailFragment;

import java.util.List;

public class CharPagerAdapter extends FragmentPagerAdapter {
    private List<Doc> mDocList;

    public CharPagerAdapter(FragmentManager fm, int behavior, List<Doc> restaurants) {
        super(fm, behavior);
        mDocList = restaurants;
    }

    @Override
    public Fragment getItem(int position) {
        return CharDetailFragment.newInstance(mDocList.get(position));
    }

    @Override
    public int getCount() {
        return mDocList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDocList.get(position).getName();
    }
}
