package com.yanb.daqsoft.baseandroid.ktapp.mvp.model

import com.daqsoft.ktxhttp.XhttpKt
import com.daqsoft.ktxhttp.scheduler.SchedulerUtils
import com.yanb.daqsoft.baseandroid.http.XhttpUtils
import com.yanb.daqsoft.baseandroid.ktapp.http.KtApiService
import com.yanb.daqsoft.baseandroid.ktapp.mvp.model.bean.HomeBean
import io.reactivex.Observable

/**
 * kt首页model
 * @author 严博
 * @date 2019-6-13.15:37
 * @version 1.0.0
 * @since JDK 1.8
 */
class KtGrammarModel{
    /**
     * 获取首页数据
     */
    fun getHomeData(num :Int):Observable<HomeBean>{
        return XhttpKt.retrofitInstance.create(KtApiService::class.java).getFirstHomeData(num)
                .compose(SchedulerUtils.ioToMain())
    }
    /**
     * 首页加载跟多
     */
    fun loadMore(url : String) :Observable<HomeBean>{
        return XhttpKt.retrofitInstance.create(KtApiService::class.java).getMoreHomeData(url)
                .compose(SchedulerUtils.ioToMain())
    }
}
