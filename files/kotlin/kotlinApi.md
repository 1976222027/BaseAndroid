
[Kotlin系列之let、with、run、apply、also函数的使用](https://blog.csdn.net/u013064109/article/details/78786646)

let使用见KtMainActivity

# 一、条件控制

## if
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
## 区间
盘判定是否在区间内
~~~
val x = 5
    val y = 9
    if (x in 1..8) {
        println("x 在区间内")
    }
~~~
## when

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
# 二、循环控制

## For 循环
可对任何迭代器遍历

for (item in collection) print(item)

# 三、高阶函数

## 1、let

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
## 2、lateinit和by lazy 延迟初始化

- lateinit:延迟初始化属性

一般属性声明非空类型必须初始化，也支持可空？=null

~~~
val nameA:String //报红线 提示如下 
Property must be initialized or be abstract

lateinit var name:String //lateinit可以避免这种情况
在初始化前访问一个lateinit属性会抛出一个特定异常，该异常明确标识该属性被访问及它没有初始化的事实。
~~~
- by lazy:惰性初始化

惰性初始化是一种常见的模式，直到第一次访问该属性的时候，才根据需要创建对象的一部分，当初始化过程消耗大量资源并且在使用对象时并不总是需要数据时，这个非常有用。

~~~
val nameB: String by lazy {
    println("getLazy")
    "123"
}

println(nameB)
println(nameB)
~~~
注意：

- by lazy只能作用于val关键字标注的属性。
- 当属性用到的时候才会初始化”lazy{}”里面的内容
- 而且再次调用属性的时候，只会得到结果，而不会再次执行lazy{}的运行过程

# 四、类

## 类定义
~~~
class Runoob {  // 类名为 Runoob
    var name:String = ""//类属性
    // 大括号内是类体构成（可以没有即可以把大括号去掉）
    
}
~~~
声明：
无new关键字

val site = Runoob()
site.name//属性的引用

## 构造器

可有一个主构造器（类头一部分）和多个次构造器，
~~~
若主构造器没有任何注解，也没有任何可见度修饰符，那么constructor关键字可以省略。
class Person constructor(firstName: String) {}
~~~
## getter 和 setter

getter 和 setter 都是可选

如果属性类型可以从初始化语句或者类的成员函数中推断出来，
那就可以省去类型，val不允许设置setter函数，因为它是只读的。
~~~
var allByDefault: Int? // 错误: 需要一个初始化语句, 默认实现了 getter 和 setter 方法
var initialized = 1    // 类型为 Int, 默认实现了 getter 和 setter
val simple: Int?       // 类型为 Int ，默认实现 getter ，但必须在构造函数中初始化
val inferredType = 1   // 类型为 Int 类型,默认实现 getter

包含两个可变变量 lastName 和 no，lastName 修改了 getter 方法，no 修改了 setter 方法。
class Person {

    var lastName: String = "zhang"
        get() = field.toUpperCase()   // 将变量赋值后转换为大写
        set

    var no: Int = 100
        get() = field                // 后端变量
        set(value) {
            if (value < 10) {       // 如果传入的值小于 10 返回该值
                field = value
            } else {
                field = -1         // 如果传入的值大于等于 10 返回 -1
            }
        }

    var heiht: Float = 145.4f
        private set
}

// 测试
fun main(args: Array<String>) {
    var person: Person = Person()

    person.lastName = "wang"

    println("lastName:${person.lastName}")

    person.no = 9
    println("no:${person.no}")

    person.no = 20
    println("no:${person.no}")

}
输出
lastName:WANG
no:9
no:-1
~~~

## 延迟初始化
非空属性必须在定义的时候初始化,kotlin提供了一种可以延迟初始化的方案,
使用 lateinit 关键字描述属性：

~~~
public class MyTest {
    lateinit var subject: TestSubject

    @SetUp fun setup() {
        subject = TestSubject()
    }

    @Test fun test() {
        subject.method()  // dereference directly
    }
}
~~~
## 主构造器

~~~
主构造器中不能包含任何代码，初始化代码放在初始化代码块中init关键字
class Person constructor(firstName: String) {
    init {
        主构造器中的参数可以在初始化代码块中使用
        println("FirstName is $firstName")
    }
}
~~~
## 次构造器

~~~
class Person { 
    constructor(parent: Person) {
        parent.children.add(this) 
    }
    如果主构造器和次构造器都有那么用代理
 class Person(val name: String) {
    constructor (name: String, age:Int) : this(name) {
        // 初始化...
    }
}   
}
~~~
## 抽象类

抽象成员在类中不存在具体的 实现
无须对抽象类或抽象成员标注open注解

~~~
open class Base {
    open fun f() {}
}

abstract class Derived : Base() {
    override abstract fun f()
}
~~~

## 嵌套类

~~~
class Outer {                  // 外部类
    private val bar: Int = 1
    class Nested {             // 嵌套类
        fun foo() = 2
    }
}

fun main(args: Array<String>) {
    val demo = Outer.Nested().foo() // 调用格式：外部类.嵌套类.嵌套类方法/属性
    println(demo)    // == 2
}
~~~
## 内部类
内部类会带有一个对外部类的对象的引用，所以内部类可以访问外部类成员属性和成员函数。

~~~
class Outer {
    private val bar: Int = 1
    var v = "成员属性"
    /**嵌套内部类**/
    inner class Inner {
        fun foo() = bar  // 访问外部类成员
        fun innerTest() {
            var o = this@Outer //获取外部类的成员变量
            println("内部类可以引用外部类的成员，例如：" + o.v)
        }
    }
}

fun main(args: Array<String>) {
    val demo = Outer().Inner().foo()
    println(demo) //   1
    val demo2 = Outer().Inner().innerTest()   
    println(demo2)   // 内部类可以引用外部类的成员，例如：成员属性
}
为了消除歧义，要访问来自外部作用域的 this，我们使用this@label，其中 @label 是一个 代指 this 来源的标签。
~~~
## 匿名内部类

~~~
class Test {
    var v = "成员属性"

    fun setInterFace(test: TestInterFace) {
        test.test()
    }
}

/**
 * 定义接口
 */
interface TestInterFace {
    fun test()
}

fun main(args: Array<String>) {
    var test = Test()

    /**
     * 采用对象表达式来创建接口对象，即匿名内部类的实例。
     */
    test.setInterFace(object : TestInterFace {
        override fun test() {
            println("对象表达式创建匿名内部类的实例")
        }
    })
}
~~~

## 类的修饰符
- abstract    // 抽象类  
- final       // 类不可继承，默认属性
- enum        // 枚举类
- open        // 类可继承，类默认是final的
- annotation  // 注解类
- private    // 仅在同一个文件中可见
- protected  // 同一个文件中或子类可见
- public     // 所有调用的地方都可见
- internal   // 同一个模块中可见

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

# 继承

Kt中所有类都继承Any类，是所有类的超类，对于没有超类的类是默认超类
~~~
class Example // 从 Any 隐式继承

~~~
Any默认实现

- equals()
- hashCode()
- toString()

注意：Any 不是 java.lang.Object。
如果一个类要被继承，可以使用 open 关键字进行修饰。

~~~
open class Base(p: Int)           // 定义基类

class Derived(p: Int) : Base(p)
~~~
## 1、构造函数

### (1)、子类有主构造函数

基类必须在主构造函数中立即初始化。
~~~
open class Person(var name : String, var age : Int){// 基类

}

class Student(name : String, age : Int, var no : String, var score : Int) : Person(name, age) {

}

// 测试
fun main(args: Array<String>) {
    val s =  Student("Runoob", 18, "S12346", 89)
    println("学生名： ${s.name}")
    println("年龄： ${s.age}")
    println("学生号： ${s.no}")
    println("成绩： ${s.score}")
}
~~~

### (2)子类没有主构造函数
必须在每一个二级构造函数中用 super 关键字初始化基类，或者在代理另一个构造函数。初始化基类时，
可以调用基类的不同构造方法。
~~~
class Student : Person {

    constructor(ctx: Context) : super(ctx) {
    } 

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx,attrs) {
    }
}
/**用户基类**/
open class Person(name:String){
    /**次级构造函数**/
    constructor(name:String,age:Int):this(name){
        //初始化
        println("-------基类次级构造函数---------")
    }
}

/**子类继承 Person 类**/
class Student:Person{

    /**次级构造函数**/
    constructor(name:String,age:Int,no:String,score:Int):super(name,age){
        println("-------继承类次级构造函数---------")
        println("学生名： ${name}")
        println("年龄： ${age}")
        println("学生号： ${no}")
        println("成绩： ${score}")
    }
}

fun main(args: Array<String>) {
    var s =  Student("Runoob", 18, "S12345", 89)
}
打印
-------基类次级构造函数---------
-------继承类次级构造函数---------
学生名： Runoob
年龄： 18
学生号： S12345
成绩： 89
~~~
## 2、重写

在基类中使用fun声明函数时此函数默认是final修饰，不能被子类重写，若要被子类重写那么添加open修饰，
重写方法使用override 

~~~
/**用户基类**/
open class Person{
    open fun study(){       // 允许子类重写
        println("我毕业了")
    }
}

/**子类继承 Person 类**/
class Student : Person() {

    override fun study(){    // 重写方法
        println("我在读大学")
    }
}

fun main(args: Array<String>) {
    val s =  Student()
    s.study();

}
输出结果为:
我在读大学
~~~
如果有多个相同的方法（继承或者实现自其他类，如A、B类），则必须要重写该方法，使用super范型去选择性地调用父类的实现。

~~~
open class A {
    open fun f () { print("A") }
    fun a() { print("a") }
}

interface B {
    fun f() { print("B") } //接口的成员变量默认是 open 的
    fun b() { print("b") }
}

class C() : A() , B{
    override fun f() {
        super<A>.f()//调用 A.f()
        super<B>.f()//调用 B.f()
    }
}

fun main(args: Array<String>) {
    val c =  C()
    c.f();

}
C 继承自 a() 或 b(), C 不仅可以从 A 或则 B 中继承函数，而且 C 可以继承 A()、B() 中共有的函数。此时该函数在中只有一个实现，为了消除歧义，该函数必须调用A()和B()中该函数的实现，并提供自己的实现。

输出结果为:
AB
~~~

### 属性重写
属性重写使用 override 关键字，属性必须具有兼容类型，每一个声明的属性都可以通过初始化程序或者getter方法被重写：

~~~
open class Foo {
    open val x: Int get { …… }
}

class Bar1 : Foo() {
    override val x: Int = ……
}
~~~
 你可以用一个var属性重写一个val属性，但是反过来不行。因为val属性本身定义了getter方法，重写为var属性会在衍生类中额外声明一个setter方法

你可以在主构造函数中使用 override 关键字作为属性声明的一部分:

~~~
interface Foo {
    val count: Int
}

class Bar1(override val count: Int) : Foo

class Bar2 : Foo {
    override var count: Int = 0
}
~~~

# 泛型

~~~
class Box<T>(t: T) {
    var value = t
}
创建实体类我们需要指定泛型参数
val box: Box<Int> = Box<Int>(1)
// 或者
val box = Box(1) // 编译器会进行类型推断，1 类型 Int，所以编译器知道我们说的是 Box<Int>。
~~~









