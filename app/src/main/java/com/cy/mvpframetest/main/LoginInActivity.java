package com.cy.mvpframetest.main;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.cy.mvpframetest.R;
import com.cy.mvpframetest.base.BaseActivity;
import com.cy.mvpframetest.bean.LoginBean;
import com.cy.mvpframetest.events.LoginEvent;
import com.cy.mvpframetest.http.api.LoginLoader;
import com.cy.mvpframetest.utils.ToastUtil;
import com.cy.mvpframetest.view.InputView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import rx.functions.Action1;

public class LoginInActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.btn_login)
    Button loginBtn;
    @BindView(R.id.iv_un)
    InputView userName;
    @BindView(R.id.iv_pwd)
    InputView passWord;

    LoginLoader mLoginLoader;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_in;
    }

    @Override
    protected void initSubViews(View view) {
        mToolbar.setNavigationOnClickListener(v -> finish());
        loginBtn.setOnClickListener(v -> {
            String unm = userName.getInputStr();
            String pwd = passWord.getInputStr();
            if ( unm.isEmpty() || pwd.isEmpty()) {
                ToastUtil.showToast("用户名或者密码不能为空！");
            }else {
                mLoginLoader = new LoginLoader();
                mLoginLoader.Login(unm,pwd).subscribe(new Action1<LoginBean>() {
                    @Override
                    public void call(LoginBean loginBean) {
                        EventBus.getDefault().post(new LoginEvent(true,loginBean.getUsername()));
                        finish();
                        ToastUtil.showToast("登陆成功");
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        ToastUtil.showToast(throwable.getMessage());
                    }
                });
            }

        });
    }

    public void toSign(View v){
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }
}
