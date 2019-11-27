package com.cy.baserecycleviewdemo.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cy.baserecycleviewdemo.R;
import com.cy.baserecycleviewdemo.bean.HomeItem;
import com.cy.baserecycleviewdemo.bean.MySection;

import java.util.List;

/**
 * Created by lenovo on 2019/6/28
 * 功能描述：分组布局
 */
public class SectionAdapter extends BaseSectionQuickAdapter<MySection, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public SectionAdapter(int layoutResId, int sectionHeadResId, List<MySection> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, MySection item) {
        helper.setText(R.id.header,item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, MySection item) {
        helper.setText(R.id.item_tit,(item.t).getTitle());
    }
}
