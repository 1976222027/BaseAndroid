package com.daqsoft.customview.login;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 常用登陆自定义Veiw
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-8.10:35
 * @since JDK 1.8
 */

public class LoginView extends LinearLayout{

    public LoginView(Context context) {
        super(context);
    }

    public LoginView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoginView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
