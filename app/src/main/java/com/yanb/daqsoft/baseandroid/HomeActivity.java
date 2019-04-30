package com.yanb.daqsoft.baseandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.yanb.daqsoft.baselib.net.RequestClient;
import com.yanb.daqsoft.baselib.net.callback.IError;
import com.yanb.daqsoft.baselib.net.callback.IFailure;
import com.yanb.daqsoft.baselib.net.callback.ISuccess;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text();
    }

    private void text() {
        RequestClient.builder()
                .url("http://ptisp.daqsoft.com/govapi/api/gov/app/userInfo/getUserInfo?siteCode=ycyjywgw&lang=cn&token=d8962d1b-34e9-4e95-857c-365f2a75d9b5")
                .loader(this)
                .onSuccess(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Logger.e("请求地址"+response);
                    }
                }).onFailure(new IFailure() {
            @Override
            public void onFailure() {

            }
        }).onRrror(new IError() {
            @Override
            public void onError(int code, String msg) {

            }
        }).build().get();
    }
}
