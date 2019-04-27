package com.yanb.daqsoft.baselib.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * @author: yanbo
 * @date: 2019/4/14.
 * @Email: 760375443@qq.com
 * @Description:
 */

public final class Apps {
    /**
     * 初始化配置文件
     */
    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getAppsConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }


    /**
     * 通过KEY获取配置文件
     */
    public static <T> T getConfigByKeys(Object key) {
        return getConfigurator().getConfigByKeys(key);
    }

    /**
     * 获取配置对象
     */
    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    /**
     * 获取当前上下文
     * @return
     */
    public static Context getApplicationContext(){
        return getConfigByKeys(ConfigKeys.APPLICATION_CONTEXT);
    }


}
