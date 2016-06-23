package com.mrliang.millionmenus.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.mrliang.millionmenus.R;
import com.mrliang.millionmenus.adapter.ImagePagerAdapter;
import com.mrliang.millionmenus.base.AppBaseActivity;
import com.mrliang.millionmenus.entity.DetailsList;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.List;

/**
 * Created by Administrator on 2016/6/14.
 */
public class StepsActivity extends AppBaseActivity {
    private ImagePagerAdapter adapter;
    private DisplayImageOptions options;
    private ViewPager pager;
    private List<DetailsList.DataBean.StepsBean> list = null;

    @Override
    protected void initVariables() {
        list = (List<DetailsList.DataBean.StepsBean>) getIntent().getSerializableExtra("steps");

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.steps_activity);
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .resetViewBeforeLoading(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)
                .displayer(new FadeInBitmapDisplayer(300))
                .build();
        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new ImagePagerAdapter(this, list, options);
        pager.setAdapter(adapter);

    }

    @Override
    protected void loadData() {

    }
}
