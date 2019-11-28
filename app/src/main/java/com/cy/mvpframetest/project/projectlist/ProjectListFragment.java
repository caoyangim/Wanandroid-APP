package com.cy.mvpframetest.project.projectlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cy.mvpframetest.R;
import com.cy.mvpframetest.base.BaseFragment;
import com.cy.mvpframetest.bean.ProListBean;
import com.cy.mvpframetest.content.ContentActivity;
import com.cy.mvpframetest.project.adpter.ProListAdapter;

import java.util.Objects;

import butterknife.BindView;

import static com.cy.mvpframetest.content.ContentActivity.toContentActivity;

public class ProjectListFragment extends BaseFragment<ProjectListPresenterImpl,ProjectListModelImpl> implements provectlistContract.ProjectListView {
    @BindView(R.id.rc_pro_list)
    RecyclerView mrcView;
    @BindView(R.id.fresh_pro_list)
    SwipeRefreshLayout fresh ;

    private ProListAdapter mAdapter;
    private int mCurrentpage = 1,pageCount;

    public static ProjectListFragment newInstance(int cid) {
        ProjectListFragment projectListFragment = new ProjectListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("cid", cid);
        projectListFragment.setArguments(bundle);
        return projectListFragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_project_list;
    }

    @Override
    protected void initSubViews(View view) {
        fresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadProListdata(getArguments().getInt("cid"), mCurrentpage);
            }
        });
    }

    @Override
    public void initData() {
        mPresenter.loadProListdata(getArguments().getInt("cid"), mCurrentpage);
    }

    @Override
    public void setListData(final ProListBean list) {
        fresh.setRefreshing(false);
        pageCount = list.getPageCount();
        mCurrentpage = list.getCurPage();
        mrcView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ProListAdapter(R.layout.item_projectlist_rc,list.getDatas());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                toContentActivity(getActivity(),list.getDatas().get(position).getId(),list.getDatas().get(position).getLink(),list.getDatas().get(position).getTitle());
            }
        });
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mAdapter.setOnLoadMoreListener(mLoadMoreListener,mrcView);
        mrcView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        mrcView.setAdapter(mAdapter);
    }

    @Override
    public void loadMore(ProListBean list) {
        mAdapter.addData(list.getDatas());
        mCurrentpage = list.getCurPage();
        mAdapter.loadMoreComplete();
    }

    private BaseQuickAdapter.RequestLoadMoreListener mLoadMoreListener = new BaseQuickAdapter.RequestLoadMoreListener() {
        @Override
        public void onLoadMoreRequested() {
            if (mCurrentpage >= pageCount){
                mAdapter.loadMoreEnd();
            }else {
                mPresenter.loadMore(getArguments().getInt("cid"),mCurrentpage+1);
            }

        }
    };


}
