package com.yanb.daqsoft.baseandroid.login.model

import android.app.Application
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import com.yanb.daqsoft.baseandroid.data.DemoRepository
import com.yanb.daqsoft.baselib.mvvmbase.BaseViewModel
import com.yanb.daqsoft.baselib.mvvmbase.binding.command.BindingAction
import com.yanb.daqsoft.baselib.mvvmbase.binding.command.BindingCommand
import com.yanb.daqsoft.baselib.mvvmbase.binding.command.BindingConsumer

class LoginViewModel:BaseViewModel<DemoRepository>{
    // 用户名绑定
    var userName = ObservableField<String>("")
    // 密码绑定
    var passWord = ObservableField<String>("")
    var clearUserVisible = ObservableInt()

    var onTextChangedUser:BindingCommand<String> = BindingCommand<String>(object :BindingConsumer<String>{
        override fun call(t: String?) {
            if (t.isNullOrEmpty()){
                clearUserVisible.set(View.GONE)
            }else{
                clearUserVisible.set(View.VISIBLE)
            }
        }

    })
    // 清除用户
    var clearUserName = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            userName.set("")
        }
    });


    /**
     * 次构造方法
     */
    constructor(application: Application,respository: DemoRepository):super(application,respository){
        userName.set(model.getUserName())
        passWord.set(model.getPassword())
    }



}