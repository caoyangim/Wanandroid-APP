package com.cy.mvpframetest.login;

import com.cy.mvpframetest.base.MvpListener;
import com.cy.mvpframetest.bean.LoginBean;
import com.cy.mvpframetest.http.api.LoginLoader;

import rx.functions.Action1;

public class LoginModel {
    private LoginLoader mLoader = new LoginLoader();

    public void Login(String unm, String pwd, final MvpListener<LoginBean> listener){
        mLoader.Login(unm,pwd).subscribe(new Action1<LoginBean>() {
            @Override
            public void call(LoginBean loginBean) {
                listener.onSuccess(loginBean);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                listener.onError(throwable.getMessage());
            }
        });
    }

    public void Register(String unm,String pwd,String repwd,final MvpListener<LoginBean> listener){
        mLoader.Register(unm,pwd,repwd).subscribe(new Action1<LoginBean>() {
            @Override
            public void call(LoginBean loginBean) {
                listener.onSuccess(loginBean);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                listener.onError(throwable.getMessage());
            }
        });
    }
}
