package com.yanb.daqsoft.baseandroid.ktapi

class X
class Book {
    // 对于int这种初始化为0
    var price = 0
    lateinit var name:String//这里延迟初始化以便不写？或！！麻烦
    /**
     * 在使用这个成员变量的时候必须先初始化这个变量
     */
    lateinit var x: X
    // 在使用的时候才回去执行lazy里的方法
    val xx by lazy {
        // 可做某些操作 val 的延迟初始化方案
        X()
    }

    /**
     * 未知类型
     * 用可空
     * 使用的时候必须加？或！！
     */
    var height:String ?=null

}
