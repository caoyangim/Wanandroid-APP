package com.cy.mvpframetest.base;

import java.lang.ref.WeakReference;

/**
 * Created by lenovo on 2019/5/25
 * 功能描述：
 */
public abstract class BasePresenter<M,V> {
    private M mModle;
    private WeakReference<V> mViewRef;

    public void onAttach(M modle, V view) {
        mModle = modle;
        mViewRef = new WeakReference<>(view);
    }

    protected V getView(){
        return isViewAttached() ? mViewRef.get() : null;
    }

    protected Boolean isViewAttached(){
        return mViewRef != null && mViewRef.get() != null;
    }

    protected void onDetach(){
        if (mViewRef != null){
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
