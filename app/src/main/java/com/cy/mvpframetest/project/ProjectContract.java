package com.cy.mvpframetest.project;

import com.cy.mvpframetest.base.BaseModel;
import com.cy.mvpframetest.base.BasePresenter;
import com.cy.mvpframetest.base.BaseView;
import com.cy.mvpframetest.base.MvpListener;
import com.cy.mvpframetest.bean.ProCategoryBean;

import java.util.List;

public interface ProjectContract {
    interface ProjectModel extends BaseModel{
        void loadProCate(MvpListener<List<ProCategoryBean>> listener);
    }

    interface ProjectView extends BaseView{
        void setProCateData(List<ProCategoryBean> beanList);
    }

    abstract class ProjectPresenter extends BasePresenter<ProjectModel,ProjectView>{
        protected abstract void loadProCateData();
    }
}
