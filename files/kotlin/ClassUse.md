## 类

类可以包含构造函数，初始化代码块、函数、属性、内部类、对象声明
关键字class

### 一、GET SET 方法

要在get或set方法中做某些操作

默认不用重写get和set方法，直接类名.属性就可以访问，如果要在get/set里做操作就要重写覆盖如下

~~~
class Book {
    var b = 0  // b 的属性类型可以推断故可以省Int
        get() {
            // 做某些操作这里
            return field// field就是值本身
        }
        set(value) {
            // 做某些操作这里
            field = value
        }
        
        // 注意val不能设置setter函数，他是只读的
}

var allByDefault: Int? // 错误: 需要一个初始化语句, 默认实现了 getter 和 setter 方法
var initialized = 1    // 类型为 Int, 默认实现了 getter 和 setter
val simple: Int?       // 类型为 Int ，默认实现 getter ，但必须在构造函数中初始化
val inferredType = 1   // 类型为 Int 类型,默认实现 getter
~~~

### 二、控制访问权限

~~~
class Book {
    // 都要加权限 
    protected var b = 0
        protected get
        protected set// 这里我们对setget添加了访问权限
}
~~~

### 三、成员变量

~~~
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

创建实例
val book = Book()// kt中没有new关键字
book.price//使用属性
~~~

### 三、构造器

#### 主构造函数

可有一个主构造器（类头一部分位于类名称之后）和多个次构造器，
~~~
若主构造器没有任何注解，也没有任何可见度修饰符，那么constructor关键字可以省略。
class Person constructor(firstName: String) {
    var siteName = firstName
    init{
        // 主构造器不能包含任何代码，初始化代码放在这初始化代码块中init关键字
    }
}
注意：主构造器的参数可以在初始化代码段中使用，也可以在类主体n定义的属性初始化代码中使用。 
一种简洁语法，可以通过主构造器来定义属性并初始化属性值（可以是var或val）：
class Person(val firstName: String, val lastName: String) {
    //...
}

// 这段代码和上面一个意思
~~~

#### 次构造函数

~~~
class Person { 
    // 次构造器关键字constructor
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

如果一个非抽象类没有声明构造函数(主构造函数或次构造函数)，它会产生一个没有参数的构造函数。构造函数是 public 。
如果你不想你的类有公共的构造函数，你就得声明一个空的主构造函数：
class DontCreateMe private constructor () {
}
注意：在 JVM 虚拟机中，如果主构造函数的所有参数都有默认值，编译器会生成一个附加的无参的构造函数，这个构造函数会直接使用默认值。这使得 Kotlin 
可以更简单的使用像 Jackson 或者 JPA 这样使用无参构造函数来创建类实例的库
class Customer(val customerName: String = "")
~~~









