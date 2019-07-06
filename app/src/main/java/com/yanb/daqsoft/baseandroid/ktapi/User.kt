package com.yanb.daqsoft.baseandroid.ktapi

import com.orhanobut.logger.Logger

/**
 * Kotlin 类可以包含：构造函数和初始化代码块、函数、属性、内部类、对象声明。
 * 关键字class
 * User为；类名，大括号内是类体构成可无
 * 实例化User() 无new关键字
 * 使用类
 * user.name即可
 * 类里可有一个柱构造器和多个次构造器，主构造器为类头部的一部分位于类名称之后
 * 如果主构造器没有任何注解也没有任何可见修改时符那么constructor可省了（下面可省了）
 * 主构造器：
 * 1.不能包含任何代码，初始化代码可放在初始化代码段中 关键字init
 */
class User constructor(name: String) {
    /**
     * 次构造函数
     * 如果类既有主又有次那么次构造函数必须通过另一个次构造函数代理主构造函数如下this(...)
     */
    constructor(name: String,age:String):this(name){

    }

    /**
     * 初始化代码块
     * 这里可以使用主构造函数的属性值
     */
    init {
        Logger.e("我是初始化代码段")
    }
    val age = 0////默认实现了get方法没有set因为val只可读
    var height: Int = 10
        //默认实现了get set方法
        get() = field//field即当前值
        set(value) {
            if (value < 170) {
                field = value
            } else {
                field = -1
            }
        }
    /**
     * get 和set方法可以复写
     */
    var lastName: String = "yan"
        set
        get() = field.toUpperCase()// 将变量赋值后大写
    /**
     * 非空属性定义的时候必须初始化
     * lateinit可以延迟初始化
     */
    lateinit var book:Book
    fun setup(){
        book = Book()
    }

}