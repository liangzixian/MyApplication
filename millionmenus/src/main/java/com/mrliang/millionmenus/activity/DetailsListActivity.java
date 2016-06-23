package com.mrliang.millionmenus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.mrliang.androidlib.net.RequestCallback;
import com.mrliang.androidlib.net.RequestParameter;
import com.mrliang.millionmenus.R;
import com.mrliang.millionmenus.adapter.DetailsListAdapter;
import com.mrliang.millionmenus.base.AppBaseActivity;
import com.mrliang.millionmenus.engine.AppConstants;
import com.mrliang.millionmenus.engine.RemoteService;
import com.mrliang.millionmenus.entity.DetailsList;
import com.mrliang.millionmenus.utils.Utils;
import com.mrliang.millionmenus.view.LoadListView;
import com.mrliang.millionmenus.view.LoadListView.ILoadListener;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/30 0030.
 */
public class DetailsListActivity extends AppBaseActivity implements ILoadListener, AdapterView
        .OnItemClickListener {

    private String id;

    private RequestCallback callback = null;

    private DetailsListAdapter adapter;

    private DetailsList details = null;
    private List<DetailsList.DataBean> list;

    LoadListView listView;
    private DisplayImageOptions options;

    private int pn = 0;

    private final int LOAD_DETAILS = 0;
    private final int LOAD_DATA = 1;

    private int listItemCount = 0;

    private int totalNum = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOAD_DETAILS:
                    pn = pn + 30;
                    list = (List<DetailsList.DataBean>) msg.obj;
                    showListView(list);
                    break;
            }
        }
    };

    @Override
    protected void initVariables() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.details_list_activity);

        options = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .displayer(new RoundedBitmapDisplayer(10))
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        listView = (LoadListView) findViewById(R.id.lv_list_details);
        listView.setInterface(DetailsListActivity.this);
        listView.setOnItemClickListener(this);

    }

    @Override
    protected void loadData() {
        dlg = Utils.createProgressDialog(this, getString(R.string.str_loadig));
        loadDetails();
    }

    private void loadDetails() {
        dlg.show();
        callback = new AbstractRequestCallback() {
            @Override
            public void onSuccess(final String content) {
                Log.d("DetailsListActivity", content);
                details = JSON.parseObject(content, DetailsList.class);
                list = details.getData();
                totalNum = Integer.valueOf(details.getTotalNum());
                Message message = new Message();
                message.what = LOAD_DETAILS;
                message.obj = list;
                handler.sendMessage(message);
                dlg.dismiss();
            }
        };
        ArrayList<RequestParameter> parameters = new ArrayList<RequestParameter>();
        RequestParameter param1 = new RequestParameter("key", AppConstants.APP_KEY);
        parameters.add(param1);
        RequestParameter param2 = new RequestParameter("cid", id);
        parameters.add(param2);
        RequestParameter param3 = new RequestParameter("rn", "30");
        parameters.add(param3);
        RequestParameter param4 = new RequestParameter("pn", pn + "");
        parameters.add(param4);
        RemoteService.getInstance().invoke(this, "getByCid", parameters, callback);
    }

    private void showListView(final List<DetailsList.DataBean> list) {
        if (adapter == null) {
            adapter = new DetailsListAdapter(DetailsListActivity.this, list,
                    options);
            listView.setAdapter(adapter);
        } else {
            adapter.onDataChange(list);
        }
        dlg.dismiss();
    }

    /**
     * 加载更多
     */
    private void getLoadData() {
        callback = new AbstractRequestCallback() {
            @Override
            public void onSuccess(String content) {
                Log.d("getLoadData", content);
                pn = pn + 30;
                details = JSON.parseObject(content, DetailsList.class);
                List<DetailsList.DataBean> dataList = details.getData();
                for (DetailsList.DataBean data : dataList) {
                    list.add(data);
                }
            }
        };
        ArrayList<RequestParameter> parameters = new ArrayList<RequestParameter>();
        RequestParameter param1 = new RequestParameter("key", AppConstants.APP_KEY);
        parameters.add(param1);
        RequestParameter param2 = new RequestParameter("cid", id);
        parameters.add(param2);
        RequestParameter param3 = new RequestParameter("rn", "30");
        parameters.add(param3);
        RequestParameter param4 = new RequestParameter("pn", pn + "");
        parameters.add(param4);
        RemoteService.getInstance().invoke(this, "getByCid", parameters, callback);

    }


    @Override
    public void onLoad() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listItemCount = listView.getCount();
                if (listItemCount >= totalNum) {
                    listView.loadComplete();
                    Toast.makeText(DetailsListActivity.this, "没有更多信息了！", Toast.LENGTH_SHORT).show();
                } else if (listItemCount < totalNum) {
                    getLoadData();
                    showListView(list);
                    listView.loadComplete();
                }
            }
        }, 2000);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DetailsList.DataBean dataBean = list.get(position);
        Intent intent = new Intent(DetailsListActivity.this, DetailsActivity.class);
        intent.putExtra("data", dataBean);
        startActivity(intent);
    }
}
