package com.cy.recgallery.viewpage;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;

/**
 * Created by lenovo on 2019/6/14
 * 功能描述：
 */
public class MyVpTransform implements ViewPager.PageTransformer {
    private float MINSCALE = 0.9f;
    private float elevation = 20;
    @Override
    public void transformPage(@NonNull View page, float position) {
        if (position<-1 || position>1){
            page.setScaleX(MINSCALE);
            page.setScaleY(MINSCALE);
        }else {
            if (position<0){
                page.setScaleX(MINSCALE + (1 + position) * (1 - MINSCALE));
                page.setScaleY(MINSCALE + (1 + position) * (1 - MINSCALE));
                ((CardView) page).setCardElevation((1 + position) * elevation);
            }else {
                page.setScaleX(MINSCALE + (1 - position) * (1 - MINSCALE));
                page.setScaleY(MINSCALE + (1 - position) * (1 - MINSCALE));
                ((CardView) page).setCardElevation((1 - position) * elevation);
            }
        }
    }
}
