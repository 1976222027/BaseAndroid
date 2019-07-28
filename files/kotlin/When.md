## When表达式

### 一、基本使用

~~~
override fun onClick(v: View?) {
        /**
         * when 表达式
         * 多个满足同一条件用，隔开
         * R.id.btn_kt_canlong,R.id.btn_kt_san->
         * sum(1,2)
         */
        when (v?.id) {
            R.id.btn_kt_sum ->
                // ...
            R.id.btn_kt_canlong->
                // ...
            else ->
                //...
~~~

### 二、表达式使用

~~~
 val x = 5
  when(x){
       is Int->
          //
       in 1..100->
         //是否在1到100
       !in 1..100->
  }
        
var b = when(x){
            in 1..100->6
            else->8
        }
~~~






