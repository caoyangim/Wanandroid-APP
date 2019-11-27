package com.cy.mvpframetest.navigation;

import com.cy.mvpframetest.base.BaseModel;
import com.cy.mvpframetest.base.BasePresenter;
import com.cy.mvpframetest.base.BaseView;
import com.cy.mvpframetest.base.MvpListener;
import com.cy.mvpframetest.bean.NavCategoryBean;

import java.util.List;

public interface NavigationContract {
    interface NavigationModel extends BaseModel{
        void loadData(MvpListener<List<NavCategoryBean>> listener);
    }

    interface NavigationView extends BaseView{
        void setData(List<NavCategoryBean> beanList);
    }

    abstract class NavigationPresenter extends BasePresenter<NavigationModel,NavigationView>{
        protected abstract void loadData();
    }
}
