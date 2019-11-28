package com.cy.mvpframetest.project.adpter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cy.mvpframetest.R;
import com.cy.mvpframetest.bean.ArticleDetailData;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ProListAdapter extends BaseQuickAdapter<ArticleDetailData,BaseViewHolder> {
    public ProListAdapter(int layoutResId, @Nullable List<ArticleDetailData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleDetailData item) {
        helper.setText(R.id.item_pro_tit,item.getTitle())
                .setText(R.id.item_pro_author,item.getAuthor())
                .setText(R.id.item_pro_date,item.getNiceDate());
        ImageView img = helper.getView(R.id.item_pro_img);
        Glide.with(mContext).load(item.getEnvelopePic())
                .apply(new RequestOptions()
                        .transforms(new CenterCrop(), new RoundedCorners(10)
                        ))
                .into(img);
    }
}
