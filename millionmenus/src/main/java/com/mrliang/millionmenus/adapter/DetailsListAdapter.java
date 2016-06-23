package com.mrliang.millionmenus.adapter;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrliang.millionmenus.R;
import com.mrliang.millionmenus.base.AppBaseActivity;
import com.mrliang.millionmenus.entity.DetailsList;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/2 0002.
 */
public class DetailsListAdapter extends BaseAdapter {

    private List<DetailsList.DataBean> list;
    private AppBaseActivity context;

    private DisplayImageOptions options;


    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public DetailsListAdapter(AppBaseActivity context, List<DetailsList.DataBean> list,
                              DisplayImageOptions options) {
        this.context = context;
        this.list = list;
        this.options = options;
    }

    public void onDataChange(List<DetailsList.DataBean> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = context.getLayoutInflater().inflate(R.layout.item_details_list, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.img_list_food);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_tags = (TextView) convertView.findViewById(R.id.tv_tags);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        DetailsList.DataBean bean = list.get(position);
        context.imageLoader.displayImage(bean.getAlbums().get(0), holder.imageView, options,
                animateFirstListener);

        holder.tv_title.setText(bean.getTitle());
        holder.tv_tags.setText(bean.getTags());

        return convertView;
    }

    class Holder {
        ImageView imageView;
        TextView tv_title;
        TextView tv_tags;
    }

    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections.synchronizedList(new
                LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }
}
