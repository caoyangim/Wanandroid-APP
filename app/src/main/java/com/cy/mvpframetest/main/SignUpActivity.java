package com.cy.mvpframetest.main;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.cy.mvpframetest.R;
import com.cy.mvpframetest.base.BaseActivity;

import butterknife.BindView;

public class SignUpActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void initSubViews(View view) {
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

}
