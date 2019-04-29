package com.yanb.daqsoft.baselib.app;

import android.app.Application;

import com.yanb.daqsoft.baselib.utils.Utils;

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
        Utils.init(this);
        Apps.init(this)
                .withApiHost("http://ptisp.test.daqsoft.com/govapi/")
                // 加入别的配置按此类推
                .build();
    }
}
