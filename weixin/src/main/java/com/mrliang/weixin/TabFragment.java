package com.mrliang.weixin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/6/21.
 */
public class TabFragment extends Fragment {

    private String mTitle = "default";
    public static final String TITLE = "title";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
