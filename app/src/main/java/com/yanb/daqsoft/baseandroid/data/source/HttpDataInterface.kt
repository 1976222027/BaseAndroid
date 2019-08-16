package com.yanb.daqsoft.baseandroid.data.source

import com.yanb.daqsoft.baseandroid.login.User
import com.yanb.daqsoft.baseandroid.ui.home.entity.ScenicEntity
import com.yanb.daqsoft.baselib.mvvmbase.http.BaseResponse
import io.reactivex.Observable

interface HttpDataInterface {
    /**
     * 登录
     */
    fun login(ignoreCode:String,account:String,pasd:String):Observable<BaseResponse<User>>
    /**
     * 请求景区列表数据
     */
    fun getScenicList(lng:String,lat:String,page:String,pageSize:String):Observable<BaseResponse<List<ScenicEntity>>>
}