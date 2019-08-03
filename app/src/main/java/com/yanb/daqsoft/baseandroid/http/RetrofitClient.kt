package com.yanb.daqsoft.baseandroid.http

import com.yanb.daqsoft.baselib.mvvmbase.utils.Utils
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import java.lang.Exception


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
        okHttpClient?.let {

        }

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