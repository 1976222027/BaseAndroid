MVVM全解

#优点
- 降低布局和逻辑的耦合性，使代码逻辑更加清晰
-  减少findViewById() 
-  防止内存泄漏
-  空监测

# 配置

~~~
在对应modelbuild.gradle加入
android {
    dataBinding {
        enabled = true
    }
}
~~~
## 布局
选中根布局alt+回车 选 Conver to data binding layout 生成对应的布局规则，详见DataBindingActivity


# 单向数据绑定
BaseObservable 提供了 notifyChange() 和 notifyPropertyChanged() 两个方法，前者会刷新所有的值域，后者则只更新对应 BR 的 flag，该 BR 的生成通过注释 @Bindable 生成，可以通过 BR notify 特定属性关联的视图



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









