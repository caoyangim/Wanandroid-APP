package com.cy.mvpframetest.utils;

import java.lang.reflect.ParameterizedType;

/**
 * Created by lenovo on 2019/5/25
 * 功能描述：
 */
public class ReflectUtil {
    public static <T> T getT(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType)
                    (o.getClass().getGenericSuperclass())).getActualTypeArguments()[i]).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
