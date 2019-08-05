# 参数

## 具名参数
在调用函数的时候传入参数的时候写参数名自动会指定到对应的参数上

~~~
调用
sum21(arg2 = 1,arg1 = 4)
fun  sum21(arg1:Int,arg2:Int):Int{
        return arg1+arg2
    }
~~~

## 变长参数

~~~
 hello(2.0,1,2,2,args = "你好")
 fun hello(double: Double,vararg ints:Int,args:String){
        // ...
    }
~~~
## 默认参数

~~~
 val array = intArrayOf(1,2,4,5)
// 这里的*表示变长参数分别展开传入
hello(ints = *array,args = "你好")
fun hello(double: Double=3.0,vararg ints:Int,args:String){
        // ...
    }
~~~






