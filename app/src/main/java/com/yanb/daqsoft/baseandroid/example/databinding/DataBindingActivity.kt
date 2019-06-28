package com.yanb.daqsoft.baseandroid.example.databinding

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.databinding.ActivityDataBindingBinding
import com.yanb.daqsoft.baseandroid.example.model.DataBindData

/**
 * databingding学习界面
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-6-27.11:17
 * @since JDK 1.8
 * 基本使用API
 * android:visibility="@{user.isAdult ? View.VISIBLE : View.GONE}"//支持三元运算符。需要导入View包
 * android:text="@{String.valueOf(user.age)}"//@{只能是String}
 * android:text="@{StringUtils.capitalize(user.firstName)}"//前面导入了这个包，可以调用这个静态方法
 * android:text="@{user.displayName ?? user.lastName}"//它表达的是如果左边不是 null 的，那么使用左边的值，否者使用右边的值
 *
 */
class DataBindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBindData:ActivityDataBindingBinding = DataBindingUtil.setContentView(this,R.layout.activity_data_binding)
        dataBindData.data = DataBindData("严博", 12)
    }
}
