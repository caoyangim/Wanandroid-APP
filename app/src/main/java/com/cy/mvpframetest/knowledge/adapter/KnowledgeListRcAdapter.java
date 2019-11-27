package com.cy.mvpframetest.knowledge.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cy.mvpframetest.R;
import com.cy.mvpframetest.bean.ArticleDetailData;

import java.util.List;

/**
 * 功能描述：知识体系（二级）界面 adapter 点开
 */
public class KnowledgeListRcAdapter extends BaseQuickAdapter<ArticleDetailData, BaseViewHolder> {

    public KnowledgeListRcAdapter(int layoutResId, @Nullable List<ArticleDetailData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleDetailData item) {
        helper.setText(R.id.item_kl_author,item.getAuthor());
        helper.setText(R.id.item_kl_date,item.getNiceDate());
        helper.setText(R.id.item_kl_title,item.getTitle());
        helper.setText(R.id.item_kl_chapterName,item.getChapterName());
    }
}
