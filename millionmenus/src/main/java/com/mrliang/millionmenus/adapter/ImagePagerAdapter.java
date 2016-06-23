package com.mrliang.millionmenus.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mrliang.millionmenus.R;
import com.mrliang.millionmenus.base.AppBaseActivity;
import com.mrliang.millionmenus.entity.DetailsList;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.List;

/**
 * Created by Administrator on 2016/6/14.
 */
public class ImagePagerAdapter extends PagerAdapter {

    private LayoutInflater inflater;
    private List<DetailsList.DataBean.StepsBean> list;
    private AppBaseActivity context;
    private DisplayImageOptions options;

    public ImagePagerAdapter(AppBaseActivity context, List<DetailsList.DataBean.StepsBean> list,
                             DisplayImageOptions
                                     options) {
        this.list = list;
        this.context = context;
        this.options = options;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.item_pager_image, container, false);
        assert view != null;
        ImageView imageView = (ImageView) view.findViewById(R.id.image_pager);
        TextView textView = (TextView) view.findViewById(R.id.tv_step);
        textView.setText(list.get(position).getStep());
        final ProgressBar spinner = (ProgressBar) view.findViewById(R.id.loading);
        context.imageLoader.displayImage(list.get(position).getImg(), imageView, options, new
                SimpleImageLoadingListener() {

                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        spinner.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        String message = null;
                        switch (failReason.getType()) {
                            case IO_ERROR:
                                message = "Input/Output error";
                                break;
                            case DECODING_ERROR:
                                message = "Image can't be decoded";
                                break;
                            case NETWORK_DENIED:
                                message = "Downloads are denied";
                                break;
                            case OUT_OF_MEMORY:
                                message = "Out Of Memory error";
                                break;
                            case UNKNOWN:
                                message = "Unknown error";
                                break;
                        }
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        spinner.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        spinner.setVisibility(View.GONE);
                    }
                });
        container.addView(view, 0);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
