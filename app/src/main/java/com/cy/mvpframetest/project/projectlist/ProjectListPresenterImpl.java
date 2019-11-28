package com.cy.mvpframetest.project.projectlist;

import com.cy.mvpframetest.base.MvpListener;
import com.cy.mvpframetest.bean.ProListBean;

public class ProjectListPresenterImpl extends provectlistContract.ProjectListPresenter {
    private provectlistContract.ProjectListModel mModel;
    @Override
    protected void loadProListdata(int id ,int page) {
        final provectlistContract.ProjectListView mView = getView();
        mModel = new ProjectListModelImpl();
        if (mView == null)
            return;
        mView.showLoading();
        mModel.loadProList(id, page, new MvpListener<ProListBean>() {
            @Override
            public void onSuccess(ProListBean result) {
                mView.setListData(result);
            }

            @Override
            public void onError(String errorMsg) {
                mView.showError(errorMsg);
            }
        });
    }

    @Override
    protected void loadMore(int id, int page) {
        final provectlistContract.ProjectListView mView = getView();
        mModel.loadProList(id, page, new MvpListener<ProListBean>() {
            @Override
            public void onSuccess(ProListBean result) {
                mView.loadMore(result);
            }

            @Override
            public void onError(String errorMsg) {
                mView.showError(errorMsg);
            }
        });
    }
}
