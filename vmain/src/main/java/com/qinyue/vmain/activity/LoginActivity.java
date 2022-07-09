package com.qinyue.vmain.activity;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qinyue.vcommon.base.BaseActivity;
import com.qinyue.vcommon.listener.OnMultiClickListener;
import com.qinyue.vcommon.manager.MySkinManager;
import com.qinyue.vmain.R;
import com.qinyue.vmain.databinding.ActivityLoginBinding;
import com.qinyue.vmain.navigation.MainNavigationPath;

/**
 * @ClassName: LoginActivity
 * @Description:
 * @Author: bodia
 * @Date: 2022/7/9
 */
@Route(path = MainNavigationPath.MAIN_LOGIN)
public class LoginActivity extends BaseActivity<ActivityLoginBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onInitViewModel() {

    }

    @Override
    protected void onInitView() {

    }

    @Override
    protected void onInput() {
        super.onInput();
    }
}
