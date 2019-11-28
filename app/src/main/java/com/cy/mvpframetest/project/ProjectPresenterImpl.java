package com.cy.mvpframetest.project;

import com.cy.mvpframetest.base.MvpListener;
import com.cy.mvpframetest.bean.ProCategoryBean;

import java.util.List;

public class ProjectPresenterImpl extends ProjectContract.ProjectPresenter {
    private ProjectContract.ProjectModel mModel;
    @Override
    protected void loadProCateData() {
        final ProjectContract.ProjectView mView = getView();
        mModel = new ProjectModelImpl();
        if (mView == null)
            return;
        mView.showLoading();
        mModel.loadProCate(new MvpListener<List<ProCategoryBean>>() {
            @Override
            public void onSuccess(List<ProCategoryBean> result) {
                mView.setProCateData(result);
                mView.hideLoading();
            }

            @Override
            public void onError(String errorMsg) {
                mView.showError(errorMsg);
            }
        });
    }
}
