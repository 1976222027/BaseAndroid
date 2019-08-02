DataBinding全解

# 优点

- 降低布局和逻辑的耦合性，使代码逻辑更加清晰
-  减少findViewById() 
-  防止内存泄漏
-  空监测

# 一、环境配置

## 添加依赖
~~~
在对应modelbuild.gradle加入
android {
    dataBinding {
        enabled = true
    }
}
~~~
# 二、修改布局

选中根布局alt+回车 选 Conver to data binding layout 生成对应的布局规则

# 三、引用类型关联控件及设置别名
- 如果xml中引入的类名一样但是路径不一样我们可通过别名来

~~~
    <data>
        <import type="com.leavesc.databinding_demo.model.User" />
        // 给引用加上别名区别
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
    
   <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_margin="20dp"
        android:orientation="vertical"
        tools:context="com.leavesc.databinding_demo.Main2Activity">

        <TextView
            android:id="@+id/tv_userName"
            ···
            android:text="@{userInfo.name,default=默认值}" />
            // 默认值用于开发看效果，默认值，不能包含引号
   </LinearLayout>
    
~~~
# 四、变量赋值

~~~
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMain2Binding activityMain2Binding = DataBindingUtil.setContentView(this, R.layout.activity_main2);
        user = new User("leavesC", "123456");
        activityMain2Binding.setUserInfo(user);
    }
~~~


# 五、布局语法
~~~
android:visibility="@{user.isAdult ? View.VISIBLE : View.GONE}"//支持三元运算符。需要导入View包
android:text="@{String.valueOf(user.age)}"//@{只能是String}
android:text="@{StringUtils.capitalize(user.firstName)}"//前面导入了这个包，可以调用这个静态方法
android:text="@{user.displayName ?? user.lastName}"//它表达的是如果左边不是 null 的，那么使用左边的值，否者使用右边的值
~~~
# 六、修改绑定名

布局绑定生成的文件名以布局文件名来的驼峰并去掉布局文件下划线
可自定义

~~~
    <data class="CustomBinding">
    </data>
~~~


## 七、设置监听

我们可以通过监听，监听属性的变化

~~~
mBooks?.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable, propertyId: Int) {
                    if (propertyId == BR.name) {
                        Logger.e("BR.name")
                    } else if (propertyId == BR.details) {
                        Log.e(TAG, "BR.details")
                    } else if (propertyId == BR._all) {
                        Log.e(TAG, "BR._all")
                    } else {
                        Log.e(TAG, "未知")
                    }
                }
            })
~~~
# 八、单向数据绑定BaseObservable


# 四、双向数据绑定

实现数据变化自动驱动UI刷新方式

- BaseObservable
- ObservableField
- ObservableCollection

BaseObservable 提供了 notifyChange() 和 notifyPropertyChanged() 两个方法，
前者会刷新所有的值域，后者则只更新对应 BR 的 flag，该 BR 的生成通过注释 @Bindable 生成，
可以通过 BR notify 特定属性关联的视图

## 1、通过ObservableField实现
继承于 Observable 类相对来说限制有点高，且也需要进行 notify 操作，因此为了简单起见可以选择使用 
ObservableField（官方对 BaseObservable 中字段的注解和刷新等操作的封装）

常见封装库

| 方法 | 描述 | -- |--|
| ------------- |:-------------| :-----|:-----|
| ObservableBoolean | |  | |
| ObservableByte | |  | |
| ObservableChar | |  | |
| ObservableShort | |  | |
| ObservableInt | |  | |
| ObservableLong | | | |
| ObservableFloat | |  | |
| ObservableDouble | |  | |
| ObservableParcelable | |  | |

**也可通过 ObservableField 泛型来申明其他类型**

~~~
public class ObservableGoods {

    private ObservableField<String> name;

    private ObservableFloat price;

    private ObservableField<String> details;

    public ObservableGoods(String name, float price, String details) {
        this.name = new ObservableField<>(name);
        this.price = new ObservableFloat(price);
        this.details = new ObservableField<>(details);
    }

    ```省略get set 方法
}

布局text
<TextView
     android:layout_width="match_parent"
     android:layout_height="wrap_content"
     android:text="@{observableGoods.name}" />
点击按钮调用方法

public void changeGoodsName() {
      observableGoods.getName().set("code" + new Random().nextInt(100));
}
        
就这样就改变了
~~~




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










