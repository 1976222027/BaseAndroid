package com.yanb.daqsoft.baseandroid.common;

import com.daqsoft.rxyhttp.EasyHttp;
import com.yanb.daqsoft.baselib.app.BaseApplication;

/**
 * IApplication
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-24.16:53
 * @since JDK 1.8
 */

public class IApplication extends BaseApplication{
    @Override
    public void onCreate() {
        super.onCreate();
        EasyHttp.init(this);
        EasyHttp.getInstance().setBaseUrl(UrlConstants.BASE_URL)
                .debug("yanb",true)
                .setRetryCount(3);


    }
}
