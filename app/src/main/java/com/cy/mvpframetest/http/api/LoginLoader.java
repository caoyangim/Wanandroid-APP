package com.cy.mvpframetest.http.api;

import com.cy.mvpframetest.bean.BaseResponse;
import com.cy.mvpframetest.bean.LoginBean;
import com.cy.mvpframetest.http.ObjectLoader;
import com.cy.mvpframetest.http.PayLoad;
import com.cy.mvpframetest.http.RetrofitServiceManager;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public class LoginLoader extends ObjectLoader {
    private LoginApi mLoginApi;
    public LoginLoader() {
        mLoginApi = RetrofitServiceManager.getInstance().create(LoginApi.class);
    }

    public Observable<LoginBean> Login(String username,String password){
        return observe(mLoginApi.login(username, password)).map(new PayLoad<LoginBean>());
    }
}
interface LoginApi{
    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseResponse<LoginBean>> login(@Field("username")String username, @Field("password") String password);
}
