package com.cy.mvpframetest.bean;

import java.util.List;

public class NavCategoryBean {
    private List<ArticleDetailData> articles;
    private int cid;
    private String name;

    public List<ArticleDetailData> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleDetailData> articles) {
        this.articles = articles;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
