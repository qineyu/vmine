package com.qinyue.vmain.activity;

import android.view.View;

import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qinyue.vcommon.base.BaseActivity;
import com.qinyue.vcommon.listener.OnMultiClickListener;
import com.qinyue.vcommon.manager.MySkinManager;
import com.qinyue.vcommon.navigation.ARouterConfig;
import com.qinyue.vmain.R;
import com.qinyue.vmain.databinding.ActivityLoginBinding;
import com.qinyue.vmain.event.LoginEvent;
import com.qinyue.vmain.messager.LoginMessager;
import com.qinyue.vmain.navigation.MainNavigationPath;

/**
 * @ClassName: LoginActivity
 * @Description:
 * @Author: bodia
 * @Date: 2022/7/9
 */
@Route(path = MainNavigationPath.MAIN_LOGIN)
public class LoginActivity extends BaseActivity<ActivityLoginBinding> {
    LoginMessager loginMessager;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onInitViewModel() {
        loginMessager = getActivityScopeViewModel(LoginMessager.class);
    }

    @Override
    protected void onInitView() {

    }

    @Override
    protected void onInput() {
        dataBind.but.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View view) {
                LoginEvent loginEvent = new LoginEvent(LoginEvent.LOGING);
                loginEvent.param.name = dataBind.nameEdit.getText().toString().trim();
                loginEvent.param.pwd = dataBind.pwdEdit.getText().toString().trim();
                loginMessager.input(loginEvent);
            }
        });
    }

    @Override
    protected void onOutput() {
        super.onOutput();
        loginMessager.output(this, new Observer<LoginEvent>() {
            @Override
            public void onChanged(LoginEvent loginEvent) {
                switch (loginEvent.eventId){
                    case LoginEvent.LOGINGSUCCESS:{
                        ARouterConfig.getAppService().toApp();
                        finish();
                    }break;
                }
            }
        });
    }
}
