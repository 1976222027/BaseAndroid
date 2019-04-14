package com.yanb.daqsoft.baselib.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * @author: yanbo
 * @date: 2019/4/14.
 * @Email: 760375443@qq.com
 * @Description:
 */

public class App {
    /**
     * 初始化配置文件
     */
    public static Configurator init(Context context){
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    /**
     *
     * @return 获取配置文件的集合map
     */
    public static WeakHashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getBaseConfigs();
    }
}
