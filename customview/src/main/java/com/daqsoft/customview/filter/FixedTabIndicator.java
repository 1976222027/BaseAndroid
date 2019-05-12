package com.daqsoft.customview.filter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daqsoft.customview.R;
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
    /**
     * tab文字大小
     */
    private int mTabTextSize = 13;
    private int mTabDefaultColor = 0xFF666666;// 未选中默认颜色
    private int mTabSelectedColor = 0xFF008DF2;// 指针选中颜色

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
        mDividerPaddingTop = SizeUtils.dp(mContext,mDividerPaddingTop);
        mDrawableRight = SizeUtils.dp(mContext,mDrawableRight);
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
     * 将相应的布局添加进去
     */
    public void setTitles(List<String> list){
        if (list==null || list.isEmpty()){
            throw new IllegalStateException("tab is null");
        }
        this.removeAllViews();//先删除所有View
        mTabNum = list.size();
        for (int i = 0; i < mTabNum; i++) {
            addView(generateTextView(list.get(i),i));
        }
        postInvalidate();//刷新
    }

    /**
     * 生成文本视图
     */
    private View generateTextView(String title,int pos){
        TextView tv = new TextView(mContext);
        tv.setGravity(Gravity.CENTER);
        tv.setText(title);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,mTabTextSize);
        tv.setTextColor(mTabDefaultColor);
        tv.setSingleLine();
        tv.setEllipsize(TextUtils.TruncateAt.END);
        // 限制4个字符
        tv.setMaxEms(6);
        // 设置箭头
        Drawable dra = getResources().getDrawable(getResources().getDrawable(R.drawable
                .level_filter).level_filter);
        tv.setCompoundDrawablesWithIntrinsicBounds(null,null,dra,null);
        tv.setCompoundDrawablePadding(mDrawableRight);
        // 将textView添加到RelativeLayout
        RelativeLayout rel = new RelativeLayout(mContext);
        RelativeLayout.LayoutParams rlParams = new RelativeLayout.LayoutParams(ViewGroup
                .LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rlParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        rel.addView(tv,rlParams);
        rel.setId(pos);

        // 在讲relativeLayout 添加到LinearLayout
        LayoutParams lineParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        lineParams.weight = 1;
        lineParams.gravity = Gravity.CENTER;
        rel.setLayoutParams(lineParams);
        rel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return rel;
    }


}
