package com.yanb.daqsoft.baseandroid.mvvmapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.databinding.ActivityMvvmMainBinding
import com.yanb.daqsoft.baseandroid.mvvmapp.viewmodel.MvvmMainViewModel
import com.yanb.daqsoft.baselib.mvvmbase.BaseMvvmActivity

/**
 * MVVM首页
 */
class MvvmMainActivity : BaseMvvmActivity<ActivityMvvmMainBinding,MvvmMainViewModel>() {
    override fun init() {
    }

    override fun initViewModel() {
    }

    override fun bindViewModel() {
    }
    override fun getLayoutResId(): Int =R.layout.activity_mvvm_main

}
