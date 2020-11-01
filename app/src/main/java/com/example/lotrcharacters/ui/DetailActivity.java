package com.example.lotrcharacters.ui;

import android.os.Bundle;
import com.example.lotrcharacters.R;
import com.example.lotrcharacters.adapters.CharPagerAdapter;
import com.example.lotrcharacters.models.Doc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.viewPager) ViewPager mViewPager;
    private CharPagerAdapter adapterViewPager;
    List<Doc> mDocList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);
        ButterKnife.bind(this);

        mDocList = Parcels.unwrap(getIntent().getParcelableExtra("characters"));
        int startingPosition = getIntent().getIntExtra("positions", 0);

        adapterViewPager = new CharPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mDocList);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);

    }

}
