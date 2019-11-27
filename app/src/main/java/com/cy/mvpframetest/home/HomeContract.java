package com.cy.mvpframetest.home;

import com.cy.mvpframetest.base.BaseModel;
import com.cy.mvpframetest.base.BasePresenter;
import com.cy.mvpframetest.base.BaseView;
import com.cy.mvpframetest.base.MvpListener;
import com.cy.mvpframetest.bean.ArticleList;
import com.cy.mvpframetest.bean.BannerDataItem;

import java.util.List;

/**
 * Created by lenovo on 2019/5/25
 * 功能描述：
 */
public interface HomeContract {
    interface HomeModel extends BaseModel {
        void loadHome(int page,MvpListener<ArticleList> listener);
        void loadBannel(MvpListener<List<BannerDataItem>> listener);
    }

    interface HomeView extends BaseView {
        void setData(ArticleList beanList);
        void setHomeBannel(List<BannerDataItem> bannerDataItems);
    }

    abstract class HomePresenter extends BasePresenter<HomeModel, HomeView> {
        protected abstract void loadData(int page);
        protected abstract void loadHomeBannel();
    }
}