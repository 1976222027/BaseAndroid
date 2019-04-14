package com.yanb.daqsoft.baselib.app;

import android.app.Application;

/**
 * @author: yanbo
 * @date: 2019/4/14.
 * @Email: 760375443@qq.com
 * @Description:
 */

public class BaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        App.init(this)
                .withApiHost("http://www.baidu.com")
                // 加入别的配置按此类推
                .configure();
    }
}
