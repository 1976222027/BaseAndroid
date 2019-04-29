package com.yanb.daqsoft.baseandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.yanb.daqsoft.baselib.net.RequestClient;
import com.yanb.daqsoft.baselib.net.callback.IError;
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
                .url("api/gov/app/siteChannel/list?channelCode=rmzt&page=1&limitPage=10&name=&siteCode=ycyjywgw&lang=cn&token=")
                .onSuccess(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.e("哈哈",response);
                    }
                })
                .onRrror(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Log.e("哈哈",msg);
                    }
                })
                .build()
        .get();
    }
}
