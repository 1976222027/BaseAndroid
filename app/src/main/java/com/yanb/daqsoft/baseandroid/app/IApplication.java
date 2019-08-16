package com.yanb.daqsoft.baseandroid.app;


import com.alibaba.android.arouter.launcher.ARouter;
import com.squareup.leakcanary.LeakCanary;
import com.yanb.daqsoft.baseandroid.BuildConfig;
import com.yanb.daqsoft.baseandroid.R;
import com.yanb.daqsoft.baseandroid.login.LoginNewActivity;
import com.yanb.daqsoft.baselib.app.BaseApplication;
import com.yanb.daqsoft.baselib.mvvmbase.crash.CaocConfig;
import com.yanb.daqsoft.baselib.utils.KLog;

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
        super.onCreate();
        // 是否开启日志打印
        KLog.init(true);
        // 初始化异常全局崩溃
        initCrash();
        //内存泄漏检测
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
        }
        initRouter();
    }
    private void initCrash() {
        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //背景模式,开启沉浸式
                .enabled(true) //是否启动全局异常捕获
                .showErrorDetails(true) //是否显示错误详细信息
                .showRestartButton(true) //是否显示重启按钮
                .trackActivities(true) //是否跟踪Activity
                .minTimeBetweenCrashesMs(2000) //崩溃的间隔时间(毫秒)
                .errorDrawable(R.mipmap.ic_launcher) //错误图标
                .restartActivity(LoginNewActivity.class) //重新启动后的activity
//                .errorActivity(YourCustomErrorActivity.class) //崩溃后的错误activity
//                .eventListener(new YourCustomEventListener()) //崩溃后的错误监听
                .apply();
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
