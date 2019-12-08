package com.cy.mvpframetest.login;

import com.cy.mvpframetest.base.BasePresenter;
import com.cy.mvpframetest.base.MvpListener;
import com.cy.mvpframetest.bean.LoginBean;

public class LoginPresent extends BasePresenter<LoginModel,LoginView> {
    private LoginModel mModel;

    public void Login(String unm,String pwd){
        mModel = new LoginModel();
        LoginView view = getView();
        if (view == null) return;
        mModel.Login(unm, pwd, new MvpListener<LoginBean>() {
            @Override
            public void onSuccess(LoginBean result) {
                view.LoginSuccess(result);
            }

            @Override
            public void onError(String errorMsg) {
                view.LoginFailed(errorMsg);
            }
        });
    }

    public void Register(String unm,String pwd,String repwd){
        mModel = new LoginModel();
        LoginView view = getView();
        if (view == null) return;
        mModel.Register(unm, pwd, repwd, new MvpListener<LoginBean>() {
            @Override
            public void onSuccess(LoginBean result) {
                view.SignSuccess(result);
            }

            @Override
            public void onError(String errorMsg) {
                view.SignFailed(errorMsg);
            }
        });
    }


}
