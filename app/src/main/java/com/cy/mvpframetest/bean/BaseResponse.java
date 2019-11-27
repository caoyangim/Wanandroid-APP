package com.cy.mvpframetest.bean;

/**
 * Created by lenovo on 2019/5/9
 * 功能描述：网络请求基类
 */
public class BaseResponse<T> {
    public int errorCode;
    public String errorMsg;
    public T data;

    /**
     * @return status==1 有非空数据才算成功
     */
    public boolean isSuccess(){
        return errorCode == 0;
    }

}
