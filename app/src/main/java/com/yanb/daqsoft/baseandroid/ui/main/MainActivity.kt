package com.yanb.daqsoft.baseandroid.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yanb.daqsoft.baseandroid.BR
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.ui.main.model.MainViewModel
import com.yanb.daqsoft.baselib.mvvmbase.base.BaseActivity

class MainActivity : BaseActivity<com.yanb.daqsoft.baseandroid.databinding.ActivityMainBinding, MainViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int = R.layout.activity_main

    override fun initVariableId(): Int  = 0

}
