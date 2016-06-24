package com.mrliang.menus.engine;

import com.mrliang.androidlib.activity.BaseActivity;
import com.mrliang.androidlib.activity.BaseFragment;
import com.mrliang.androidlib.net.DefaultThreadPool;
import com.mrliang.androidlib.net.HttpRequest;
import com.mrliang.androidlib.net.RequestCallback;
import com.mrliang.androidlib.net.RequestParameter;
import com.mrliang.androidlib.net.URLData;
import com.mrliang.androidlib.net.UrlConfigManager;

import java.util.List;

/**
 * Created by Administrator on 2016/5/30 0030.
 */
public class RemoteService {
    private static RemoteService service = null;

    private RemoteService() {

    }

    public static synchronized RemoteService getInstance() {
        if (RemoteService.service == null) {
            RemoteService.service = new RemoteService();
        }
        return service;
    }

    public void invoke(final BaseActivity activity, final String apiKey, final List<RequestParameter> params,
                       final RequestCallback callback) {
        final URLData urlData = UrlConfigManager.findURL(activity, apiKey);
        if (urlData.getUrl() != null) {
            HttpRequest request = activity.getRequestManager().createRequest(urlData, params, callback);
            DefaultThreadPool.getInstance().execute(request);
        } else {
            return;
        }
    }

    public void invoke(final BaseFragment fragment, final String apiKey, final List<RequestParameter> params,
                       final RequestCallback callback) {
        final URLData urlData = UrlConfigManager.findURL(fragment.getActivity(), apiKey);
        if (urlData.getUrl() != null) {
            HttpRequest request = fragment.getRequestManager().createRequest(urlData, params, callback);
            DefaultThreadPool.getInstance().execute(request);
        } else {
            return;
        }
    }
}
