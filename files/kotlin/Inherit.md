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
open class Person(var name : String){// 基类

}
class Student(name : String, age : Int, var no : String) : Person(name) {

}

~~~

### (2)子类没有主构造函数
必须在每一个二级构造函数中用 super 关键字初始化基类，或者在代理另一个构造函数。初始化基类时，
可以调用基类的不同构造方法。
~~~

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
      
        
    }
}
~~~







