package com.cy.mvpframetest.project;

import com.cy.mvpframetest.base.MvpListener;
import com.cy.mvpframetest.bean.ProCategoryBean;
import com.cy.mvpframetest.http.api.ProjectLoader;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

public class ProjectModelImpl implements ProjectContract.ProjectModel {
    ProjectLoader mLoader = new ProjectLoader();
    @Override
    public void loadProCate(final MvpListener<List<ProCategoryBean>> listener) {
        Observable<List<ProCategoryBean>> observable = mLoader.getProCategoryData();
        observable.subscribe(new Action1<List<ProCategoryBean>>() {
            @Override
            public void call(List<ProCategoryBean> proCategoryBeans) {
                listener.onSuccess(proCategoryBeans);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                listener.onError(throwable.toString());
            }
        });
    }
}
