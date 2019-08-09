package com.yanb.daqsoft.baseandroid.utils

import com.yanb.daqsoft.baseandroid.common.UrlConstants

/**
 * App工具类
 * 这里我们介绍伴生对象
 * java中静态方法在kotlin中的使用
 */
class HelpUtils {
    /**
     * 伴生对象的建立
     * object后跟名称，但是kotlin在调用伴生对象里的方式时不需要名称所以可省了
     *
     * java中调用:
     * java中调用这个的方法如果没的名称我们使用Companion去调用，HelpUtils.Companion.sum()
     * 也可以和java静态方法调用一样的形式，但是在属性上加@JvmField方法上加@JvmStatic，kotlin调用不变
     */
    companion object{
        /**
         *  以下两种属性同java中的static final 静态常量
         */
        const val sex = "男"
        @JvmField
        val flag = false
        /**
         * 方法，也可以建立对象属性
         */
        @JvmStatic
        fun sum(a:Int,b:Int):Int{
            return a+b
        }
    }
}