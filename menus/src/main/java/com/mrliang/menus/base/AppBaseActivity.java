package com.mrliang.menus.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;

import com.mrliang.androidlib.activity.BaseActivity;
import com.mrliang.androidlib.net.RequestCallback;

/**
 * Created by Administrator on 2016/7/2.
 */
public abstract class AppBaseActivity extends BaseActivity{

    protected ProgressDialog dlg;
//    public ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    protected void onDestroy() {
//        imageLoader.clearMemoryCache();
        super.onDestroy();
    }

    public abstract class AbstractRequestCallback implements RequestCallback {
        public abstract void onSuccess(String content);

        public void onFail(String errorMessage) {
            dlg.dismiss();
            new AlertDialog.Builder(AppBaseActivity.this).setTitle("出错啦").setMessage(errorMessage)
                    .setPositiveButton("确定", null).show();
        }
    }
}
