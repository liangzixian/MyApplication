package com.mrliang.millionmenus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mrliang.millionmenus.R;
import com.mrliang.millionmenus.base.AppBaseActivity;
import com.mrliang.millionmenus.entity.DetailsList;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/6/6 0006.
 */
public class DetailsActivity extends AppBaseActivity {
    private int screenWidth;
    private int screenHeigth;


    private ImageView img_details_albums;
    private TextView tv_details_title;
    private TextView tv_details_tags;
    private TextView tv_details_imtro;
    private TextView tv_details_ingredients;
    private TextView tv_details_burden;

    private DetailsList.DataBean dataBean;

    private DisplayImageOptions options;


    @Override
    protected void initVariables() {
        dataBean = (DetailsList.DataBean) getIntent().getSerializableExtra("data");

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.details_activity);
        initView();
        setLayoutParam();

        options = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .displayer(new RoundedBitmapDisplayer(10))
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();


    }

    @Override
    protected void loadData() {
        imageLoader.displayImage(dataBean.getAlbums().get(0), img_details_albums, options);
        tv_details_title.setText(dataBean.getTitle());
        tv_details_tags.setText(dataBean.getTags());
        tv_details_imtro.setText(dataBean.getImtro());
        tv_details_ingredients.setText(dataBean.getIngredients());
        tv_details_burden.setText(dataBean.getBurden());

        img_details_albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, StepsActivity.class);
                List<DetailsList.DataBean.StepsBean> stepsBean = dataBean.getSteps();
                intent.putExtra("steps", (Serializable) stepsBean);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        img_details_albums = (ImageView) findViewById(R.id.img_details_albums);
        tv_details_title = (TextView) findViewById(R.id.tv_details_title);
        tv_details_tags = (TextView) findViewById(R.id.tv_details_tags);
        tv_details_imtro = (TextView) findViewById(R.id.tv_details_imtro);
        tv_details_ingredients = (TextView) findViewById(R.id.tv_details_ingredients);
        tv_details_burden = (TextView) findViewById(R.id.tv_details_burden);

    }


    private void setLayoutParam() {
        // 获取屏幕信息
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeigth = displayMetrics.heightPixels;
        Log.i("DetailsActivity", screenWidth + "\n" + screenHeigth);

//        LinearLayout.LayoutParams imgParas = new LinearLayout.LayoutParams(screenWidth,
//        (int) (screenHeigth * 0.25f));
//        imgParas.setMargins((int) (screenWidth * 0.01f), 0, (int) (screenWidth * 0.01f), 0);
//        img_details_albums.setLayoutParams(imgParas);
    }

}
