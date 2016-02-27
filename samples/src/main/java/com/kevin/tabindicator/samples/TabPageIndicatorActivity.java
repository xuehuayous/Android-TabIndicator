package com.kevin.tabindicator.samples;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.kevin.tabindicator.TabPageIndicator;
import com.kevin.tabindicator.TabPageIndicatorEx;

import java.util.ArrayList;
import java.util.List;

public class TabPageIndicatorActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabPageIndicator mTabPageIndicator;
    private List<Fragment> mTabs = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;

    private boolean isGradualChange;

    private String[] mTitles = new String[] { "First Fragment!",
            "Second Fragment!", "Third Fragment!"};

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabpage_indicator);

        initViews();
        initEvents();
    }

    /**
     * 初始化View
     */
    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mTabPageIndicator = (TabPageIndicator) findViewById(R.id.tabpage_act_tpi);
        initTabIndicator();
        initViewPager();
    }

    private void initViewPager() {

        for (String title : mTitles) {
            TabFragment tabFragment = new TabFragment();
            Bundle args = new Bundle();
            args.putString("title", title);
            tabFragment.setArguments(args);
            mTabs.add(tabFragment);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mTabs.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mTabs.get(arg0);
            }
        };
        mViewPager.setAdapter(mAdapter);
    }

    /**
     * 初始化事件
     */
    private void initEvents() {
        mTabPageIndicator.setOnTabSelectedListener(new TabPageIndicatorEx.OnTabSelectedListener() {

            @Override
            public void onTabSelected(int index) {
                mViewPager.setCurrentItem(index, false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("改变切换模式");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().toString().equals("改变切换模式")) {
            mTabPageIndicator.setIsGradualChange(!isGradualChange);
            isGradualChange = !isGradualChange;
        }
        return true;
    }

    private void initTabIndicator() {
        mTabPageIndicator.setViewPager(mViewPager);
        mTabPageIndicator.setIndicateDisplay(2, true);
    }

}
