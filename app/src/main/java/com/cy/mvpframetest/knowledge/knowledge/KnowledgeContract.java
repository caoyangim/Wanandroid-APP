package com.cy.mvpframetest.knowledge.knowledge;

import com.cy.mvpframetest.base.BaseModel;
import com.cy.mvpframetest.base.BasePresenter;
import com.cy.mvpframetest.base.BaseView;
import com.cy.mvpframetest.base.MvpListener;
import com.cy.mvpframetest.bean.KnowledgeBean;

import java.util.List;

/**
 * Created by lenovo on 2019/8/6
 * 功能描述：
 */
public interface KnowledgeContract {
    interface KnowledgeModel extends BaseModel{
        void loadKnowledgeData(MvpListener<List<KnowledgeBean>> listener);
    }
    interface KnowledgeView extends BaseView{
        void setData(List<KnowledgeBean> beanList);
    }
    abstract class KnowledgePresent extends BasePresenter<KnowledgeModel,KnowledgeView>{
        protected abstract void loadKnowledgeData();
    }
}
