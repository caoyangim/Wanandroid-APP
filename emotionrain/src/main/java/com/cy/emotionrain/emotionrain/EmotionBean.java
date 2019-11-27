package com.cy.emotionrain.emotionrain;

import android.graphics.Bitmap;

/**
 * Created by lenovo on 2019/6/11
 * 功能描述：
 */
public class EmotionBean {
    /**
     * 需要绘制的bitmap
     */
    public Bitmap bitmap;
    /**
     * 坐标
     */
    public int x,y;
    /**
     * X.Y轴的速率
     */
    public int velocityX,velocityY;

    /**
     * 图标出现的时间
     */
    public int appearTimeStamp;

}
