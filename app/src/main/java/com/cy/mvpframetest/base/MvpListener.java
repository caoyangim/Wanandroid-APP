package com.cy.mvpframetest.base;

public interface MvpListener<T> {
    void onSuccess(T result);
    void onError(String errorMsg);
}