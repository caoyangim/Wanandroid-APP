package com.cy.mvpframetest.base;

/**
 * Created by lenovo on 2019/5/25
 * 功能描述：
 */
public interface BaseView {
    void showLoading();
    void hideLoading();
    void showError(String msg);
}
