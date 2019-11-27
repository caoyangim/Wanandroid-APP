package com.cy.mvpframetest.utils;

import android.widget.Toast;

import com.cy.mvpframetest.MyApp;

/**
 * Created by lenovo on 2019/5/25
 * 功能描述：
 */
public class ToastUtil {
    private static Toast toast;
    public static void showToast(String text) {
        if (toast == null) {
            toast = Toast.makeText(MyApp.getInstance(), text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }
}
