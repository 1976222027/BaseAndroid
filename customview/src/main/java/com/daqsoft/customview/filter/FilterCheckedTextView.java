package com.daqsoft.customview.filter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.Checkable;

/**
 * 当选择条目文字的样式自定义
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-10.16:41
 * @since JDK 1.8
 */

public class FilterCheckedTextView extends AppCompatTextView implements Checkable{
    /**
     * 时候选中
     */
    private boolean mIsChecked;
    public FilterCheckedTextView(Context context) {
        this(context,null);
    }

    public FilterCheckedTextView(Context context, @Nullable AttributeSet attrs) {
       this(context,attrs,0);
    }

    public FilterCheckedTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setChecked(boolean checked) {
        if (checked!=mIsChecked){
            mIsChecked = checked;
            // 刷新
            refreshDrawableState();
        }
    }

    @Override
    public boolean isChecked() {
        return mIsChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mIsChecked);
    }


    private static final int[] CHECKED_STATE_SET = {
            android.R.attr.state_checked
    };

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }
}
