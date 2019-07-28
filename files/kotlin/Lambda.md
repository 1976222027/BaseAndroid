## Lambda表达式

### 一、基本写法

其实就是匿名函数

语法：

{ [参数列表]->[函数体，最后一行是返回值] }

~~~
val textLambda = {a:Int,b:Int->a+b}

/**
     * 无参数情况
     */
    fun NoParameters(){
        ToastUtils.showCenterShort("无参数")
    }
    //等价于
    val LNoParameters = { ToastUtils.showCenterShort("无参数")}

    /**
     * 有参数情况
     */
    fun HaveParameters(a:Int,b: Int) :Int{
        return a+b
    }
    //等价于
    val LHaveParameters:(Int,Int)-> Int= {a,b->a+b}
    //或
    val LHaveParameters2 = {a:Int,b:Int->ToastUtils.showCenterShort("a+b=${a+b}")}
~~~

### 二、类型表示

~~~
()->Unit
无参返回值为Unit

(Int)->Int
传入整型，返回整型

（String,(String)->String）->Boolean
传入字符串 Lambda表达式，返回Boolean
~~~

### 三、调用

用（）进行调用等价于invoke(),如下：

~~~
val sumsss = {a:Int,b:Int->a+b}
  sumsss(2,3)
  等价于
  sumsss.invoke(2,3)
  
  
   /**
     * lambda表达式作为函数中参数的时候
     */
    fun FunParameters(a:Int,b:Int):Int{
        return a+b
    }

    fun FunSum(num1:Int,num2:Int):Int{
        return num1+num2
    }
    //等价于(invoke通过函数变量调用自身)
    fun LFunParameters(a:Int,b:(num1:Int,num2:Int)->Int):Int{
        return a+b.invoke(3,5)
    }
~~~

### 四、简化

- 函数参数调用时最后一个Lambda可以移出去
- 函数参数只有一个Lambda,调用时小括号可省略
- Lambda只有一个参数可默认为it
- 入参，返回值与形参一致的函数可以用函数引用的方式作为实参传入