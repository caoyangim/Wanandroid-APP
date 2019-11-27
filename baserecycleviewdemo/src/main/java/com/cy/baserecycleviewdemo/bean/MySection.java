package com.cy.baserecycleviewdemo.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by lenovo on 2019/6/28
 * 功能描述：
 */
public class MySection extends SectionEntity<HomeItem> {
    private boolean isMore;
    public MySection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MySection(HomeItem homeItem) {
        super(homeItem);
    }
}
