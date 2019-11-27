package com.cy.mvpframetest.home.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by lenovo on 2019/5/28
 * 功能描述：FloatingActionButton缓慢消失
 */
public class FabBehavior extends FloatingActionButton.Behavior {
    private boolean visible = true;//是否可见

    public FabBehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes,int type) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild,
                target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed,int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed,type);
        if (dyConsumed > 0 && visible) {
            //show
            visible = false;
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            child.animate().translationY(child.getHeight() + layoutParams.bottomMargin).setInterpolator(new AccelerateInterpolator(3));
        } else if (dyConsumed < 0) {
            //hide
            visible = true;
            child.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));
        }

    }

}

