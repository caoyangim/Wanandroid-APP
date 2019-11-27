package com.cy.mvpframetest.navigation;

import com.cy.mvpframetest.base.MvpListener;
import com.cy.mvpframetest.bean.NavCategoryBean;
import com.cy.mvpframetest.http.api.NavigationLoader;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

public class NavigationModelImpl implements NavigationContract.NavigationModel{
    NavigationLoader mLoader = new NavigationLoader();
    @Override
    public void loadData(final MvpListener<List<NavCategoryBean>> listener) {
        Observable<List<NavCategoryBean>> observable = mLoader.getNavCategoryData();
        observable.subscribe(new Action1<List<NavCategoryBean>>() {
            @Override
            public void call(List<NavCategoryBean> navCategoryBeans) {
                listener.onSuccess(navCategoryBeans);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                listener.onError(throwable.toString());
            }
        });
    }
}
