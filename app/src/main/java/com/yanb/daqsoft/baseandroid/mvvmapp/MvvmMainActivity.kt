package com.yanb.daqsoft.baseandroid.mvvmapp

import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.databinding.ActivityMvvmMainBinding
import com.yanb.daqsoft.baseandroid.mvvmapp.viewmodel.MvvmMainViewModel

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
