package com.cy.mvpframetest.content;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.cy.mvpframetest.R;
import com.cy.mvpframetest.base.BaseActivity;
import com.just.agentweb.AgentWeb;
import com.r0adkll.slidr.Slidr;

import butterknife.BindView;

/**
 * Created by lenovo on 2019/6/26
 * 功能描述：内容详情页
 */

public class ContentActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.content_layout)
    RelativeLayout mLayout;
    private AgentWeb mAgentWeb;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_content;
    }

    @Override
    protected void initSubViews(View view) {
        int primary = getResources().getColor(R.color.colorPrimary);
        int secondary = getResources().getColor(R.color.colorPrimary);
        Slidr.attach(this,primary,secondary);
        toolBarSet();
        initWebView(getIntent().getStringExtra("url"));
    }

    private void toolBarSet() {
        toolbar.setTitle(getIntent().getStringExtra("title"));
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        //getSupportActionBar().setHomeButtonEnabled(true); 设置返回键可用,此处无效
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initWebView(String url) {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mLayout, new RelativeLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            backWebView();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /***
     * WebView浏览后退，若没有后退则关闭浏览界面
     */
    private void backWebView() {
        boolean isBack = mAgentWeb.back();
        Log.d("后退判断->" ,">>>"+ isBack);
        if (!isBack) {
            finish();
        }
    }

    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.e("-->","状态栏高度"+height);
        return height;
    }
}
