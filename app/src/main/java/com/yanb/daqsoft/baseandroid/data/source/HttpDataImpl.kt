package com.yanb.daqsoft.baseandroid.data.source

import com.daqsoft.xhttp.response.BaseResponse
import com.yanb.daqsoft.baseandroid.data.http.HttpApiService
import com.yanb.daqsoft.baseandroid.login.User
import com.yanb.daqsoft.baselib.mvvmbase.http.BaseResponse

import io.reactivex.Observable

class HttpDataImpl private constructor(private val httpApiService: HttpApiService): HttpDataInterface {
    /**
     * 单列模式
     */
    companion object{
        @Volatile
        private var instance : HttpDataImpl?=null
        fun getInstance(httpApiService: HttpApiService) = instance
                ?: synchronized(this){
            instance
                    ?: HttpDataImpl(httpApiService).also {
                instance =it
            }
        }
    }

    /**
     * 登录请求
     */
    override fun login(ignoreCode:String,account:String,pasd:String): Observable<BaseResponse<User>> {
        return httpApiService.login(ignoreCode,account,pasd)
    }
}