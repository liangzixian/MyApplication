package com.mrliang.menus.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;

import com.mrliang.androidlib.activity.BaseFragment;
import com.mrliang.androidlib.net.RequestCallback;

/**
 * Created by Administrator on 2016/6/24.
 */
public abstract class AppBaseFragment extends BaseFragment {
    protected ProgressDialog dlg;

    public abstract class AbstractRequestCallback implements RequestCallback {
        public abstract void onSuccess(String content);

        public void onFail(String errorMessage) {
            dlg.dismiss();
            new AlertDialog.Builder(getActivity()).setTitle("出错啦").setMessage(errorMessage)
                    .setPositiveButton("确定", null).show();
        }
    }

}
