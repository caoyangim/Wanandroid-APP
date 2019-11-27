package com.cy.mvpframetest.knowledge.knowledge;

import com.cy.mvpframetest.base.MvpListener;
import com.cy.mvpframetest.bean.KnowledgeBean;

import java.util.List;

/**
 * Created by lenovo on 2019/8/6
 * 功能描述：
 */
public class KnowledgePresentImpl extends KnowledgeContract.KnowledgePresent {
    private KnowledgeContract.KnowledgeModel mModel;

    @Override
    protected void loadKnowledgeData() {
        final KnowledgeContract.KnowledgeView mView = getView();
        mModel = new KnowledgeModleImpl();
        if (mView == null) {
            return;
        }
        mView.showLoading();
        mModel.loadKnowledgeData(new MvpListener<List<KnowledgeBean>>() {
            @Override
            public void onSuccess(List<KnowledgeBean> result) {
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
