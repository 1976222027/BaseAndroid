DataBinding全解

#优点
- 降低布局和逻辑的耦合性，使代码逻辑更加清晰
-  减少findViewById() 
-  防止内存泄漏
-  空监测

# 一、环境配置

## 1、添加依赖
~~~
在对应modelbuild.gradle加入
android {
    dataBinding {
        enabled = true
    }
}
~~~
## 2、修改布局
选中根布局alt+回车 选 Conver to data binding layout 生成对应的布局规则，详见DataBindingActivity
注意：
- 如果xml中引入的类名一样但是路径不一样我们可通过别名来

~~~
    <data>
        <import type="com.leavesc.databinding_demo.model.User" />
        <import
            alias="TempUser"
            type="com.leavesc.databinding_demo.model2.User" />
        <variable
            name="userInfo"
            type="User" />
        <variable
            name="tempUserInfo"
            type="TempUser" />
    </data>
    这样我们就可以用如userInfo或tempUserInfo去设置数据了
~~~

# 二、一些常用的技巧
## 1、界面默认值

~~~
 android:text="@{userInfo.name,default=defaultValue}"
 defaultValue为默认值，不能包含引号
~~~

## 2、文件名

布局绑定生成的文件名以布局文件名来的驼峰并去掉布局文件下划线
可自定义

~~~
    <data class="CustomBinding">
    </data>
~~~


# 单向数据绑定

实现数据变化自动驱动UI刷新方式

- BaseObservable
- ObservableField
- ObservableCollection

BaseObservable 提供了 notifyChange() 和 notifyPropertyChanged() 两个方法，前者会刷新所有的值域，后者则只更新对应 BR 的 flag，该 BR 的生成通过注释 @Bindable 生成，可以通过 BR notify 特定属性关联的视图

| 方法 | 描述 | -- |--|
| ------------- |:-------------| :-----|:-----|
| notifyChange() | 空格| &nbsp； | &#160；|
|  notifyPropertyChanged() | 只更新对应 BR 的 flag，该 BR 的生成通过注释 @Bindable 生成，可以通过 BR notify 特定属性关联的视图| &nbsp； | &#160；|



# 常用的转义字符
| 显示结果 | 描述 | 转义字符 |十进制|
| ------------- |:-------------| :-----|:-----|
| | 空格| &nbsp； | &#160；|
| <| 小于号| &lt； | &#60；|
| >| 大于号| &gt； | &#62；|
| &| 与号| &amp； | &#38；|
| "| 引号| &quot； | &#34；|
| ‘| 撇号| &apos； | &#39；|
| ×| 乘号|&times； | &#215；|
| ÷| 除号|&divide； | &#247；|










