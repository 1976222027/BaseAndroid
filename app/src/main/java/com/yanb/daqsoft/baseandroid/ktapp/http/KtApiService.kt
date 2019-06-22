package com.yanb.daqsoft.baseandroid.ktapp.http

import com.yanb.daqsoft.baseandroid.ktapp.mvp.model.bean.HomeBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * 接口
 * @author 严博
 * @date 2019-6-21.10:15
 * @version 1.0.0
 * @since JDK 1.8
 */
interface KtApiService {
    /**
     * 首页精选
     */
    @GET("v2/feed?")
    fun getFirstHomeData(@Query("num") num:Int): Observable<HomeBean>
    /**
     * 根据 nextPageUrl 请求数据下一页数据
     */
    @GET
    fun getMoreHomeData(@Url url: String): Observable<HomeBean>
}