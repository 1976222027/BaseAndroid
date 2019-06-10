package com.yanb.daqsoft.baseandroid.ktapi

import android.os.Bundle
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
 * 三、定义常量与变量
 */
class KtExampleActivity : BaseActivity(), View.OnClickListener {
    override fun initView(savedInstanceState: Bundle?) {
        /**
         * 绑定监听
         */
        btn_kt_sum.setOnClickListener(this)
        tv_kt_content.setOnClickListener(this)
    }

    /**
     * -------------------------------------------------------------------------一、点击事件用法
     * 点击事件用法通过实现方法
     */
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_kt_sum ->
                /**
                 * 字符串模板用法
                 * $varName 表示变量值
                 * ${vaeName.fun()变量方法返回值}
                 */
                ToastUtils.showCenterShort("1+4=${sum2(1,4)}+")
            R.id.btn_kt_canlong->
                    vars("我是","可变长","函数")
        }
    }

    /**
     * 点击事件通过xmlonclick方法
     */
    fun click(v:View?){
        when(v?.id){
            R.id.btn_kt_noreturn ->
                noReturn("无返回值")
        }
    }

    override fun start() {

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

    /**
     * 可变长参数函数
     * vararg修饰
     */
    fun vars(vararg v:String){
        for (vt in v){
            tv_kt_content.setText(vt)
        }
        val age:Int

    }
    /**
     * ----------------------------------------------------------------------------------三、定义常量与变量
     * 常量val 只能赋值一次如java中的final
     * 变量var
     * 变量常量都可没有初始值，但是在引用前必须初始化
     *
     */

    val name : String = "张三"
    val name1 = "李四"// 自动推断类型
    fun text(){
        val age:Int//不在声明时初始化必须指定类型
        age = 1 //明确赋值 赋值一次再赋值就报错

        var sex = "男"
        sex = "女"// 变量可以修改

        // 类型后面加？表示可为 空
        var height:String? = "170cm"
        var str :String ? = null//当str为空则等于null
        val heght2 = height!!.toInt()// 抛出空指针异常
        val height3 = height?.toInt()// 不做处理返回NULL
        val height4 = height?.toInt() ?: -1//height为空返回-1

    }

    /**
     * ----------------------------------------------------------------------------------四、NULL检查机制
     *
     *
     */
}


