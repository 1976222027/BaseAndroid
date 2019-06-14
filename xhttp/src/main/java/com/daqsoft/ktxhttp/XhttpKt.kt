package com.daqsoft.ktxhttp

/**
 * kt写的网络请求
 * @author 严博
 * @date 2019-6-13.15:52
 * @version 1.0.0
 * @since JDK 1.8
 * object 的用法
 *
 */
object XhttpKt{
    /**
     * 演示单列类名object修饰就变成单列了
     * 对象直接点里面的方法
     * object编译成一个类拥有一个静态成员来持有对自己的引用，并且这个静态成员的名称为INSTANCE，当然这个INSTANCE是单例的
     * object修饰的类尽管和普通类的声明一样，可以包含属性、方法、初始化代码块以及可以继承其他类或者实现某个接口，
     * 但是它不能包含构造器（包括主构造器以及次级构造器）
     * object也可定义在一个类内部
     *  class ObjectOuter {
     *      object Inner{
     *          fun method(){
     *              println("I'm in inner class")
     *              }
     *          }
     *       }
     *  fun main(args: Array<String>) { ObjectOuter.Inner.method() }
     */
    fun method(){

    }
    /**
     * 下面介绍单列模式的运用
     */
//    val service :KtApiService by lazy(LazyThreadSafetyMode.NONE){
//
//    }
}