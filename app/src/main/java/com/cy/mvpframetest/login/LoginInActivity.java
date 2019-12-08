package com.cy.mvpframetest.login;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.cy.mvpframetest.R;
import com.cy.mvpframetest.base.BaseActivity;
import com.cy.mvpframetest.bean.LoginBean;
import com.cy.mvpframetest.events.LoginEvent;
import com.cy.mvpframetest.utils.ToastUtil;
import com.cy.mvpframetest.view.InputView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

public class LoginInActivity extends BaseActivity implements LoginView{
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.btn_login)
    Button loginBtn;
    @BindView(R.id.iv_un)
    InputView userName;
    @BindView(R.id.iv_pwd)
    InputView passWord;

    LoginPresent mPresent = new LoginPresent();
    LoginModel mModel = new LoginModel();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_in;
    }

    @Override
    protected void initSubViews(View view) {
        mPresent.onAttach(mModel,this);

        mToolbar.setNavigationOnClickListener(v -> finish());
        loginBtn.setOnClickListener(v -> {
            String unm = userName.getInputStr();
            String pwd = passWord.getInputStr();
            if (check(unm,pwd))
                mPresent.Login(unm,pwd);
        });
    }

    private boolean check(String unm, String pwd) {
        if (unm.isEmpty() || pwd.isEmpty()){
            ToastUtil.showToast("用户名密码不能为空");
            return false;
        }
        return true;
    }

    public void toSign(View v){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public void LoginSuccess(LoginBean loginBean) {
        EventBus.getDefault().post(new LoginEvent(true,loginBean.getUsername()));
        finish();
        ToastUtil.showToast("登陆成功");
    }

    @Override
    public void LoginFailed(String msg) {
        ToastUtil.showToast(msg);
    }

    @Override
    public void SignSuccess(LoginBean loginBean) {

    }

    @Override
    public void SignFailed(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
