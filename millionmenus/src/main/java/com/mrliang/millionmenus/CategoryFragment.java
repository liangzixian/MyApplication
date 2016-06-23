package com.mrliang.millionmenus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.mrliang.millionmenus.activity.DetailsListActivity;
import com.mrliang.millionmenus.adapter.CategoryGridViewAdapter;
import com.mrliang.millionmenus.entity.Category;

import java.util.List;

/**
 * Created by Administrator on 2016/5/30 0030.
 */
public class CategoryFragment extends Fragment {

    private GridView gridView;

    private List<Category> list;

    private List<Category.ListBean> typeName;

    private TextView typeView;

    private CategoryGridViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_type, null);
        gridView = (GridView) view.findViewById(R.id.list_view);
        typeView = (TextView) view.findViewById(R.id.top_type);
        int index = getArguments().getInt("index");
        Log.e("CategoryFragment", index + "");
        list = (List<Category>) getArguments().getSerializable("list");


        typeName = list.get(index).getList();
        typeView.setText(list.get(index).getName());

        adapter = new CategoryGridViewAdapter(getActivity(), typeName);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), typeName.get(position).getParentId() + "/" + typeName.get(position)
                        .getName() + "/" + typeName.get(position).getId(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), DetailsListActivity.class);
                intent.putExtra("id",typeName.get(position).getId());
                startActivity(intent);

            }
        });
        return view;
    }
}
