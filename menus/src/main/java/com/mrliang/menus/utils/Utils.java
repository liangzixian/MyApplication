package com.mrliang.menus.utils;

import android.app.Activity;
import android.app.ProgressDialog;

import com.mrliang.androidlib.activity.BaseActivity;

/**
 * Created by Administrator on 2016/5/30 0030.
 */
public class Utils {
    /**
     * @param activity
     * @param msg
     * @return
     */
    public static ProgressDialog createProgressDialog(final Activity activity, final String msg) {
        final ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setMessage(msg);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}
