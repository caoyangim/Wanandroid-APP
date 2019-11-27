package com.cy.mvpframetest.navigation;

import com.cy.mvpframetest.base.MvpListener;
import com.cy.mvpframetest.bean.NavCategoryBean;

import java.util.List;

public class NavigationPresenterImpl extends NavigationContract.NavigationPresenter {
    private NavigationContract.NavigationModel mModel;
    @Override
    protected void loadData() {
        final NavigationContract.NavigationView mView = getView();
        mModel = new NavigationModelImpl();
        if (mView == null)
            return;
        mView.showLoading();
        mModel.loadData(new MvpListener<List<NavCategoryBean>>() {
            @Override
            public void onSuccess(List<NavCategoryBean> result) {
                mView.setData(result);
                mView.hideLoading();
            }

            @Override
            public void onError(String errorMsg) {
                mView.showError(errorMsg);
            }
        });
    }
}
