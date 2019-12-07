package com.cy.mvpframetest.http.api;

import com.cy.mvpframetest.bean.ArticleList;
import com.cy.mvpframetest.bean.BaseResponse;
import com.cy.mvpframetest.http.ObjectLoader;
import com.cy.mvpframetest.http.PayLoad;
import com.cy.mvpframetest.http.RetrofitServiceManager;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public class CollectLoader extends ObjectLoader {
    private CollectApi mCollectApi;

    public CollectLoader() {
        mCollectApi = RetrofitServiceManager.getInstance().create(CollectApi.class);
    }

    public Observable<ArticleList> getArticlesData(int page){
        return observe(mCollectApi.getCollectData(page)).map(new PayLoad<ArticleList>());
    }
}
interface CollectApi{
    /**
     * 获取收藏列表
     * https://www.wanandroid.com/lg/collect/list/0/json
     */
    @GET("lg/collect/list/{pageNo}/json")
    Observable<BaseResponse<ArticleList>> getCollectData(@Path("pageNo") int num);
}
