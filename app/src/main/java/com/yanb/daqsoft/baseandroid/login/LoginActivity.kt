package com.yanb.daqsoft.baseandroid.login

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.yanb.daqsoft.baseandroid.BR
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.app.AppViewModelFactory
import com.yanb.daqsoft.baseandroid.login.model.LoginViewModel
import com.yanb.daqsoft.baselib.mvvmbase.BaseActivity
import com.yanb.daqsoft.baseandroid.databinding.ActivityLoginBinding

/**
 * 登录界面
 */
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override fun initVariableId(): Int {
        return BR.viewModel
    }
    // ActivityLoginBinding类是databinding框架自定生成的,对应activity_login.xml
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_login
    }

    override fun initViewModel(): LoginViewModel {
        // 使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        val factory = AppViewModelFactory.getInstance(application)
        return ViewModelProviders.of(this,factory).get(LoginViewModel::class.java)
    }



}
