package com.daqsoft.xhttp;

import com.daqsoft.xhttp.interceptor.HttpCacheInterceptor;
import com.daqsoft.xhttp.interceptor.HttpHeaderInterceptor;
import com.daqsoft.xhttp.interceptor.LoggingInterceptor;
import com.yanb.daqsoft.baselib.utils.Utils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-27.15:07
 * @since JDK 1.8
 */

public class Xhttp {
    /**
     * 请求超时时间
     */
    private static final int DEFAULT_TIMEOUT = 20000;
    private static Retrofit mRetrofit;
    /**
     * 本类单列
     */
    private static Xhttp instance;
    public static Xhttp getInstance(){
        if (instance==null){
            synchronized (Xhttp.class){
                if (instance==null){
                    instance = new Xhttp();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化必要参数
     */
    public void init(){
        File cacheFile = new File(Utils.getApp().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        // 初始化okhttpclient
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new HttpHeaderInterceptor())
                .addInterceptor(new HttpCacheInterceptor())
                .cache(cache)
                .build();
        mRetrofit = new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("")
                .build();
    }

}
