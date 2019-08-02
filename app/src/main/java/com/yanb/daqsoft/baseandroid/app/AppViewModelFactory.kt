package com.yanb.daqsoft.baseandroid.app

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.annotation.VisibleForTesting
import com.yanb.daqsoft.baseandroid.data.DemoRepository
import com.yanb.daqsoft.baseandroid.login.model.LoginViewModel

class AppViewModelFactory() : ViewModelProvider.NewInstanceFactory() {
    private  val repositorys: DemoRepository by lazy {

    }
    private  val applications: Application?=null
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

    /**
     * 不带属性的常用情况
     */

    constructor(application: Application,repository: DemoRepository):this(){
        repositorys = repository
        applications = application
    }


    @VisibleForTesting
    fun destroyInstance() {
        instance = null
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(applications,repositorys) as T
        }
        return super.create(modelClass)
    }

}