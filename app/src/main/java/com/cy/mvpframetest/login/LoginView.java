package com.cy.mvpframetest.login;

import com.cy.mvpframetest.bean.LoginBean;

public interface LoginView {
    void showLoading();
    void hideLoading();

    void LoginSuccess(LoginBean loginBean);
    void LoginFailed(String msg);

    void SignSuccess(LoginBean loginBean);
    void SignFailed(String msg);
}
