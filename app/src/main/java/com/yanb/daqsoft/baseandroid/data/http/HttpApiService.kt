package com.yanb.daqsoft.baseandroid.data.http

import com.yanb.daqsoft.baseandroid.common.UrlConstants
import com.yanb.daqsoft.baseandroid.login.entity.User
import com.yanb.daqsoft.baselib.mvvmbase.http.BaseResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface HttpApiService {
    @GET(UrlConstants.URL_LOGIN)
    fun login(@Query("ignoreCode") ignoreCode:String
    ,@Query("account") account:String
    ,@Query("password") password:String):Observable<BaseResponse<User>>

}