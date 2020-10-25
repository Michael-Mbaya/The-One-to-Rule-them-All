package com.example.lotrcharacters.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.lotrcharacters.models.Doc;
import com.example.lotrcharacters.ui.CharDetailFragment;

import java.util.List;

public class CharacterPagerAdapter extends FragmentPagerAdapter {
    private List<Doc> mRestaurants;

    public CharacterPagerAdapter(FragmentManager fm, int behavior, List<Doc> restaurants) {
        super(fm, behavior);
        mRestaurants = restaurants;
    }

    @Override
    public Fragment getItem(int position) {
        return CharDetailFragment.newInstance(mRestaurants.get(position));
    }

    @Override
    public int getCount() {
        return mRestaurants.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mRestaurants.get(position).getName();
    }
}
