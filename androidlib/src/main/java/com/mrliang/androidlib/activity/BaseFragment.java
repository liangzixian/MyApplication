package com.mrliang.androidlib.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrliang.androidlib.net.RequestManager;

/**
 * Created by Administrator on 2016/6/24.
 */
public abstract class BaseFragment extends Fragment {

    /**
     * 请求列表管理器
     */
    protected RequestManager requestManager = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        requestManager = new RequestManager();
        initVariables();
        loadData();
        View view = initViews(inflater, container, savedInstanceState);
        return view;
    }


    protected abstract void initVariables();

    protected abstract View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    protected abstract void loadData();


    @Override
    public void onPause() {
        // 在Fragment停止的时候同时设置停止请求，停止线程请求回调
        if (requestManager != null) {
            requestManager.cancelRequest();
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        // 在Fragment销毁的时候同时设置停止请求，停止线程请求回调
        if (requestManager != null) {
            requestManager.cancelRequest();
        }
        super.onDestroy();
    }

    public RequestManager getRequestManager() {
        return requestManager;
    }
}
