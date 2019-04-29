package com.yanb.daqsoft.baseandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
                .url("www.baidu.com")
                .loader(this)
                .onSuccess(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

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
