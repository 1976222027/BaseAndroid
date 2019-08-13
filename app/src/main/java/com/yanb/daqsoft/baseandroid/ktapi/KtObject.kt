package com.yanb.daqsoft.baseandroid.ktapi
import com.yanb.daqsoft.baselib.utils.KLog

/**
 * 类的声明
 * @author 严博
 * @date 2019-6-25.14:07
 * @version 1.0.0
 * @since JDK 1.8
 * 1、类没有结构体大括号可省了
 * 2.主构造函数关键字constructor（跟在类名后面里面传参数））
 * 4、声明属性的简便方法直接在主构造函数里面声明，必须加var变量，可以直接使用如下
 * 5、如下面类的声明中
 *
 * constructor什么时候可以省略
 * 1、在构造函数不具有注释符或者默认的可见性修饰符（public）时(下面必须写)
 * 2、class Test private constructor(num: Int){
 *   }
 *   class Test @Inject constructor(num: Int){
 *    }
 */
class KtObject constructor(var name:String,var age :Int){
    /**
     * 初始化代码块
     * 只能做初始化工作
     * 初始化的时候默认先调用这个方法关键字init
     */
    init {
        KLog.e("初始化代码")
    }
    /**
     * 二级构造函数
     * 因为此类有主构造函数
     * 所以二级构造函数必须委派给主构造函数如下:this(name,age)，使用this关键字
     */
    constructor(name:String,age:Int,sex:String):this(name,age){

    }

    /**
     * 伴生对象
     *
     */
    companion object Sum{
        fun sum(a:Int,b:Int):Int{
            return a+b
        }
    }

}
