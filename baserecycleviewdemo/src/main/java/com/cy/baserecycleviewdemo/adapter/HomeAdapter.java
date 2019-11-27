package com.cy.baserecycleviewdemo.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cy.baserecycleviewdemo.R;
import com.cy.baserecycleviewdemo.bean.HomeItem;

import java.util.List;

/**
 * Created by lenovo on 2019/6/28
 * 功能描述：
 */
public class HomeAdapter extends BaseQuickAdapter<HomeItem, BaseViewHolder> {

    public HomeAdapter(int layoutResId, @Nullable List<HomeItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeItem item) {
        helper.setText(R.id.item_tit,item.getTitle());
    }
}
