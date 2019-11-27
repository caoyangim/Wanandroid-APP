package com.cy.mvpframetest.http.api;

import com.cy.mvpframetest.bean.ArticleList;
import com.cy.mvpframetest.bean.BaseResponse;
import com.cy.mvpframetest.bean.KnowledgeBean;
import com.cy.mvpframetest.http.ObjectLoader;
import com.cy.mvpframetest.http.PayLoad;
import com.cy.mvpframetest.http.RetrofitServiceManager;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lenovo on 2019/8/6
 * 功能描述：
 */
public class KnowledgeLoader extends ObjectLoader {
    private KnowledgeApi mKnowledgeApi;

    public KnowledgeLoader() {
        mKnowledgeApi = RetrofitServiceManager.getInstance().create(KnowledgeApi.class);
    }

    public Observable<List<KnowledgeBean>> getKnowledgeBean(){
        return observe(mKnowledgeApi.getKnowledgeData()).map(new PayLoad<List<KnowledgeBean>>());
    }

    public Observable<ArticleList> getKnowledgeListBean(int id,int cid){
        return observe(mKnowledgeApi.getKnowledgeList(id,cid)).map(new PayLoad<ArticleList>());
    }
}
interface KnowledgeApi{
    /**
     * 获取知识体系数据
     */
    @GET("tree/json")
    Observable<BaseResponse<List<KnowledgeBean>>> getKnowledgeData();
    /**
     * 获取知识体系下的列表文章
     * id:页码
     * cid:分类的id
     */
    @GET("article/list/{id}/json")
    Observable<BaseResponse<ArticleList>> getKnowledgeList(@Path("id") int treeId, @Query("cid") int cid);
}
