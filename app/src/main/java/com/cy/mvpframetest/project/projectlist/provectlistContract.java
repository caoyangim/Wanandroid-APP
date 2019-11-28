package com.cy.mvpframetest.project.projectlist;

import com.cy.mvpframetest.base.BaseModel;
import com.cy.mvpframetest.base.BasePresenter;
import com.cy.mvpframetest.base.BaseView;
import com.cy.mvpframetest.base.MvpListener;
import com.cy.mvpframetest.bean.ProListBean;

import java.util.List;

public interface provectlistContract {
    interface ProjectListModel extends BaseModel{
        void loadProList(int id,int page,MvpListener<ProListBean> listener);
    }

    interface ProjectListView extends BaseView{
        void setListData(ProListBean lists);
        void loadMore(ProListBean list);
    }

    abstract class ProjectListPresenter extends BasePresenter<ProjectListModel,ProjectListView>{
        protected abstract void loadProListdata(int id,int page);
        protected abstract void loadMore(int id,int page);
    }
}
