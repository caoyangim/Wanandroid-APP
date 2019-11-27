package com.cy.mvpframetest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cy.mvpframetest.utils.ReflectUtil;

import butterknife.ButterKnife;


/**
 * Created by Spark on 2016/7/10.
 * Github: github/SparkYuan
 */
public abstract class BaseFragment<T extends BasePresenter,M extends BaseModel> extends Fragment implements BaseView {

    protected T mPresenter;
    protected M mModel;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        Log.e("Fragment","onViewCreated");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View containerView = inflater.inflate(getContentViewId(), container, false);
        ButterKnife.bind(this,containerView);
        mPresenter = ReflectUtil.getT(this, 0);
        mModel = ReflectUtil.getT(this, 1);
        initSubViews(containerView);
        mPresenter.onAttach(mModel, this);
        Log.e("Fragment","onCreateView");
        return containerView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e("Fragment","onCreate");
        super.onCreate(savedInstanceState);
    }

    protected abstract int getContentViewId();
    protected abstract void initSubViews(View view);
    public abstract void initData();


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetach();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }
}
