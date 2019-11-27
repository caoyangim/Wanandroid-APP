package com.cy.mvpframetest.http.api;

import com.cy.mvpframetest.bean.BaseResponse;
import com.cy.mvpframetest.bean.NavCategoryBean;
import com.cy.mvpframetest.http.PayLoad;
import com.cy.mvpframetest.http.ObjectLoader;
import com.cy.mvpframetest.http.RetrofitServiceManager;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public class NavigationLoader extends ObjectLoader{
    private NavigationApi mApi;

    public NavigationLoader() {
        mApi = RetrofitServiceManager.getInstance().create(NavigationApi.class);
    }

    public Observable<List<NavCategoryBean>> getNavCategoryData(){
        return observe(mApi.getNavCategoryData()).map(new PayLoad<List<NavCategoryBean>>());
    }
}
interface NavigationApi{
    /**
     * 获取导航数据
     * url:https://www.wanandroid.com/navi/json
     */
    @GET("navi/json")
    Observable<BaseResponse<List<NavCategoryBean>>> getNavCategoryData();

}
