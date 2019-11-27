package com.cy.mvpframetest.home;

import com.cy.mvpframetest.base.MvpListener;
import com.cy.mvpframetest.bean.ArticleList;
import com.cy.mvpframetest.bean.BannerDataItem;
import com.cy.mvpframetest.http.api.HomeLoader;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by lenovo on 2019/5/25
 * 功能描述：
 */
public class HomeModelImpl implements HomeContract.HomeModel {
    HomeLoader homeLoader = new HomeLoader();
    @Override
    public void loadHome(int page, final MvpListener<ArticleList> listener) {
        Observable<ArticleList> observable = homeLoader.getArticlesData(page);
        observable.subscribe(new Action1<ArticleList>() {
            @Override
            public void call(ArticleList articleList) {
                listener.onSuccess(articleList);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                listener.onError(throwable.toString());
            }
        });
    }

    @Override
    public void loadBannel(final MvpListener<List<BannerDataItem>> listener) {
        Observable<List<BannerDataItem>> observable = homeLoader.getHomeBannerBean();
        observable.subscribe(new Action1<List<BannerDataItem>>() {
            @Override
            public void call(List<BannerDataItem> bannerDataItems) {
                listener.onSuccess(bannerDataItems);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                listener.onError(throwable.toString());
            }
        });
    }
}

