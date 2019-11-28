package com.cy.mvpframetest.project;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.cy.mvpframetest.R;
import com.cy.mvpframetest.base.BaseFragment;
import com.cy.mvpframetest.bean.ProCategoryBean;
import com.cy.mvpframetest.project.adpter.ProPageAdapter;

import java.util.List;

import butterknife.BindView;

public class ProjectFragment extends BaseFragment<ProjectPresenterImpl,ProjectModelImpl> implements ProjectContract.ProjectView {
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.pro_vp)
    ViewPager viewPager;

    private ProPageAdapter mAdapter;

    public static ProjectFragment newInstance() {
        return new ProjectFragment();
    }
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initSubViews(View view) {

    }

    @Override
    public void initData() {
        mPresenter.loadProCateData();
    }

    @Override
    public void setProCateData(List<ProCategoryBean> beanList) {
        mAdapter = new ProPageAdapter(getActivity().getSupportFragmentManager(),beanList);
        viewPager.setAdapter(mAdapter);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tablayout.setupWithViewPager(viewPager);
    }
}
