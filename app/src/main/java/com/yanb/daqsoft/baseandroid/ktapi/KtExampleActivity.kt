package com.yanb.daqsoft.baseandroid.ktapi

import android.view.View
import com.orhanobut.logger.Logger
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baselib.ktbase.BaseActivity
import com.yanb.daqsoft.baselib.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_kt_example.*

/**
 * 本界面介绍kt的用法
 *
 * 通过大标题搜索用法
 *
 * 一、点击事件用法
 * 1、控件.btn_kt_sum.setOnClickListener { ToastUtils.showCenterShort("你好") }
 * 2、实现View.OnClickListener，具体见代码
 * 3、布局onClick 具体见代码
 * 二、函数的定义
 */
class KtExampleActivity : BaseActivity(), View.OnClickListener {

    /**
     * -------------------------------------------------------------------------一、点击事件用法
     * 点击事件用法通过实现方法
     */
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_kt_sum ->
                /**
                 * 字符串模板用法
                 *
                 */
                ToastUtils.showCenterShort("1+4=${sum2(1,4)}")
        }
    }
    fun click(v:View?){
        when(v?.id){
            R.id.btn_kt_noreturn ->
                noReturn("无返回值")
        }
    }

    override fun start() {
    }

    override fun initView() {
        /**
         * 绑定监听
         */
        btn_kt_sum.setOnClickListener(this)
    }

    override fun initData() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_kt_example
    }


    /**
     * ----------------------------------------------------------------------------------二、函数的定义
     * 介绍函数的定义
     */
    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    /**
     * 表达式作为函数体，返回类型自动推断
     */
    fun sum1(a: Int, b: Int) = a + b
    public fun sum2(a: Int, b: Int):Int = a + b//public必须指明返回类型
    /**
     * 无返回值
     * Unit可以省略 public也是一样
     */
    fun noReturn(a:String) :Unit{
       ToastUtils.showCenterShort("无返回值:"+a)
    }
    fun vars(vararg v:String){
        for (vt in v){

        }
    }
}


