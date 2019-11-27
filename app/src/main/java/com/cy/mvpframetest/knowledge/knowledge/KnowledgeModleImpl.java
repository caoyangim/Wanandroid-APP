package com.cy.mvpframetest.knowledge.knowledge;

import com.cy.mvpframetest.base.MvpListener;
import com.cy.mvpframetest.bean.KnowledgeBean;
import com.cy.mvpframetest.http.api.KnowledgeLoader;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by lenovo on 2019/8/6
 * 功能描述：
 */
public class KnowledgeModleImpl implements KnowledgeContract.KnowledgeModel {
    private KnowledgeLoader mKnowledgeLoader = new KnowledgeLoader();

    @Override
    public void loadKnowledgeData(final MvpListener<List<KnowledgeBean>> listener) {
        Observable<List<KnowledgeBean>> observable = mKnowledgeLoader.getKnowledgeBean();
        observable.subscribe(new Action1<List<KnowledgeBean>>() {
            @Override
            public void call(List<KnowledgeBean> knowledgeBeans) {
                listener.onSuccess(knowledgeBeans);
            }
        });
    }
}
