package com.mrliang.millionmenus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.mrliang.androidlib.net.RequestCallback;
import com.mrliang.androidlib.net.RequestParameter;
import com.mrliang.millionmenus.R;
import com.mrliang.millionmenus.base.AppBaseActivity;
import com.mrliang.millionmenus.engine.AppConstants;
import com.mrliang.millionmenus.engine.RemoteService;
import com.mrliang.millionmenus.utils.Utils;

import java.util.ArrayList;

public class MainActivity extends AppBaseActivity {


    private RequestCallback callback = null;

    private String contant;

    private Button button;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            contant = msg.obj.toString();
        }
    };

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.classify);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ClassifyActivity.class);
                intent.putExtra("contant", contant);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void loadData() {
        dlg = Utils.createProgressDialog(this, this.getString(R.string.str_loadig));
        dlg.show();
        loadCategory();
    }

    private void loadCategory() {
        callback = new AbstractRequestCallback() {
            @Override
            public void onSuccess(final String content) {
                new Thread() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.obj = content;
                        handler.sendMessage(message);
                    }
                }.start();
                dlg.dismiss();

            }
        };
        ArrayList<RequestParameter> params = new ArrayList<RequestParameter>();
        RequestParameter param = new RequestParameter("key", AppConstants.APP_KEY);
        params.add(param);
        RemoteService.getInstance().invoke(this, "getCategory", params, callback);
    }
}
