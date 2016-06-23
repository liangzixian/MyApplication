package com.mrliang.millionmenus.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.mrliang.millionmenus.CategoryFragment;
import com.mrliang.millionmenus.R;
import com.mrliang.millionmenus.entity.Category;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/30 0030.
 */
public class ClassifyActivity extends FragmentActivity {
    private String contant = null;

    private TextView[] tvList;
    private View[] views;

    private List<Category> list;

    private LayoutInflater inflater;
    private ScrollView scrollView;
    private int currentItem = 0;

    private CategoryAdapter adapter;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classify_activity);
        contant = getIntent().getStringExtra("contant");
        Log.i("ClassifyActivity", contant);
        loadCategory();

        scrollView = (ScrollView) findViewById(R.id.tools_scrollView);
        adapter = new CategoryAdapter(getSupportFragmentManager());
        inflater = LayoutInflater.from(this);
        showToolsView();
        initPager();

    }

    private void showToolsView() {
        LinearLayout toolsLayout = (LinearLayout) findViewById(R.id.tools);
        tvList = new TextView[list.size()];
        views = new View[list.size()];

        for (int i = 0; i < list.size(); i++) {
            View view = inflater.inflate(R.layout.item_list_layout, null);
            view.setId(i);
            view.setOnClickListener(toolsItemListener);
            TextView textView = (TextView) view.findViewById(R.id.text);
            textView.setText(list.get(i).getName());
            toolsLayout.addView(view);
            tvList[i] = textView;
            views[i] = view;
        }
        changeTextColor(0);
    }

    private View.OnClickListener toolsItemListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewPager.setCurrentItem(v.getId());
        }
    };

    /**
     * initPager<br/>
     * 初始化ViewPager控件相关内容
     */
    private void initPager() {
        viewPager = (ViewPager) findViewById(R.id.category_page);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(onPageChangeListener);
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (viewPager.getCurrentItem() != position)
                viewPager.setCurrentItem(position);
            if (currentItem != position) {
                changeTextColor(position);
                changeTextLocation(position);
            }
            currentItem = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void loadCategory() {
        list = JSON.parseArray(contant, Category.class);
    }

    private class CategoryAdapter extends FragmentPagerAdapter {

        public CategoryAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new CategoryFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("index", position);
            bundle.putSerializable("list", (Serializable) list);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }


    /**
     * 改变textView的颜色
     *
     * @param id
     */
    private void changeTextColor(int id) {
        for (int i = 0; i < tvList.length; i++) {
            if (i != id) {
                tvList[i].setBackgroundColor(0x00000000);
                tvList[i].setTextColor(0xFF000000);
            }
        }
        tvList[id].setBackgroundColor(0xFFFFFFFF);
        tvList[id].setTextColor(0xFFFF5D5E);
    }

    /**
     * 改变栏目位置
     *
     * @param clickPosition
     */
    private void changeTextLocation(int clickPosition) {
        int x = (views[clickPosition].getTop());
        scrollView.smoothScrollTo(0, x);
    }
}
