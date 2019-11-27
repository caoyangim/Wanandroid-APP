package com.cy.baserecycleviewdemo.bean;

import android.os.Build;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by lenovo on 2019/6/28
 * 功能描述：
 */

public class HomeItem implements MultiItemEntity {
    private String imageResource;
    private String title;
    private int itemType;

    public HomeItem(String imageResource, String title,int itemType) {
        this.imageResource = imageResource;
        this.title = title;
        this.itemType = itemType;
    }

    public String getImageResource() {
        return imageResource;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

}
