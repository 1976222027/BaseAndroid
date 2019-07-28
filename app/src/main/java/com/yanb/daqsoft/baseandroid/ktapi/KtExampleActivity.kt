package com.yanb.daqsoft.baseandroid.ktapi

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import com.orhanobut.logger.Logger
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.ktapp.adapter.GrammarHomeAdapter
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
 * 四、字符串模板
 * 五、条件判断
 */
/**
 * 类的声明
 *
 */
class KtExampleActivity : BaseActivity(), View.OnClickListener {
    override fun getLayoutId(): Int {
        return R.layout.activity_kt_example
    }
    /**
     * 下面是变量用法
     * var 变量可修改
     * val 常量 只能赋值一次如java中的final
     * 注意：
     * 1、变量常量都可没有初始值，但是在引用前必须初始化
     * 2、没有初始化的时候必须声明类型,然后在赋值
     * 3、声明变量可空
     * 4、lateinit后期初始化，只能用于var 不可声明空，或基本数据类型（Int、Float、Double等）String是可以的,这里不可赋值，后面使用到的时候再赋值
     * 5、延迟初始化（当程序在第一次使用到这个变量（属性）的时候在初始化）只能是val
     */
    private var mName :String = "yanbo"// 可省了类型 kt自动推断类型
    private val mAge :Int = 25
    private val mContry:String?=null//可空的声明
    private lateinit var mSex :String
    private val mHeight:Int by lazy { 170 }//延迟初始化

    /**
     * 基本数据类型
     * Byte=> 字节 => 8位
     * Short => 短整型 => 16位
     * Int => 整型 => 32位
     * Long => 长整型 => 64位 由L标记
     * Float => 浮点型 => 32位 单精度浮点型由小写字母f或大写字符F标记
     * Double => 双精度浮点型 => 64位
     */

    private val mPhone :Long = 137_7806_9524L//下划线分组增加可读性

    /**
     * 比较
     * 数值比较 ==
     * 内存地址比较===
     */
    /**
     * 数值转换
     *
     * toByte() => 转换为字节型
     * toShort() => 转换为短整型
     * toInt() => 转换为整型
     * toLong() => 转换为长整型
     * toFloat() => 转换为浮点型
     * toDouble() => 转换为双精度浮点型
     * toChar() => 转换为字符型
     * toString() => 转换为字符串型
     */
    /**
     * 字符转义
     *
     * \t => 表示制表符
     * \n => 表示换行符
     * \b => 表示退格键（键盘上的Back建）
     * \r => 表示键盘上的Enter键
     * \\ => 表示反斜杠
     * \' => 表示单引号
     * \" => 表示双引号
     * \$ => 表示美元符号，如果不转义在kotlin中就表示变量的引用了
     * 其他的任何字符请使用Unicode转义序列语法。例：'\uFF00'
     */



    private val strA = "我是字符串A"
    private val strB = "我是字符串B"
    override fun initView(savedInstanceState: Bundle?) {
        mName
        /**
         * 绑定监听
         */
        btn_kt_sum.setOnClickListener(this)
        tv_kt_content.setOnClickListener(this)
        btn_kt_str.setOnClickListener(this)
        btn_kt_san.setOnClickListener(this)
        btn_kt_textif.setOnClickListener(this)
        btn_kt_set.setOnClickListener(this)
        btn_kt_array.setOnClickListener(this)
        btn_kt_lambda.setOnClickListener(this)
        btn_kt_it.setOnClickListener(this)
        btn_kt_x.setOnClickListener(this)
        btn_kt_noname.setOnClickListener(this)
        btn_kt_let.setOnClickListener(this)
        btn_kt_with.setOnClickListener(this)
        btn_kt_run.setOnClickListener(this)
        btn_kt_apply.setOnClickListener(this)
        textObject()
    }

    /**
     * -------------------------------------------------------------------------一、点击事件用法
     * 点击事件用法通过实现方法
     */
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onClick(v: View?) {
        /**
         * when 表达式
         * 多个满足同一条件用，隔开
         * R.id.btn_kt_canlong,R.id.btn_kt_san->
         * sum(1,2)
         */
        when (v?.id) {
            R.id.btn_kt_sum ->
                /**
                 * 字符串模板用法
                 * $varName 表示变量值
                 * ${vaeName.fun()变量方法返回值}
                 */
                ToastUtils.showCenterShort("1+4=${sum2(1,4)}+$strA")
            R.id.btn_kt_canlong->
                    vars("我是","可变长","函数")
            R.id.btn_kt_str->
                tv_kt_content.setText("1+2=${sum(1,2)}+$strA")
            R.id.btn_kt_san->
                tv_kt_content.setText(if (sum(1,2)>1) 3 else 5)// 三元表达式if else还可以 写成代码块加{ }
            R.id.btn_kt_textif->
                    testIf(5)
            R.id.btn_kt_set->
                    textList()
            R.id.btn_kt_array->
                    textArray()
            R.id.btn_kt_lambda->
                //LHaveParameters2(1,3)
                ToastUtils.showCenterShort("${LFunParameters(1,{num1, num2 -> num1+num2 })}")
            R.id.btn_kt_it->
                //itFilter()
                Logger.e("${itText(1,{it>5})}")
            R.id.btn_kt_x->
                forEachMap()
            R.id.btn_kt_noname->
                Logger.e("${noNameFun(1,2)}")
            R.id.btn_kt_let->
                    textLet()
            R.id.btn_kt_with->
                    testWith()
            R.id.btn_kt_run->
                textRun()
            R.id.btn_kt_apply->
                    textApply()
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
     *
     */
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
     * ----------------------------------------------------------------------------------四、字符串模板
     */
    /**
     * ----------------------------------------------------------------------------------五、条件判断
     *
     */
    private fun testIf(str :Int){
        //..表示1到10
        if (str in 1..10&&str is Int){
            ToastUtils.showCenterShort("$str+在1到10之间并且是int类型")
        }else{
            ToastUtils.showCenterShort("$str+不在1到10之间并且不是int类型")
        }
    }
    /**
     * ----------------------------------------------------------------------------------六、集合
     *
     */
    private fun textList(){
        // 集合的创建下面三个是只读无set方法
        val list : List<String> = listOf<String>()
        val set : Set<String> = setOf<String>()
        val map : Map<String,Int> = mapOf<String,Int>()
        /**
         * 对应上面的三个
         * var mutableList :MutableList<String> = mutableListOf()
         * var mutableSet: MutableSet<Int> = mutableSetOf()
         * var mutableMap: MutableMap<String, Int> = mutableMapOf()
         */
        var mutableList :MutableList<String> = mutableListOf()
        mutableList.add("张三")
        mutableList.clear()
        mutableList.add("王五")
        mutableList.add("李丹")
        mutableList.remove("李丹")

        /**
         * 介绍for循环用法
         * for ((index, value) in array.withIndex()) {
         * println("the element at $index is $value")
         *     //index是索引，value是值
         *  }
         */
        for (i in mutableList.indices){
            tv_kt_content.append(mutableList.get(i))
        }

    }
    /**
     * ----------------------------------------------------------------------------------七、数组
     * arrayOf     字符串数组
     * intArrayOf  Int数组
     * charArrayOf char数组
     * ByteArray、ShortArray、LongArray、FloatArray、DoubleArray、BooleanArray等
     *
     */
    private fun textArray(){
        var arrayStr = arrayOf("张三","李四")
        var arrayInt = intArrayOf(1,2,3)
        var arrayChar = charArrayOf('H','T')
        // 数组基本方法遍历
        for (str in arrayStr){
            arrayStr[1] = "王麻子"//替换值
            tv_kt_content.append(str)
        }
        //数组转换为字符串
        tv_kt_content.append(arrayInt.joinToString(""))
    }



    /**
     * ----------------------------------------------------------------------------------九、it的使用场景
     *
     */
    val itArr = arrayOf(1,2,5,7)

    // 取数组小于5的第一个打印
    val itFilter = {Logger.e("${itArr.filter { it<5 }.component1()}")}

    /**
     * 函数返回类型为Int,
     */
    fun itText(num1: Int,boo1:(Int)->Boolean):Int{
        return if (boo1(num1)){
            num1
        } else 0
    }
    /**
     * ----------------------------------------------------------------------------------九、下划线的使用场景
     *
     */
    val _map = mapOf("key1" to "value1","key2" to "value2","key3" to "value3")

    /**
     * 这里我们便利集合只打印value用下划线，未使用的参数，表示不处理这个参数
     */
    @RequiresApi(Build.VERSION_CODES.N)
    fun forEachMap(){
        _map.forEach { _,value-> Logger.e("$value")}
    }
    /**
     * ----------------------------------------------------------------------------------九、匿名函数
     * 3种写法
     * 匿名函数的参数传值，总是在小括号内部传递。而Lambda表达式传值，可以有省略小括号的简写写法。
     * 在一个不带标签的return语句中，匿名函数时返回值是返回自身函数的值，而Lambda表达式的返回值是将包含它的函数中返回。
     */
    val  noNameFun= fun (num1:Int,num2:Int):Int = num1+num2//后面的返回值类型可省了
    val  noNameFun1 = fun (num1:Int,num2:Int):Int{
        return num1+num2
    }
    /**
     * ----------------------------------------------------------------------------------十、object使用
     * 如本类class
     * 注意：
     * 1、object作为匿名实现如下
     *
     */
    fun textObject(){
        btn_kt_object.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                Logger.e("使用对象表达式监听")
                Logger.e("调用伴生对象的方法"+KtObject.sum(1,2))
            }
        })
        /**
         * lambda演变过程
         */
        //btn_kt_object.setOnClickListener({view: View? ->  })//简化一步
        //btn_kt_object.setOnClickListener({view ->  })//省略类型智能推断
        //btn_kt_object.setOnClickListener({})//view唯一参数未使用到直接省略
        //btn_kt_object.setOnClickListener(){}//由于小括号里面是一个函数可以把小括号提到外面
        //btn_kt_object.setOnClickListener {  }//如果这个函数只有一个参数就可以省略，这就是最简单的了

    }

    /**
     * ----------------------------------------------------------------------------------十、let使用
     * 1、作用域函数，内用it代替这个对象
     * 2、空判断，使用let函数处理需要针对一个可null的对象统一做判空处理。他的意思是如果ktobj不为空就走函数体内的方法
     */
    fun textLet(){
        val ktobj = KtObject("严博",25)
        ktobj.let {
            Logger.e("名字：${it.name}")
        }
    }

    /**
     * ----------------------------------------------------------------------------------十一、with使用
     * 函数体类直接调用对象属性避免重复写对象名
     * with返回值是函数体最后一行，或return指定
     *
     */
    fun testWith(){
        val ktobj = KtObject("严博",25)
        val result = with(ktobj){
            Logger.e("with调用--》${name}")
            1000
        }
        Logger.e("返回值->$result")
    }
    /**
     * ----------------------------------------------------------------------------------十一、run使用
     * run 就是let和with的结合体
     * 盖晗两者的所有优点和功能
     *
     */
    fun textRun(){
        val ktobj = KtObject("严博",25)
        ktobj.run { Logger.e("名字-》$name") }
        // 返回值道理同with
    }
    /**
     * ----------------------------------------------------------------------------------十一、apply使用
     * 和run类似但是他的返回值是对象本身
     * 可以连续判空处理
     * 他也集let run等的全部方法
     */
    fun textApply(){
        val ktobj = KtObject("严博",25)
        ktobj.apply { ktobj.name="李四" }.apply { Logger.e("apply调用对象-》$name") }
    }
    /**
     * ----------------------------------------------------------------------------------十一、also使用
     * 和let类似只是返回值是对象本身
     *
     */
    fun textAlso(){
        val ktobj = KtObject("严博",25)
        ktobj.also { it.name="王麻子" }.let { Logger.e("调用also的名字${it.name}") }
    }


    fun <T> prientInfo(t:T){
        //...泛型方法
    }
    // 泛型接口
    interface Generator<T>{
        fun next():T
    }
    internal inner class FruitGenerator<T> : Generator<T> {
        override fun next(): T {
            return null
        }

    }

}


