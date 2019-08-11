package com.yanb.daqsoft.baseandroid.data.http

import android.content.Context
import com.yanb.daqsoft.baseandroid.BuildConfig
import com.yanb.daqsoft.baseandroid.common.ParamsConstants
import com.yanb.daqsoft.baselib.mvvmbase.http.cookie.CookieJarImpl
import com.yanb.daqsoft.baselib.mvvmbase.http.cookie.store.PersistentCookieStore
import com.yanb.daqsoft.baselib.mvvmbase.http.interceptor.BaseInterceptor
import com.yanb.daqsoft.baselib.mvvmbase.http.interceptor.CacheInterceptor
import com.yanb.daqsoft.baselib.mvvmbase.http.interceptor.ParamsInterceptor
import com.yanb.daqsoft.baselib.mvvmbase.http.interceptor.logging.Level
import com.yanb.daqsoft.baselib.mvvmbase.http.interceptor.logging.LoggingInterceptor
import com.yanb.daqsoft.baselib.mvvmbase.utils.Utils
import io.reactivex.Observable
import io.reactivex.Observer
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * 网络请求
 */
class RetrofitClient{
    companion object{
        val instance:RetrofitClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitClient()
        }
    }
    // 超时时间
    val DEFAULT_TIMEOUT:Long = 20
    // 缓存时间
    val CACHE_TIMEOUT:Long = 10*1024*1024
    var BASE_URL = "http://ptisp.daqsoft.com/govapi/"
    /**
     * 缓存路径
     */
    val httpCacheDirectory:File by lazy {
        File(mContent.cacheDir,"goldze_cache")
    }
    val cache :Cache by lazy {
        Cache(httpCacheDirectory,CACHE_TIMEOUT)
    }

    val mContent:Context by lazy {
        Utils.getContext()
    }
    val okHttpClient :OkHttpClient
    val retrofit: Retrofit
    private constructor(){
        okHttpClient = OkHttpClient.Builder().cookieJar(CookieJarImpl(PersistentCookieStore(mContent)))
                /**
                 * 请求头
                 */
                //.addInterceptor(BaseInterceptor(headers))
                /**
                 * 缓存
                 */
                .addInterceptor(CacheInterceptor(mContent))
                /**
                 * 这里配置统一参数
                 * addQueryParam添加参数
                 */
                .addInterceptor(ParamsInterceptor.Builder()
                        .addQueryParam(ParamsConstants.PARAMS_SITECODE_KTY,ParamsConstants.PARAMS_SITECODE_VALUE)
                        .build())
                /**
                 * 日志打印
                 */
                .addInterceptor(LoggingInterceptor
                        .Builder()//构建者模式
                        .loggable(BuildConfig.DEBUG)//是否开启日志打印
                        .setLevel(Level.BASIC)//打印的的等级
                        .log(Platform.INFO)// 打印类型
                        .request("请求")// request的Tag
                        .response("结果")// Response的Tag
                        .addHeader("log-header","I am the log request header.")// 添加打印头, 注意 key 和 value 都不能是中文
                        .build())
                .connectTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
                // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里8个，和每个保持时间为10s
                .connectionPool(ConnectionPool(8,15,TimeUnit.SECONDS))
                .build()
        retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()
    }

    fun <T> create(service: Class<T>?): T {
        if (service == null) {
            throw RuntimeException("Api service is null!")
        }
        return retrofit.create(service)
    }
    fun <T> execute(observable: Observable<T>, subscriber: Observer<T>): T? {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)

        return null
    }
}