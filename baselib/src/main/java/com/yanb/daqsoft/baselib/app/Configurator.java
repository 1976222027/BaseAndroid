package com.yanb.daqsoft.baselib.app;

import java.util.WeakHashMap;

/**
 * @author: yanbo
 * @date: 2019/4/14.
 * @Email: 760375443@qq.com
 * @Description: 全局配置文件存储或获取
 */

public class Configurator {
    // WeakHashMap不使用的时候就可以进行回收，非常及时，可以避免内存爆满
    private static final WeakHashMap<String,Object> BASE_CONFIGS = new WeakHashMap<>();

    final WeakHashMap<String, Object> getBaseConfigs() {
        return BASE_CONFIGS;
    }

    /**
     * 构造方法
     */
    private Configurator(){
        // 刚开始，配置未完成（配置开始）
        BASE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(),false);
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

    /**
     * 下面是可能用到的方法
     *
     */
    public final void configure(){
        BASE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(),true);//配置文件状态好了
    }

    /**
     * 配置APIHOST网络请求
     */
    public final Configurator withApiHost(String host){
        BASE_CONFIGS.put(ConfigKeys.API_HOST.name(),host);
        return this;
    }
    /**
     * 检查配置
     * 首先检查配置，没配置完成就抛出异常
     */
    private void checkConfiguration(){
        final boolean isReady = (boolean) BASE_CONFIGS.get(ConfigKeys.CONFIG_READY.name());
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
    final <T> T getConfiguration(Object key){
        checkConfiguration();
        final Object value = BASE_CONFIGS.get(key);
        if (value == null){
            throw new NullPointerException(key.toString()+ " IS NULL");
        }
        return (T) BASE_CONFIGS.get(key);
    }

}
