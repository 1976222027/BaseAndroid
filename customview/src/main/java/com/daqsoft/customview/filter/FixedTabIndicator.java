package com.daqsoft.customview.filter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.yanb.daqsoft.baselib.utils.SizeUtils;

import java.util.List;

/**
 * 刷选条目的字顶部
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-8.19:03
 * @since JDK 1.8
 */

public class FixedTabIndicator extends LinearLayout{
    /**
     * 分割线画笔
     */
    private Paint mDividerPaint;
    private int mDividerColor = 0xFFdddddd;// 分割线颜色
    private int mDividerPaddingTop = 13;// 分割线距离上下的距离
    private int mDrawableRight = 10;
    /**
     * 上线两条线
     */
    private Paint mLinePaint;
    private float mLineHeight = 1;
    private int mLineColor = 0xFFeeeeee;

    private Context mContext;

    private int measureHeight;
    private int measureWidth;

    private int mTabNum;// 顶部条目数量

    public FixedTabIndicator(Context context) {
        this(context,null);
    }

    public FixedTabIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FixedTabIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     */
    private void init(Context context) {
        this.mContext = context;
        setOrientation(LinearLayout.HORIZONTAL);
        setBackgroundColor(Color.WHITE);
        // 想要重写onDraw，就要调用setWillNotDraw（false）
        setWillNotDraw(false);
        initPaint();
    }

    /**
     * 设置画笔
     */
    private void initPaint() {
        mDividerPaint =new Paint();
        mDividerPaint.setAntiAlias(true);//房据此
        mDividerPaint.setColor(mDividerColor);

        mLinePaint = new Paint();
        mLinePaint.setColor(mLineColor);
        mDividerPaddingTop = SizeUtils.px2dp(mDividerPaddingTop);
        mDrawableRight = SizeUtils.px2dp(mDrawableRight);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureHeight = getMeasuredHeight();
        measureWidth = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 分割线的个数比tab少1
        for (int i = 0; i < mTabNum - 1; i++) {
            final View child = getChildAt(i);
            if (null == child|| child.getVisibility() == View.GONE){
                continue;
            }
            // 划分割线
            canvas.drawLine(child.getRight(),mDividerPaddingTop,getRight(),measureHeight-mDividerPaddingTop,mDividerPaint);
        }
        // 绘制上下黑线
    }

    /**
     * 设置标题
     */
    public void setTitles(List<String> list){
        if (list==null || list.isEmpty()){
            throw new IllegalStateException("tab is null");
        }
        this.removeAllViews();//先删除所有View
        mTabNum = list.size();
        for (int i = 0; i < mTabNum; i++) {

        }
        postInvalidate();//刷新
    }

}
