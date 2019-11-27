package com.cy.baserecycleviewdemo.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cy.baserecycleviewdemo.bean.HomeItem;
import com.cy.baserecycleviewdemo.R;

import java.util.List;

/**
 * Created by lenovo on 2019/6/28
 * https://www.jianshu.com/p/b343fcff51b0 简书介绍
 * 功能描述：一行数量随机（1,2,3）
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<HomeItem, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultipleItemQuickAdapter(List<HomeItem> data) {
        super(data);
        addItemType(1, R.layout.item_rc);
        addItemType(2, R.layout.item_rc2);
        addItemType(3, R.layout.item_rc2);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeItem item) {
        switch (helper.getItemViewType()){
            case 1:
                helper.setText(R.id.item_tit,item.getTitle());
                break;
            case 2:
                helper.setText(R.id.item_tit,item.getTitle());
                helper.setText(R.id.item_tit2,"2");
                break;
            case 3:
                helper.setText(R.id.item_tit,item.getTitle());
                helper.setText(R.id.item_tit2,"3");
                break;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager){
            final GridLayoutManager gridmanager = (GridLayoutManager) manager;
            gridmanager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type){
                        //GridLayout一行总个数为6，返回n则改为n/6
                        case 1:
                            //  6/6,即1个
                            return 6;
                        case 2:
                            //  6/3,即2个
                            return 3;
                        case 3:
                            //  6/2,即3个
                            return 2;
                        default:
                            return 6;
                    }
                }
            });
        }
    }
}
