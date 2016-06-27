package com.mrliang.menus.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.mrliang.androidlib.net.RequestCallback;
import com.mrliang.androidlib.net.RequestParameter;
import com.mrliang.menus.R;
import com.mrliang.menus.base.AppBaseFragment;
import com.mrliang.menus.engine.AppConstants;
import com.mrliang.menus.engine.RemoteService;
import com.mrliang.menus.entity.Category;
import com.mrliang.menus.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/23.
 */
public class ClassifyFragment extends AppBaseFragment implements ViewPager.OnPageChangeListener {

    private View mRootView;

    private ScrollView scrollView;
    private ViewPager viewPager;

    private LinearLayout toolsLayout;

    private LayoutInflater inflater;

    private int currentItem = 0;

    private TextView[] tvList;
    private View[] views;

    private RequestCallback callback = null;
    private List<Category> list;
    private String content;

    private ClassifyAdapter adapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            content = msg.obj.toString();
            list = JSON.parseArray(content, Category.class);
            showToolsView();
            adapter = new ClassifyAdapter(getFragmentManager());
            viewPager.setAdapter(adapter);
        }
    };

    @Override
    protected void initVariables() {

    }


    @Override
    protected void loadData() {
        dlg = Utils.createProgressDialog(getActivity(), this.getString(R.string.str_loadig));
        dlg.show();
        callback = new AbstractRequestCallback() {
            @Override
            public void onSuccess(final String content) {
                Log.e("ClassifyFragment", content);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.obj = content;
                        handler.sendMessage(message);
                    }
                }).start();
                dlg.dismiss();
            }
        };
        ArrayList<RequestParameter> params = new ArrayList<RequestParameter>();
        RequestParameter param = new RequestParameter("key", AppConstants.APP_KEY);
        params.add(param);
        RemoteService.getInstance().invoke(this, "getCategory", params, callback);
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        if (mRootView == null) {
            Log.e("ClassifyFragment", "mRootView is null");
            mRootView = inflater.inflate(R.layout.classify_fragment, container, false);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        toolsLayout = (LinearLayout) mRootView.findViewById(R.id.id_classify_scrollview_tools);
        scrollView = (ScrollView) mRootView.findViewById(R.id.id_classify_scrollview);
        viewPager = (ViewPager) mRootView.findViewById(R.id.id_classify_viewpager);
        viewPager.setOnPageChangeListener(this);
        this.inflater = inflater;

        return mRootView;
    }



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

    private class ClassifyAdapter extends FragmentPagerAdapter {

        public ClassifyAdapter(FragmentManager fm) {
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

    private void showToolsView() {
        tvList = new TextView[list.size()];
        views = new View[list.size()];

        for (int i = 0; i < list.size(); i++) {
            View view = inflater.inflate(R.layout.item_classify_list_layout, null);
            view.setId(i);
            view.setOnClickListener(toolsItemListener);
            TextView textView = (TextView) view.findViewById(R.id.id_classify_tv_list_layout);
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
