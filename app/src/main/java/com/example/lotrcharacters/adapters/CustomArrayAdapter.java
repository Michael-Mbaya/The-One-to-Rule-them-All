package com.example.lotrcharacters.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

public class CustomArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mNames;
    private String[] mRaces;    // #JoynerLucas - I'm Not Racist

    public CustomArrayAdapter(Context mContext, int resource, String[] mTracks, String[] mTrackArtists) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mNames = mTracks;
        this.mRaces = mTrackArtists;
    }

    @Override
    public Object getItem(int position) {
        String tracks = mNames[position];
        String trackArtists = mRaces[position];
        return String.format("%s \n Of Race: %s", tracks, trackArtists);
    }

    @Override
    public int getCount() {
        return mNames.length;
    }
}
