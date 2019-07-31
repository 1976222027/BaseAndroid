## 循环语句

循环语句主要有 for while 

### 一、基本使用

~~~
var mArray = arrayListOf<String>()
        mArray.add("李偲")
        mArray.add("王五")
        mArray.add("张三")
        for (aee in mArray){
            if (aee == "王五") continue // 当等于王五时跳过这个继续循环下一次
            if (aee == "张三") break // 当等于张三直接终止此次循环
            // 先条件判断
            while (aee is String){
                // doSomeThing
            }
            // 先执行一条再根据while判断是否执行下一次
            do {
                // doSomething
            }while (aee is String)
        }
~~~






