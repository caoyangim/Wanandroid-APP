package com.cy.mvpframetest.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.cy.mvpframetest.R;
import com.cy.mvpframetest.base.BaseActivity;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {
    private static final long DELAY_TIME = 2000;
    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initSubViews(View view) {
        avi.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 启动登录窗口
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.translate_in, R.anim.translate_out);
                // 关闭启动画面
                finish();
            }
        }, DELAY_TIME);
    }
}
