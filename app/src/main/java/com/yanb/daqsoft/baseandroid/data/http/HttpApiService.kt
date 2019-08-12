package com.yanb.daqsoft.baseandroid.data.http

import com.yanb.daqsoft.baseandroid.common.UrlConstants
import com.daqsoft.xhttp.response.BaseResponse
import com.yanb.daqsoft.baseandroid.login.User
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