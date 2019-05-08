package com.daqsoft.customview.filter;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * 筛选条目
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-8.17:21
 * @since JDK 1.8
 */

public class DropDownMenu extends RelativeLayout{
    public DropDownMenu(Context context) {
        this(context,null);
    }

    public DropDownMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public DropDownMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化
     */
    private void init() {
        setBackgroundColor(Color.WHITE);
    }
}
