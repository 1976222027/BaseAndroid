# Kt的使用

[Kotlin系列之let、with、run、apply、also函数的使用](https://blog.csdn.net/u013064109/article/details/78786646)

let使用见KtMainActivity

## 条件控制

### if
~~~
作为表达式
var max = a 
if (a < b) max = b

赋值给变量
val max = if (a > b) {
    print("Choose a")
    a
} else {
    print("Choose b")
    b
}
~~~
### 区间
盘判定是否在区间内
~~~
val x = 5
    val y = 9
    if (x in 1..8) {
        println("x 在区间内")
    }
~~~
### when

~~~
when (x) {
    1 -> print("x == 1")
    2 -> print("x == 2")
    else -> { // 注意这个块
        print("x 不是 1 ，也不是 2")
    }
}

else同switch 的default,如果很多分支满足同一条件可以合并
when (x) {
    0, 1 -> print("x == 0 or x == 1")
    else -> print("otherwise")
}

我们也可以检测一个值在（in）或者不在（!in）一个区间或者集合中：
when (x) {
    in 1..10 -> print("x is in the range")
    in validNumbers -> print("x is valid")
    !in 10..20 -> print("x is outside the range")
    else -> print("none of the above")
}

另一种可能性是检测一个值是（is）或者不是（!is）一个特定类型的值。注意： 由于智能转换，你可以访问该类型的方法和属性而无需 任何额外的检测。
fun hasPrefix(x: Any) = when(x) {
    is String -> x.startsWith("prefix")
    else -> false
}

when 也可以用来取代 if-else if链。 如果不提供参数，所有的分支条件都是简单的布尔表达式，而当一个分支的条件为真时则执行该分支
when {
    x.isOdd() -> print("x is odd")
    x.isEven() -> print("x is even")
    else -> print("x is funny")
}

when 中使用 in 运算符来判断集合内是否包含某实例：

fun main(args: Array<String>) {
    val items = setOf("apple", "banana", "kiwi")
    when {
        "orange" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
    }
}

~~~
## 循环控制

### For 循环
可对任何迭代器遍历

for (item in collection) print(item)

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








