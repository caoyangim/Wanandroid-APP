package com.cy.mvpframetest.http.api;

import com.cy.mvpframetest.bean.ArticleList;
import com.cy.mvpframetest.bean.BannerDataItem;
import com.cy.mvpframetest.bean.BaseResponse;
import com.cy.mvpframetest.http.ObjectLoader;
import com.cy.mvpframetest.http.PayLoad;
import com.cy.mvpframetest.http.RetrofitServiceManager;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by lenovo on 2019/5/25
 * 功能描述：
 */
public class HomeLoader extends ObjectLoader {
    private HomeApi mHomeApi;
    public HomeLoader() {
        mHomeApi = RetrofitServiceManager.getInstance().create(HomeApi.class);
    }

    public Observable<ArticleList> getArticlesData(int page){
        return observe(mHomeApi.getArticlesData(page)).map(new PayLoad<ArticleList>());
    }

    public Observable<List<BannerDataItem>> getHomeBannerBean(){
        return observe(mHomeApi.getHomeBannerData()).map(new PayLoad<List<BannerDataItem>>());
    }

}
interface HomeApi {

    /***
     * 获取首页banner数据
     * @return
     */
    @GET("banner/json")
    Observable<BaseResponse<List<BannerDataItem>>> getHomeBannerData();

    /***
     * 获取首页文章列表数据
     * @return
     */
    @GET("article/list/{pageNo}/json")
    Observable<BaseResponse<ArticleList>> getArticlesData(@Path("pageNo") int num);

}
