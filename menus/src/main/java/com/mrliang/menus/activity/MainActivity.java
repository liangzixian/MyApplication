package com.mrliang.menus.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.mrliang.menus.R;
import com.mrliang.menus.fragment.ClassifyFragment;
import com.mrliang.menus.fragment.FindFragment;
import com.mrliang.menus.fragment.MenuFragment;
import com.mrliang.menus.fragment.MyFragment;
import com.mrliang.menus.view.ChangeColorIconWithText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ViewPager mViewpager;
    private List<Fragment> mTabs = new ArrayList<Fragment>();

    private FragmentPagerAdapter mAdapter;

    private List<ChangeColorIconWithText> mTabIndicators = new ArrayList<ChangeColorIconWithText>();

    MenuFragment menuFragment = null;
    FindFragment findFragment = null;
    ClassifyFragment classifyFragment = null;
    MyFragment myFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        initData();

        mViewpager.setAdapter(mAdapter);

        initEvent();
    }

    private void initData() {
        menuFragment = new MenuFragment();
        mTabs.add(menuFragment);
        findFragment = new FindFragment();
        mTabs.add(findFragment);
        classifyFragment = new ClassifyFragment();
        mTabs.add(classifyFragment);
        myFragment = new MyFragment();
        mTabs.add(myFragment);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mTabs.get(position);
            }

            @Override
            public int getCount() {
                return mTabs.size();
            }
        };
    }

    private void initEvent() {
        mViewpager.setOnPageChangeListener(this);
    }

    private void initView() {
        mViewpager = (ViewPager) findViewById(R.id.id_viewpager);
        ChangeColorIconWithText menu = (ChangeColorIconWithText) findViewById(R.id.id_tab_menu);
        mTabIndicators.add(menu);
        ChangeColorIconWithText find = (ChangeColorIconWithText) findViewById(R.id.id_tab_find);
        mTabIndicators.add(find);
        ChangeColorIconWithText classify = (ChangeColorIconWithText) findViewById(R.id.id_tab_classify);
        mTabIndicators.add(classify);
        ChangeColorIconWithText my = (ChangeColorIconWithText) findViewById(R.id.id_tab_my);
        mTabIndicators.add(my);

        menu.setOnClickListener(this);
        find.setOnClickListener(this);
        classify.setOnClickListener(this);
        my.setOnClickListener(this);

        menu.setIconAlpha(1.0f);
    }


    @Override
    public void onClick(View v) {
        clickTab(v);
    }

    private void clickTab(View v) {
        resetOtherTabs();

        switch (v.getId()) {
            case R.id.id_tab_menu:
                mTabIndicators.get(0).setIconAlpha(1.0f);
                mViewpager.setCurrentItem(0, false);
                break;
            case R.id.id_tab_find:
                mTabIndicators.get(1).setIconAlpha(1.0f);
                mViewpager.setCurrentItem(1, false);
                break;
            case R.id.id_tab_classify:
                mTabIndicators.get(2).setIconAlpha(1.0f);
                mViewpager.setCurrentItem(2, false);
                break;
            case R.id.id_tab_my:
                mTabIndicators.get(3).setIconAlpha(1.0f);
                mViewpager.setCurrentItem(3, false);
                break;
        }
    }

    /**
     * 重置其他的TabIndicator的颜色
     */
    private void resetOtherTabs() {
        for (int i = 0; i < mTabIndicators.size(); i++) {
            mTabIndicators.get(i).setIconAlpha(0);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset > 0) {
            ChangeColorIconWithText left = mTabIndicators.get(position);
            ChangeColorIconWithText right = mTabIndicators.get(position + 1);
            left.setIconAlpha(1 - positionOffset);
            right.setIconAlpha(positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
