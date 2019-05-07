package com.daqsoft.customview.bubble;

import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 气泡动画
 * 可用于引导页
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-7.18:14
 * @since JDK 1.8
 */

public class BubbleView extends View{
    // 组合动画
    private AnimatorSet animatorSet;
    private Paint mPaint;//画笔
    /**
     * 重写构造方法
     * @param context
     */
    public BubbleView(Context context) {
        super(context);
        init();
    }

    public BubbleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        animatorSet = new AnimatorSet();
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint= new Paint();
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAlpha(60);
        mPaint.setAntiAlias(true);
    }

}
