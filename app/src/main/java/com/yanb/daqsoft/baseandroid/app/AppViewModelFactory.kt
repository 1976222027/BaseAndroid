package com.yanb.daqsoft.baseandroid.app

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.annotation.VisibleForTesting
import com.yanb.daqsoft.baseandroid.data.DemoRepository
import com.yanb.daqsoft.baseandroid.login.model.LoginViewModel

class AppViewModelFactory private constructor(private val application: Application,private val repository:DemoRepository) : ViewModelProvider.NewInstanceFactory() {

    /**
     * 单例模式双重校验锁
     * 带属性的情况
     */
    companion object{
        @Volatile
        private var instance:AppViewModelFactory?=null
        fun getInstance(application: Application)=
                instance?: synchronized(this){
                    instance?:AppViewModelFactory(application,Injection.provideDemoRepository()).also {
                        instance = it
                    }
                }
    }


    @VisibleForTesting
    fun destroyInstance() {
        instance = null
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(application,repository) as T
        }
        return super.create(modelClass)
    }

}