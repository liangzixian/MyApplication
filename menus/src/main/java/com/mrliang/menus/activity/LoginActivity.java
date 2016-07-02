package com.mrliang.menus.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrliang.menus.R;
import com.mrliang.menus.base.AppBaseActivity;

/**
 * Created by Administrator on 2016/7/2.
 */
public class LoginActivity extends AppBaseActivity implements View.OnClickListener {

    private ImageView img_login_back;
    private TextView tv_login_register;
    private EditText et_login_username;
    private EditText et_login_password;
    private TextView tv_login_forget;
    private Button btn_login_btnlogin;

    private ImageView img_login_qq;
    private ImageView img_login_weibo;


    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.login_activity);
        img_login_back = (ImageView) findViewById(R.id.img_login_back);
        tv_login_register = (TextView) findViewById(R.id.tv_login_register);
        et_login_username = (EditText) findViewById(R.id.et_login_username);
        et_login_password = (EditText) findViewById(R.id.et_login_password);
        tv_login_forget = (TextView) findViewById(R.id.tv_login_forget);
        btn_login_btnlogin = (Button) findViewById(R.id.btn_login_btnlogin);
        img_login_qq = (ImageView) findViewById(R.id.img_login_qq);
        img_login_weibo = (ImageView) findViewById(R.id.img_login_weibo);

        img_login_back.setOnClickListener(this);
        tv_login_register.setOnClickListener(this);
        et_login_username.setOnClickListener(this);
        et_login_password.setOnClickListener(this);
        tv_login_forget.setOnClickListener(this);
        btn_login_btnlogin.setOnClickListener(this);
        img_login_qq.setOnClickListener(this);
        img_login_weibo.setOnClickListener(this);


    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_login_back:
                break;
            case R.id.tv_login_register:
                break;
            case R.id.et_login_username:
                break;
            case R.id.et_login_password:
                break;
            case R.id.tv_login_forget:
                break;
            case R.id.btn_login_btnlogin:
                break;
            case R.id.img_login_qq:
                break;
            case R.id.img_login_weibo:
                break;
        }
    }
}
