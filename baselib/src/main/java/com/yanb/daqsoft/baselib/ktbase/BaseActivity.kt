package com.yanb.daqsoft.baselib.ktbase

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.yanb.daqsoft.baselib.view.MultipleStatusView

/**
 * kotlin基础baseActivity
 * @author 严博
 * @date 2019-6-5.16:46
 * @version 1.0.0
 * @since JDK 1.8
 * :继承
 * var: 变量
 * val:常量
 *
 */
abstract class BaseActivity : AppCompatActivity(){
    /**
     * 多布局状态的View切换
     * NULL检查机制
     * Kotlin的空安全设计对于声明可为空的参数，在使用时要进行空判断处理，有两种处理方式，
     * 1、字段后加!!像Java一样抛出空异常，
     * 2、另一种字段后加?可不做处理返回值为 null或配合?:做空判断处理
     */
    protected var mLayoutStatusView:MultipleStatusView?=null

    /**
     * onCreate
     * override 重写标识
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initData()
        initView()
        start()
        initListener()
    }

    /**
     * 初始化监听
     * 函数定义关键字fun 参数格式：参数:类型
     * 此方法无返回值可以用:Unit加在后面也可以不写
     */
    private fun initListener(){
        mLayoutStatusView?.setOnClickListener(mRetryClikListener)
    }

    /**
     * 如果这个类要被修饰需要用open修饰
     */
    open val mRetryClikListener: View.OnClickListener = View.OnClickListener {
        start()
    }


    /**
     * 请求
     */
    abstract fun start()

    /**
     * 初始化View
     */
    abstract fun initView()

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 获取布局ID
     * 返回值用：加类型
     */
    abstract fun getLayoutId():Int
    /**
     * 打卡软键盘
     */
    fun openKeyBord(mEditText: EditText, mContext: Context) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }
    /**
     * 关闭软键盘
     */
    fun closeKeyBord(mEditText: EditText, mContext: Context) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mEditText.windowToken, 0)
    }
}
