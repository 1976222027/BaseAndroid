package com.yanb.daqsoft.baseandroid.app

import com.yanb.daqsoft.baseandroid.data.source.LocalDataSourceImpl
import com.yanb.daqsoft.baseandroid.data.http.HttpApiService
import com.yanb.daqsoft.baseandroid.data.source.HttpDataImpl
import com.yanb.daqsoft.baseandroid.data.http.RetrofitClient
import com.yanb.daqsoft.baseandroid.model.AppRepositoryModel

object Injection {
    fun provideAppRepository():AppRepositoryModel{
        // 网络服务
        val httpApiService = RetrofitClient.instance.create(HttpApiService::class.java)
        val httpDataInterface =  HttpDataImpl.getInstance(httpApiService)
        val localDataSource = LocalDataSourceImpl.instance
        return AppRepositoryModel.getInstance(localDataSource,httpDataInterface)
    }
}