package com.mrliang.androidlib.net;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public interface RequestCallback {
    public void onSuccess(String content);

    public void onFail(String errorMessage);
}
