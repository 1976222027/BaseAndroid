package com.yanb.daqsoft.baseandroid.app

import com.yanb.daqsoft.baseandroid.data.LocalDataSourceImpl
import com.yanb.daqsoft.baseandroid.model.AppRepositoryModel

object Injection {
    fun provideAppRepository():AppRepositoryModel{
        val localDataSource = LocalDataSourceImpl.instance
        return AppRepositoryModel.getInstance(localDataSource)
    }
}