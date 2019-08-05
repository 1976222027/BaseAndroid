package com.yanb.daqsoft.baseandroid.data.source.http

import com.yanb.daqsoft.baselib.mvvmbase.http.BaseResponse
import io.reactivex.Observable
import retrofit2.http.GET



interface ApiService {
    @GET("action/apiv2/banner?catalog=1")
    fun demoGet(): Observable<BaseResponse<String>>
}