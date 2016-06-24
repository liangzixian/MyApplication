package com.mrliang.menus.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mrliang.androidlib.net.RequestCallback;
import com.mrliang.androidlib.net.RequestParameter;
import com.mrliang.menus.R;
import com.mrliang.menus.base.AppBaseFragment;
import com.mrliang.menus.engine.AppConstants;
import com.mrliang.menus.engine.RemoteService;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/23.
 */
public class ClassifyFragment extends AppBaseFragment {

    private View mRootView;
    private RequestCallback callback = null;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void loadData() {
        callback = new AbstractRequestCallback() {
            @Override
            public void onSuccess(String content) {
                Log.e("ClassifyFragment", content);
            }
        };
        ArrayList<RequestParameter> params = new ArrayList<RequestParameter>();
        RequestParameter param = new RequestParameter("key", AppConstants.APP_KEY);
        params.add(param);
        RemoteService.getInstance().invoke(this, "getCategory", params, callback);
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
