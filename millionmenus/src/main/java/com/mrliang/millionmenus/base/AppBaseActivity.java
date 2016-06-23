package com.mrliang.millionmenus.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.mrliang.androidlib.activity.BaseActivity;
import com.mrliang.androidlib.net.RequestCallback;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public abstract class AppBaseActivity extends BaseActivity {
    protected ProgressDialog dlg;
    public ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    protected void onDestroy() {
        imageLoader.clearMemoryCache();
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
