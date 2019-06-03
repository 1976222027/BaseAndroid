package com.yanb.daqsoft.baseandroid.common;


import com.alibaba.android.arouter.launcher.ARouter;
import com.daqsoft.xhttp.Xhttp;
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
    private boolean isDebug = true;
    @Override
    public void onCreate() {
        Xhttp.getInstance().init(UrlConstants.BASE_URL);
        super.onCreate();
        initRouter();
    }
    /**
     * 初始化路由跳转
     */
    private void initRouter() {
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (isDebug){
            // 打印日志
            ARouter.openLog();
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(this);
    }
}
