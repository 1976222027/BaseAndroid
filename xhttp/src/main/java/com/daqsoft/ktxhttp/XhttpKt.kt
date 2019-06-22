package com.daqsoft.ktxhttp

import android.os.Build
import com.yanb.daqsoft.baselib.utils.NetworkUtils
import com.yanb.daqsoft.baselib.utils.Preference
import com.yanb.daqsoft.baselib.utils.Utils
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * kt写的网络请求
 * @author 严博
 * @date 2019-6-13.15:52
 * @version 1.0.0
 * @since JDK 1.8
 * object 的用法
 *
 */
object XhttpKt{
    /**
     * 演示单列类名object修饰就变成单列了
     * 对象直接点里面的方法
     * object编译成一个类拥有一个静态成员来持有对自己的引用，并且这个静态成员的名称为INSTANCE，当然这个INSTANCE是单例的
     * object修饰的类尽管和普通类的声明一样，可以包含属性、方法、初始化代码块以及可以继承其他类或者实现某个接口，
     * 但是它不能包含构造器（包括主构造器以及次级构造器）
     * object也可定义在一个类内部
     *  class ObjectOuter {
     *      object Inner{
     *          fun method(){
     *              println("I'm in inner class")
     *              }
     *          }
     *       }
     *  fun main(args: Array<String>) { ObjectOuter.Inner.method() }
     */
    fun method(){

    }

    private var token:String by Preference("token","")
    /**
     * 下面介绍单列模式的运用
     */
    val retrofitInstance :Retrofit by lazy(LazyThreadSafetyMode.SYNCHRONIZED){
        getRetrofit()
    }

    private fun getRetrofit(): Retrofit{
        // 获取retrofit实例
        return Retrofit.Builder()
                .baseUrl("http://baobab.kaiyanapp.com/api/")
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    /**
     * OkhttpClient
     */
    private fun getOkHttpClient():OkHttpClient{
        // 添加一个日志拦截器
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        //设置请求缓存大小
        val cacheFile = File(Utils.getApp().cacheDir,"cache");
        val cache = Cache(cacheFile, 1024 * 1024 * 50)//50M
        return OkHttpClient.Builder()
                .addInterceptor(addQueryParameInterceptor())//参数添加
                .addInterceptor(addHeaderInterceptor())//token过滤
                .addInterceptor(httpLoggingInterceptor)//日志，所有请求响应度看到
                .cache(cache)//缓存
                .connectTimeout(60L,TimeUnit.SECONDS)
                .readTimeout(60L,TimeUnit.SECONDS)
                .writeTimeout(60L,TimeUnit.SECONDS)
                .build()
    }
    /**
     * 设置公告参数
     */
    private fun addQueryParameInterceptor():Interceptor{
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val request :Request
            val modifieUrl = originalRequest.url().newBuilder()
                    .addQueryParameter("udid","d2807c895f0348a180148c9dfa6f2feeac0781b5")
                    .addQueryParameter("deviceModel", getMobileModel())
                    .build()
            request = originalRequest.newBuilder().url(modifieUrl).build()
            chain.proceed(request)
        }
    }
    /**
     * 设置头
     */
    private fun addHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
                    // Provide your custom header here
                    .header("token", token)
                    .method(originalRequest.method(), originalRequest.body())
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    /**
     * 设置缓存
     */
    private fun addCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            if (!NetworkUtils.isConnected()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build()
            }
            val response = chain.proceed(request)
            if (NetworkUtils.isConnected()) {
                val maxAge = 0
                // 有网络时 设置缓存超时时间0个小时 ,意思就是不读取缓存数据,只对get有用,post没有缓冲
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Retrofit")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build()
            } else {
                // 无网络时，设置超时为4周  只对get有用,post没有缓冲
                val maxStale = 60 * 60 * 24 * 28
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("nyn")
                        .build()
            }
            response
        }
    }
    fun getMobileModel(): String {
        var model: String? = Build.MODEL
        model = model?.trim { it <= ' ' } ?: ""
        return model
    }

}