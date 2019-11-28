package com.cy.mvpframetest.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cy.mvpframetest.utils.ToastUtil;
import com.just.agentweb.LogUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.lang.reflect.Field;

import butterknife.ButterKnife;

/**
 * Created by lenovo on 2019/5/25
 * 功能描述：
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View containerView = getLayoutInflater().inflate(getLayoutId(), getViewGroupRoot());
        setContentView(containerView);
        //去掉状态栏，显示全屏
        /*View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);*/
        ButterKnife.bind(this);
        initSubViews(containerView);
    }
    
    protected abstract int getLayoutId();
    protected abstract void initSubViews(View view);
    public ViewGroup getViewGroupRoot() {
        return null;
    }

    @Override
    public void showLoading() {
        Log.e(">>","show Loading");
    }

    @Override
    public void hideLoading() {
        Log.e(">>","Hide Loading");
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onStop() {
        super.onStop();
        //Log.e(">>>","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Log.e(">>>","onDestroy");
    }
}
