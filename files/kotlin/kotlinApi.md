# Kt的使用

[Kotlin系列之let、with、run、apply、also函数的使用](https://blog.csdn.net/u013064109/article/details/78786646)

let使用见KtMainActivity

## 一、高阶函数

### 1、let

~~~
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
~~~

# 类
# NULL检查机制

~~~
 /**
     * Kotlin的空安全设计对于声明可为空的参数，在使用时要进行空判断处理，有两种处理方式，
     * 字段后加!!像Java一样抛出空异常，
     * 另一种字段后加?可不做处理返回值为 null或配合?:做空判断处理
     */
    //类型后面加?标识可为空
    var age:String?="23"
    //val ages = age!!.toInt()
~~~







