package com.cy.mvpframetest.http;



import com.cy.mvpframetest.bean.BaseResponse;

import rx.functions.Func1;

/**
 * Created by lenovo on 2019/5/9
 * 功能描述：拦截失败的请求，只有非空数据才能通过
 */
public class PayLoad<T> implements Func1<BaseResponse<T>,T> {
    @Override
    public T call(BaseResponse<T> tBaseResponse) {
        if (!tBaseResponse.isSuccess()){
            throw new Error("获取数据失败:errorCode="+tBaseResponse.errorCode+",errorMsg="+tBaseResponse.errorMsg);
        }
        return tBaseResponse.data;
    }
}
