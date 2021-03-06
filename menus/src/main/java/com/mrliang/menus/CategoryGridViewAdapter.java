package com.mrliang.menus;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mrliang.menus.entity.Category;

import java.util.List;

/**
 * Created by Administrator on 2016/6/27.
 */
public class CategoryGridViewAdapter extends BaseAdapter {
    private List<Category.ListBean> list;
    private Context context;

    private Holder view;


    public CategoryGridViewAdapter(Context context, List<Category.ListBean> list) {
        this.list = list;
        this.context = context;
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
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_classify_gridview, null);
            view = new Holder(convertView);
            convertView.setTag(view);
        } else {
            view = (Holder) convertView.getTag();
        }

        view.name.setText(list.get(position).getName());

        return convertView;
    }

    private class Holder {
        private TextView name;

        public Holder(View view) {
            name = (TextView) view.findViewById(R.id.id_classify_tv_type);
        }
    }
}
