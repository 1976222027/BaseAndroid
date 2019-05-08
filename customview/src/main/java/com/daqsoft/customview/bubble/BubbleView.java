package com.daqsoft.customview.bubble;

import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.daqsoft.customview.R;

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
    private int colors[] = new  int[2];
    private float positions[] = new  float[2];
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
        init();
    }

    public BubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        animatorSet = new AnimatorSet();
        initPaint();
        initShader();
    }

    /**
     * 初始化圆渐变色的LinearGradient的colors和positions
     */
    private void initShader() {
        colors[0] = getResources().getColor(R.color.circle_start);
        colors[1] = getResources().getColor(R.color.circle_end);
        positions[0] = 0;
        positions[1] = 1;
    }

    /**
     * 初始化画笔
     *
     */
    private void initPaint() {
        mPaint= new Paint();
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAlpha(60);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (animatorSet.isStarted()){

        }
    }
}
