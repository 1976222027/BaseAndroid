package com.daqsoft.customview.example;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * 自定义View教程
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-9.16:29
 * @since JDK 1.8
 *
 * 自定义View流程
 * 1.构造函数，初始化画笔等
 * 2.onMeasure 测量View的大小
 * 3.onSizeChanged 确定View的大小
 * 4.onLayout 确定子view布局
 * 5.onDraw 绘制内容
 * 6.对外提供接口
 */

public class CustomviewExampleView extends View{
    // 颜色表(注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080};
    private float mStartAngle = 0;//初始角度
    private List<PieData> mData;//数据
    private int mWidth,mHeight;//宽高
    private Paint mPaint;//画笔

    /**
     * 构造方法
     * @param context
     */
    public CustomviewExampleView(Context context) {
        this(context,null);
    }

    public CustomviewExampleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomviewExampleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化
     */
    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null == canvas)
            return;
        float currentStartAngle = mStartAngle;//当前起始角度
        canvas.translate(mWidth/2,mHeight/2);//将画布坐标原点移动到屏幕中心位置
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);  // 饼状图半径
        RectF rectF = new RectF(-r,-r,r,r);
        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);
            mPaint.setColor(pie.getColor());
            canvas.drawArc(rectF,currentStartAngle,pie.getAngle(),true,mPaint);
            currentStartAngle+=pie.getAngle();
        }
    }

    /**
     * 设置起始角度
     * @param angle
     */
    public void setStartAngle(int angle){
       this.mStartAngle = angle;
       invalidate();//刷新
    }
    public void setData(List<PieData> mDatas){
        this.mData = mDatas;
        initData(mDatas);
        invalidate();
    }

    /**
     * 初始化数据
     * @param mDatas
     */
    private void initData(List<PieData> mDatas) {
        if (null==mDatas||mDatas.size()==0){
            return;// 数据有问题直接返回
        }
        float sumValue = 0;// 总数
        for (int i = 0; i < mDatas.size(); i++) {
            PieData pie = mDatas.get(i);
            sumValue +=pie.getValue();//计算数值和
            int j = i %mColors.length;//设置颜色
            pie.setColor(mColors[j]);
        }
        float sumAngle = 0;
        for (int i = 0; i < mDatas.size(); i++) {
            PieData pie = mDatas.get(i);
            float percentage = pie.getValue() / sumValue;// 百分比
            float angle = percentage * 360;
            pie.setPercentage(percentage);//记录百分比
            pie.setAngle(angle);
            sumAngle+=angle;
        }
    }
}
