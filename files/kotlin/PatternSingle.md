## 单例模式

参考博客https://www.jianshu.com/p/5797b3d0ebd0
主要有5种

- 饿汉式
- 懒汉式
- 线程安全的懒汉式
- 双重校验锁式
- 静态内部类式

# 一、饿汉式

~~~
//Java实现
public class SingletonDemo {
    private static SingletonDemo instance=new SingletonDemo();
    private SingletonDemo(){

    }
    public static SingletonDemo getInstance(){
        return instance;
    }
}
//Kotlin实现
object SingletonDemo
~~~

# 二、懒汉式

~~~
//Java实现
public class SingletonDemo {
    private static SingletonDemo instance;
    private SingletonDemo(){}
    public static SingletonDemo getInstance(){
        if(instance==null){
            instance=new SingletonDemo();
        }
        return instance;
    }
}
//Kotlin实现
class SingletonDemo private constructor() {
    companion object {
        private var instance: SingletonDemo? = null
            get() {
                if (field == null) {
                    field = SingletonDemo()
                }
                return field
            }
        fun get(): SingletonDemo{
        //细心的小伙伴肯定发现了，这里不用getInstance作为为方法名，是因为在伴生对象声明时，内部已有getInstance方法，所以只能取其他名字
         return instance!!
        }
    }
}
~~~

# 三、线程安全的懒汉式

~~~
//Java实现
public class SingletonDemo {
    private static SingletonDemo instance;
    private SingletonDemo(){}
    public static synchronized SingletonDemo getInstance(){//使用同步锁
        if(instance==null){
            instance=new SingletonDemo();
        }
        return instance;
    }
}
//Kotlin实现
class SingletonDemo private constructor() {
    companion object {
        private var instance: SingletonDemo? = null
            get() {
                if (field == null) {
                    field = SingletonDemo()
                }
                return field
            }
        @Synchronized
        fun get(): SingletonDemo{
            return instance!!
        }
    }

}
大家都知道在使用懒汉式会出现线程安全的问题，需要使用使用同步锁，
在Kotlin中，如果你需要将方法声明为同步，需要添加@Synchronized注解。
~~~
# 四、双重校验锁式（Double Check)

~~~
//Java实现
public class SingletonDemo {
    private volatile static SingletonDemo instance;
    private SingletonDemo(){} 
    public static SingletonDemo getInstance(){
        if(instance==null){
            synchronized (SingletonDemo.class){
                if(instance==null){
                    instance=new SingletonDemo();
                }
            }
        }
        return instance;
    }
}
//kotlin实现无参数
class SingletonDemo private constructor() {
    companion object {
        val instance: SingletonDemo by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        SingletonDemo() }
    }
}
//kotlin实现有参数
class SingletonDemo private constructor(private val property: Int) {//这里可以根据实际需求发生改变
  
    companion object {
        @Volatile private var instance: SingletonDemo? = null
        fun getInstance(property: Int) =
                instance ?: synchronized(this) {
                    instance ?: SingletonDemo(property).also { instance = it }
                }
    }
}
~~~
# 五、静态内部类式

~~~
//Java实现
public class SingletonDemo {
    private static class SingletonHolder{
        private static SingletonDemo instance=new SingletonDemo();
    }
    private SingletonDemo(){
        System.out.println("Singleton has loaded");
    }
    public static SingletonDemo getInstance(){
        return SingletonHolder.instance;
    }
}
//kotlin实现
class SingletonDemo private constructor() {
    companion object {
        val instance = SingletonHolder.holder
    }

    private object SingletonHolder {
        val holder= SingletonDemo()
    }

}
~~~






