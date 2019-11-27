package com.cy.mvpframetest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cy.mvpframetest.utils.ReflectUtil;

/**
 * Created by lenovo on 2019/5/25
 * 功能描述：
 */
public abstract class BaseMvpActivity<T extends BasePresenter,M extends BaseModel> extends BaseActivity{
    protected T mPresenter;
    protected M mModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = ReflectUtil.getT(this, 0);
        mModel = ReflectUtil.getT(this, 1);
        mPresenter.onAttach(mModel, this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        loadData();
    }

    protected abstract void loadData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

}
