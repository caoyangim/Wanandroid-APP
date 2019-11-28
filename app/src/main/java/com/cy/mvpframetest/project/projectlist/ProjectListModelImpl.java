package com.cy.mvpframetest.project.projectlist;

import com.cy.mvpframetest.base.MvpListener;
import com.cy.mvpframetest.bean.ArticleDetailData;
import com.cy.mvpframetest.bean.ProListBean;
import com.cy.mvpframetest.http.api.ProjectLoader;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

public class ProjectListModelImpl implements provectlistContract.ProjectListModel {
    ProjectLoader mLoader = new ProjectLoader();
    @Override
    public void loadProList(int id, int page, final MvpListener<ProListBean> listener) {
        Observable<ProListBean> observable = mLoader.getProListData(id,page);
        observable.subscribe(new Action1<ProListBean>() {
            @Override
            public void call(ProListBean proListBeans) {
                listener.onSuccess(proListBeans);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                listener.onError(throwable.toString());
            }
        });
    }
}
