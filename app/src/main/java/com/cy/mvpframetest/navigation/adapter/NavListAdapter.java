package com.cy.mvpframetest.navigation.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cy.mvpframetest.R;
import com.cy.mvpframetest.bean.NavCategoryBean;

import java.util.List;

public class NavListAdapter extends BaseQuickAdapter<NavCategoryBean, BaseViewHolder> {
    public NavListAdapter(int layoutResId, @Nullable List<NavCategoryBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NavCategoryBean item) {
        helper.setText(R.id.tv_nav_list,item.getName());
    }
}
