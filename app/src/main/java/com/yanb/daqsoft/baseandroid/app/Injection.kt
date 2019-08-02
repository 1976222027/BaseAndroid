package com.yanb.daqsoft.baseandroid.app

import com.yanb.daqsoft.baseandroid.data.DemoRepository

/**
 * 静态方法如果这个类下所有方法都是静态在类名前用object修饰即可
 * 部分静态方法用companion object包裹
 */
class Injection {
    companion object{
        fun provideDemoRepository(): DemoRepository {
            //网络API服务
            val apiService = RetrofitClient.getInstance().create(DemoApiService::class.java)
            //网络数据源
            val httpDataSource = HttpDataSourceImpl.getInstance(apiService)
            //本地数据源
            val localDataSource = LocalDataSourceImpl.getInstance()
            //两条分支组成一个数据仓库
            return DemoRepository.getInstance(httpDataSource, localDataSource)
        }
    }

}