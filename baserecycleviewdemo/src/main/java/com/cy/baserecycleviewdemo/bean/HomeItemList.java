package com.cy.baserecycleviewdemo.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by lenovo on 2019/6/28
 * 功能描述：
 */
public class HomeItemList implements MultiItemEntity {
    private String title;
    private List<HomeItem> mHomeItems;
    private int itemType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<HomeItem> getHomeItems() {
        return mHomeItems;
    }

    public void setHomeItems(List<HomeItem> homeItems) {
        mHomeItems = homeItems;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
