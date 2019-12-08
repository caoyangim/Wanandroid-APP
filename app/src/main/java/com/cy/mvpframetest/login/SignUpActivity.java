package com.cy.mvpframetest.login;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.cy.mvpframetest.R;
import com.cy.mvpframetest.base.BaseActivity;
import com.cy.mvpframetest.bean.LoginBean;
import com.cy.mvpframetest.utils.ToastUtil;
import com.cy.mvpframetest.view.InputView;

import butterknife.BindView;

public class SignUpActivity extends BaseActivity implements LoginView{
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.btn_sign)
    Button signBtn;
    @BindView(R.id.iv_un)
    InputView userName;
    @BindView(R.id.iv_pwd)
    InputView password;
    @BindView(R.id.iv_repwd)
    InputView rePassword;

    LoginPresent mPresent = new LoginPresent();
    LoginModel mModel = new LoginModel();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void initSubViews(View view) {
        mPresent.onAttach(mModel,this);

        mToolbar.setNavigationOnClickListener(v -> finish());
        signBtn.setOnClickListener(v ->{
            String unm = userName.getInputStr();
            String pwd = password.getInputStr();
            String rePwd = rePassword.getInputStr();
            if (check(unm,pwd,rePwd)){
                mPresent.Register(unm,pwd,rePwd);
            }
        });
    }

    /**
     * 校验输入内容是否合法
     * @param unm
     * @param pwd
     * @param rePwd
     * @return true合法
     */
    private boolean check(String unm, String pwd, String rePwd) {
        if (unm.isEmpty() || pwd.isEmpty() || rePwd.isEmpty()) {
            ToastUtil.showToast("用户名或者密码不能为空！");
            return false;
        } else if (!pwd.equals(rePwd)){
            ToastUtil.showToast("两次输入密码不一致！");
            return false;
        }
        return true;
    }

    @Override
    public void LoginSuccess(LoginBean loginBean) {

    }

    @Override
    public void LoginFailed(String msg) {

    }

    @Override
    public void SignSuccess(LoginBean loginBean) {
        finish();
        ToastUtil.showToast("注册成功");
    }

    @Override
    public void SignFailed(String msg) {
        ToastUtil.showToast(msg);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
