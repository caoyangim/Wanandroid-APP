package com.cy.mvpframetest.home;

import com.cy.mvpframetest.base.MvpListener;
import com.cy.mvpframetest.bean.ArticleList;
import com.cy.mvpframetest.bean.BannerDataItem;

import java.util.List;

/**
 * Created by lenovo on 2019/5/25
 * 功能描述：
 */
public class HomePresenterImpl extends HomeContract.HomePresenter {
    private HomeContract.HomeModel mHomeModel;

    @Override
    protected void loadData(int page) {
        final HomeContract.HomeView mView = getView();
        mHomeModel = new HomeModelImpl();
        if (mView == null) {
            return;
        }

        mView.showLoading();
        mHomeModel.loadHome(page, new MvpListener<ArticleList>() {
            @Override
            public void onSuccess(ArticleList result) {
                mView.setData(result);
                mView.hideLoading();
            }

            @Override
            public void onError(String errorMsg) {
                mView.showError(errorMsg);
            }
        });
    }

    @Override
    protected void loadHomeBannel() {
        final HomeContract.HomeView mView = getView();
        mHomeModel = new HomeModelImpl();
        if (mView == null) {
            return;
        }
        mView.showLoading();
        mHomeModel.loadBannel(new MvpListener<List<BannerDataItem>>() {
            @Override
            public void onSuccess(List<BannerDataItem> result) {
                mView.setHomeBannel(result);
                mView.hideLoading();
            }

            @Override
            public void onError(String errorMsg) {
                mView.showError(errorMsg);
            }
        });
    }
}
