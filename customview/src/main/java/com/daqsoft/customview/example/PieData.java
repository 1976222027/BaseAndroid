package com.daqsoft.customview.example;

/**
 * 饼状图的数据
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-9.16:31
 * @since JDK 1.8
 */

public class PieData {
    private String name;
    private float value;
    private float percentage;

    private int color = 0;
    private float angle = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
