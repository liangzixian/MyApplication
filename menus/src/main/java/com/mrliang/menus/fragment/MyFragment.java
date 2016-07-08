package com.mrliang.menus.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrliang.menus.R;
import com.mrliang.menus.activity.LoginActivity;
import com.mrliang.menus.activity.RegisterActivity;

/**
 * Created by Administrator on 2016/6/23.
 */
public class MyFragment extends Fragment implements View.OnClickListener {
    private View mRootView;

    private Button btn_my_login;
    private Button btn_my_register;

    private ImageView img_my_icon;

    private TextView tv_my_undefind;
    private ImageView img_my_setting;
    private Button btn_my_exit;

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            Log.e("MyFragment", "mRootView is null");
            mRootView = inflater.inflate(R.layout.my_fragment, container, false);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        initView(mRootView);
        initEvent();
        return mRootView;
    }


    /**
     * 初始化UI
     *
     * @param view
     */
    private void initView(View view) {
        btn_my_login = (Button) view.findViewById(R.id.btn_my_login);
        btn_my_register = (Button) view.findViewById(R.id.btn_my_register);
        img_my_icon = (ImageView) view.findViewById(R.id.img_my_icon);
        tv_my_undefind = (TextView) view.findViewById(R.id.tv_my_undefind);
        img_my_setting = (ImageView) view.findViewById(R.id.img_my_setting);
        btn_my_exit = (Button) view.findViewById(R.id.btn_my_exit);
    }

    private void initEvent() {
        btn_my_login.setOnClickListener(this);
        btn_my_register.setOnClickListener(this);
        img_my_icon.setOnClickListener(this);
        tv_my_undefind.setOnClickListener(this);
        img_my_setting.setOnClickListener(this);
        btn_my_exit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_my_login:
                Intent intentLogin = new Intent(getActivity(), LoginActivity.class);
                startActivity(intentLogin);
                break;
            case R.id.btn_my_register:
                Intent intentRegister = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
            case R.id.img_my_icon:
                break;
            case R.id.tv_my_undefind:
                break;
            case R.id.img_my_setting:
                break;
            case R.id.btn_my_exit:
                break;

        }
    }
}
