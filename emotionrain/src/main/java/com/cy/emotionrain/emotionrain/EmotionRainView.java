package com.cy.emotionrain.emotionrain;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.cy.emotionrain.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lenovo on 2019/6/11
 * 功能描述：
 */
public class EmotionRainView extends View {

    private boolean isRaining = false;
    private float mEmotionHeight,mEmotionWidth;
    private Context mContext;
    private Random random;
    private List<EmotionBean> mEmotionBeanList = new ArrayList<>();
    private Matrix matrix;
    private Paint mPaint;
    private long mStartTimeStamp;

    public EmotionRainView(Context context,AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        random = new Random();
        matrix = new Matrix();
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
    }

    public void startRain(final List<Bitmap> list){

        stopRain();

        setVisibility(VISIBLE);
        initData(list);
        isRaining = true;
        invalidate();
    }

    public void stopRain(){
        setVisibility(GONE);
        isRaining = false;
    }

    /**
     * 初始化数据bean
     * @param list
     */
    private void initData(final List<Bitmap> list) {
        mEmotionHeight = 50;
        mEmotionWidth = 50;

        mEmotionBeanList.clear();
        mStartTimeStamp = System.currentTimeMillis();

        int maxDuration = 2000;
        int currentDuration = 0;
        int i = 0;
        int size = list.size();
        while (currentDuration < maxDuration){
            EmotionBean bean = new EmotionBean();
            bean.bitmap = list.get(i % size);
            bean.x = random.nextInt(getWidth());
            bean.y = (int) - Math.ceil(mEmotionHeight);

            float duration = maxDuration + random.nextInt(500);
            bean.velocityY = (int) (getHeight()*16/duration);
            bean.velocityX = Math.round(random.nextFloat());
            bean.appearTimeStamp = currentDuration;

            currentDuration += random.nextInt(250);
            mEmotionBeanList.add(bean);
            i++;
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(!isRaining || mEmotionBeanList.size() == 0){
            return;
        }
        long totalTimeStamp = System.currentTimeMillis();
        for (int i=0;i<mEmotionBeanList.size();i++){
            EmotionBean bean = mEmotionBeanList.get(i);
            Bitmap bitmap = mEmotionBeanList.get(i).bitmap;
            if (bean.bitmap.isRecycled() || bean.y>getHeight() || totalTimeStamp - mStartTimeStamp < bean.appearTimeStamp){
                continue;
            }

            matrix.reset();
            float heightScale = mEmotionHeight/bitmap.getHeight();
            float widthScale = mEmotionWidth/bitmap.getWidth();
            matrix.setScale(widthScale,heightScale);

            bean.x = bean.x + bean.velocityX;
            bean.y = bean.y + bean.velocityY;

            matrix.postTranslate(bean.x,bean.y);
            canvas.drawBitmap(bitmap,matrix,mPaint);
        }

        postInvalidate();
    }
}
