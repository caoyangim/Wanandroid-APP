package com.cy.mvpframetest.knowledge.knowledge;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cy.mvpframetest.R;
import com.cy.mvpframetest.base.BaseFragment;
import com.cy.mvpframetest.bean.KnowledgeBean;
import com.cy.mvpframetest.knowledge.adapter.KnowledgeRcAdapter;
import com.cy.mvpframetest.knowledge.knowledgeList.KnowledgeListActivity;

import java.util.List;

import butterknife.BindView;

import static com.cy.mvpframetest.knowledge.knowledgeList.KnowledgeListActivity.ID_INTENT;
import static com.cy.mvpframetest.knowledge.knowledgeList.KnowledgeListActivity.TIT_INTENT;

/**
 * Created by lenovo on 2019/8/6
 * 功能描述：
 */
public class KnowledgeFragement extends BaseFragment<KnowledgePresentImpl,KnowledgeModleImpl> implements KnowledgeContract.KnowledgeView {

    @BindView(R.id.knowledge_rc)
    RecyclerView mRecyclerView;

    private KnowledgeRcAdapter mAdapter;

    public static KnowledgeFragement newInstance() {
        return new KnowledgeFragement();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_knowledgen;
    }

    @Override
    protected void initSubViews(View view) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new KnowledgeRcAdapter(this.getContext());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mPresenter.loadKnowledgeData();
    }

    @Override
    public void setData(List<KnowledgeBean> beanList) {
        mAdapter.setDatas(beanList);
        mAdapter.setOnItemClickListener(new KnowledgeRcAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(KnowledgeBean bean, int position) {
                Intent intent = new Intent(getContext(), KnowledgeListActivity.class);
                intent.putExtra(ID_INTENT,bean.getChildren().get(position).getId());
                intent.putExtra(TIT_INTENT,bean.getChildren().get(position).getName());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.translate_in, R.anim.translate_out);
            }
        });
        mAdapter.notifyDataSetChanged();
    }
}
