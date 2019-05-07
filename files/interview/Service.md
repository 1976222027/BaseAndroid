# Service生命周期？

启动两种：
## 1.startService()

startService()-->onCreate()-->onStartConmon()-->onDestroy()

注意：
- startService调用后多次调用 startService ,onCreate()方法只会被调用一次，onStartConmn会被调用多次，调用stopService时候，onDestroy()执行，销毁服务
- 当我们startService启动的时候，通过intent传值，在onStartConmon方法中获取值的时候先判断intent是否为null

## 2.bindService()

bindService-->onCreate-->onBind-->unBind-->onDestroy

好处：
便利activity中操作service,比如加入service中有几个方法，a,b ，
如果要在activity中调用，在需要在activity获取ServiceConnection对象，
通过ServiceConnection来获取service中内部类的类对象，
然后通过这个类对象就可以调用类中的方法，当然这个类需要继承Binder对象







