package com.mrliang.menus.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mrliang.menus.R;

/**
 * Created by Administrator on 2016/6/23.
 */
public class ClassifyFragment extends Fragment {

    private View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            Log.e("ClassifyFragment", "mRootView is null");
            mRootView = inflater.inflate(R.layout.classify_fragment, container, false);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        TextView textView = (TextView) mRootView.findViewById(R.id.id_tv_classify);
        textView.setText("ClassifyFragment");
        return mRootView;
    }
}
