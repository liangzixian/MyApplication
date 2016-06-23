package com.mrliang.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpUtils.get().url("http://op.juhe.cn/onebox/weather/query").addParams("key", "d44aad7a3bd4a0b81c9a8a2a11d163ee").addParams("cityname", "深圳").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                
            }

            @Override
            public void onResponse(Call call, String s) {
                Log.i("MainActivity", s);
                call.cancel();
            }
        });
        

    }
}
