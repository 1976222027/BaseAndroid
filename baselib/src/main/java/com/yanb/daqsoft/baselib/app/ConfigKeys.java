package com.yanb.daqsoft.baselib.app;

/**
 * @author: yanbo
 * @date: 2019/4/14.
 * @Email: 760375443@qq.com
 * @Description: 枚举类，整个应用是唯一单列，只能被初始化一次
 * 多线程操作，线程安全懒汉模式
 * API_HOST: 网络请求域名
 * APPLICATION_CONTEXT：全局上下文
 * CONFIG_READY：控制初始化完成与否
 */

public enum ConfigKeys {
    API_HOST,
    APPLICATION_CONTEXT,
    CONFIG_READY,
    LOADER_DELAYED,
    INTERCEPTOR,
    ICON
}
