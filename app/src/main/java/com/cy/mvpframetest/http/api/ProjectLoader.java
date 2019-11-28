package com.cy.mvpframetest.http.api;

import com.cy.mvpframetest.bean.ArticleDetailData;
import com.cy.mvpframetest.bean.BaseResponse;
import com.cy.mvpframetest.bean.ProCategoryBean;
import com.cy.mvpframetest.bean.ProListBean;
import com.cy.mvpframetest.http.ObjectLoader;
import com.cy.mvpframetest.http.PayLoad;
import com.cy.mvpframetest.http.RetrofitServiceManager;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public class ProjectLoader extends ObjectLoader {
    private ProjectApi mApi;

    public ProjectLoader() {
        mApi = RetrofitServiceManager.getInstance().create(ProjectApi.class);
    }

    public Observable<List<ProCategoryBean>> getProCategoryData(){
        return observe(mApi.getProCategoryData()).map(new PayLoad<List<ProCategoryBean>>());
    }

    public Observable<ProListBean> getProListData(int id ,int page){
        return observe(mApi.getProListData(page,id)).map(new PayLoad<ProListBean>());
    }
}

interface ProjectApi{
    @GET("project/tree/json")
    Observable<BaseResponse<List<ProCategoryBean>>> getProCategoryData();

    /**
     * https://www.wanandroid.com/project/list/1/json?cid=294
     */
    @GET("project/list/{id}/json")
    Observable<BaseResponse<ProListBean>> getProListData(@Path("id") int treeId, @Query("cid") int cid);
}
