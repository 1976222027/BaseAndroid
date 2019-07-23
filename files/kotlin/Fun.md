## 函数

关键字Fun

### 有返回值

~~~
/**
     * 有返回值
     */
    fun sum(a: Int, b: Int): Int {
        return a + b
    }
    /**
     * 如果函数返回值是一个表达式直接=
     * 并且类型都可以去掉
     */
    fun sum2(a:Int,b:Int)=a+b

    /**
     * 函数不需要名字情况
     * 函数赋值到变量
     * 调用sums(1,2)
     */
    val sums = fun(a:Int,b:Int)=a+b
~~~

### 无返回值

~~~
/**
     * 无返回值Unit可省略
     */
    fun text(name: String): Unit {

    }
~~~


