package com.yanb.daqsoft.baselib.app;

import android.os.Handler;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.yanb.daqsoft.baselib.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

import okhttp3.Interceptor;

/**
 * @author: yanbo
 * @date: 2019/4/14.
 * @Email: 760375443@qq.com
 * @Description: 全局配置文件存储或获取
 */

public class Configurator {
    // 首先一个map存对象
    private static final HashMap<Object,Object> APPS_CONFIGS = new HashMap<>();
    private static final Handler HANDLER = new Handler();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    /**
     * 构造方法
     */
    private Configurator(){
        // 刚开始，配置未完成（配置开始）
        APPS_CONFIGS.put(ConfigKeys.CONFIG_READY,false);
        APPS_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
    }

    /**
     * 静态累不累单列模式的单列
     * 线程安全的懒汉模式
     * 静态累不累Holder然后get他
     */
    static Configurator getInstance(){
        return Holder.INSTANCE;
    }
    /**
     * 静态累不累
     */
    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    final HashMap<Object, Object> getAppsConfigs() {
        return APPS_CONFIGS;
    }



    /**
     * 开始配置
     *
     */
    public final void build(){
        Logger.addLogAdapter(new AndroidLogAdapter());
        APPS_CONFIGS.put(ConfigKeys.CONFIG_READY,true);//配置文件状态好了
        Utils.init(Apps.getApplicationContext());
    }

    /**
     * 配置APIHOST网络请求
     */
    public final Configurator withApiHost(String host){
        APPS_CONFIGS.put(ConfigKeys.API_HOST,host);
        return this;
    }
    public final Configurator withLoaderDelayed(long delayed) {
        APPS_CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        APPS_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        APPS_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }
    /**
     * 检查配置
     * 首先检查配置，没配置完成就抛出异常
     */
    private void checkConfiguration(){
        final boolean isReady = (boolean) APPS_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady){
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    /**
     * 获取
     * @param key
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    final <T> T getConfigByKeys(Object key){
        checkConfiguration();
        final Object value = APPS_CONFIGS.get(key);
        if (value == null){
            throw new NullPointerException(key.toString()+ " IS NULL");
        }
        return (T) APPS_CONFIGS.get(key);
    }

}