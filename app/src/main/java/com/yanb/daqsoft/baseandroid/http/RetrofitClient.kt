package com.yanb.daqsoft.baseandroid.http

import com.yanb.daqsoft.baseandroid.BuildConfig
import com.yanb.daqsoft.baselib.mvvmbase.http.cookie.CookieJarImpl
import com.yanb.daqsoft.baselib.mvvmbase.http.cookie.store.PersistentCookieStore
import com.yanb.daqsoft.baselib.mvvmbase.http.interceptor.BaseInterceptor
import com.yanb.daqsoft.baselib.mvvmbase.http.interceptor.CacheInterceptor
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
import java.lang.Exception
import java.util.concurrent.TimeUnit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers




/**
 * 单列实现网络请求
 * 这里我使用静态类部类方式
 * 私有化主构造器
 */
class RetrofitClient private constructor(){
    // 超时时间
    private val DEFAULT_TIMEOUT = 20
    // 缓存时间
    private val CACHE_TIMEOUT = 10*1024*1024
    /**
     * 服务器端跟地址
     */
    private var BASE_URL = "https://www.oschina.net/"
    private lateinit var okHttpClient: OkHttpClient
    // 做一个延迟初始化
    private val mContext by lazy {
        Utils.getContext()
    }
    /**
     * 缓存路径的文件
     */
    private val mHttpCacheDirectory by lazy {
        File(mContext.cacheDir,"yanb_cache")
    }
    private val mCache by lazy {
        Cache(mHttpCacheDirectory, CACHE_TIMEOUT.toLong())
    }

    private lateinit var mRetrofit: Retrofit
    /**
     * 下面是次构造函数
     */
    constructor(url:String):this(){
        RetrofitClient(url,null)
    }
    constructor(url: String,headers:Map<String,String>?):this(){
        if (url.isNullOrEmpty()){
            BASE_URL = url
        }
        val sslSocketFactory = HttpsUtils.getSslSocketFactory()
        okHttpClient = OkHttpClient.Builder()
                .cookieJar(CookieJarImpl(PersistentCookieStore(mContext)))
                .addInterceptor(BaseInterceptor(headers))
                .addInterceptor(CacheInterceptor(mContext))
                .sslSocketFactory(sslSocketFactory.sSLSocketFactory,sslSocketFactory.trustManager)
                .addInterceptor(LoggingInterceptor
                        .Builder()
                        .loggable(BuildConfig.DEBUG)
                        .setLevel(Level.BASIC)
                        .log(Platform.INFO)
                        .request("Request")
                        .response("Response")
                        .addHeader("log-header","I am the log request header.")
                        .build())
                .connectTimeout(DEFAULT_TIMEOUT.toLong(),TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT.toLong(),TimeUnit.SECONDS)
                .connectionPool(ConnectionPool(8,15,TimeUnit.SECONDS))
                .build()

        mRetrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build()

    }

    /**
     * 我们使用泛型创建Retrofit
     */
    fun <T> create(service: Class<T>?): T {
        if (service == null) {
            throw RuntimeException("Api service is null!")
        }
        return mRetrofit.create(service)
    }
    fun <T> execute(observable: Observable<T>, subscriber: Observer<T>): T? {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)

        return null
    }

    /**
     * 以下是静态类不类实现单列
     */
    companion object{
        val instance =RetrofitHolder.holder
    }
    private object RetrofitHolder{
        val holder = RetrofitClient()
    }


}