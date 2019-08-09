package com.yanb.daqsoft.baseandroid.factory

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.yanb.daqsoft.baseandroid.login.model.LoginViewModel
import com.yanb.daqsoft.baseandroid.model.AppRepositoryModel

class AppViewModelFactory private constructor(private val application: Application, private val appRepositoryModel: AppRepositoryModel) : ViewModelProvider.NewInstanceFactory() {

    /**
     * 单列模式带参数
     * 这里也是伴生对象
     */
    companion object {
        @Volatile
        private var instance: AppViewModelFactory? = null
        fun getInstance(application: Application, appRepositoryModel: AppRepositoryModel) =
                instance ?: synchronized(this) {
                    instance ?: AppViewModelFactory(application, appRepositoryModel).also {
                        instance = it
                    }
                }
    }


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java!!)) {
            return LoginViewModel(application,appRepositoryModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}