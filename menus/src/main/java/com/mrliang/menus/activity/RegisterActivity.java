package com.mrliang.menus.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrliang.menus.R;
import com.mrliang.menus.base.AppBaseActivity;

/**
 * Created by Administrator on 2016/7/4.
 */
public class RegisterActivity extends AppBaseActivity {

    private ImageView img_register_back;
    private TextView tv_register_login;
    private EditText et_register_phone;
    private EditText et_register_code;
    private Button btn_register_get_code;
    private Button btn_register_reg;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.register_activity);

        img_register_back = (ImageView) findViewById(R.id.img_register_back);
        tv_register_login = (TextView) findViewById(R.id.tv_register_login);
        et_register_phone = (EditText) findViewById(R.id.et_register_phone);
        et_register_code = (EditText) findViewById(R.id.et_register_code);
        btn_register_get_code = (Button) findViewById(R.id.btn_register_get_code);
        btn_register_reg = (Button) findViewById(R.id.btn_register_reg);
    }

    @Override
    protected void loadData() {

    }


}
