package com.yanb.daqsoft.baseandroid.login

import android.os.Bundle
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.login.model.LoginViewModel
import com.yanb.daqsoft.baselib.mvvmbase.BaseActivity
import com.yanb.daqsoft.baseandroid.databinding.ActivityLoginBinding

/**
 * 登录界面
 */
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override fun initVariableId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    // ActivityLoginBinding类是databinding框架自定生成的,对应activity_login.xml
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_login
    }


}
