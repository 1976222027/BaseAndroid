package com.yanb.daqsoft.baseandroid.data.http

import android.content.Context
import com.yanb.daqsoft.baselib.mvvmbase.http.cookie.CookieJarImpl
import com.yanb.daqsoft.baselib.mvvmbase.http.cookie.store.PersistentCookieStore
import com.yanb.daqsoft.baselib.mvvmbase.http.interceptor.BaseInterceptor
import com.yanb.daqsoft.baselib.mvvmbase.utils.Utils
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File

class RetrofitClient {
    // 超时时间
    val DEFAULT_TIMEOUT:Int = 20
    // 缓存时间
    val CACHE_TIMEOUT:Int = 10*1024*1024
    var BASE_URL = "https://www.oschina.net/"
    /**
     * 缓存路径
     */
    val httpCacheDirectory:File by lazy {
        File(mContent.cacheDir,"goldze_cache")
    }
    val cache :Cache by lazy {
        Cache(httpCacheDirectory,CACHE_TIMEOUT.toLong())
    }

    val mContent:Context by lazy {
        Utils.getContext()
    }
    val okHttpClient:OkHttpClient by lazy {
        OkHttpClient.Builder().cookieJar(CookieJarImpl(PersistentCookieStore(mContent)))
                .addInterceptor(BaseInterceptor())
    }
    /**
     * 下面介绍静态类部类单列
     */
    companion object{
        val instance = SingletonHolder.holder
    }
    private object SingletonHolder{
        val holder = RetrofitClient()
    }
    private constructor(){
        RetrofitClient(BASE_URL,null)
    }
    private constructor(url:String,headers:Map<String,String>?){

    }

}