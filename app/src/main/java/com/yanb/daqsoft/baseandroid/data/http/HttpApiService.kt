package com.yanb.daqsoft.baseandroid.data.http

import com.yanb.daqsoft.baseandroid.common.UrlConstants
import com.yanb.daqsoft.baseandroid.login.User
import com.yanb.daqsoft.baseandroid.ui.home.entity.ScenicEntity
import com.yanb.daqsoft.baselib.mvvmbase.http.BaseResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface HttpApiService {
    /**
     * 登录
     */
    @GET(UrlConstants.URL_LOGIN)
    fun login(@Query("ignoreCode") ignoreCode:String
    ,@Query("account") account:String
    ,@Query("password") password:String):Observable<BaseResponse<User>>
    /**
     * 景区列表
     */
    @GET(UrlConstants.URL_SCENIC_LIST)
    fun getScenicList(@Query("lng") lng:String,
                      @Query("lat") lat:String,
                      @Query("page") page:String,
                      @Query("limitPage") limitPage:String):Observable<BaseResponse<List<ScenicEntity>>>
}