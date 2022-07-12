package com.qinyue.vmain.messager;

import android.text.TextUtils;

import com.kunminx.architecture.domain.dispatch.MviDispatcher;
import com.qinyue.vcommon.base.BaseEvent;
import com.qinyue.vcommon.base.BaseMvi;
import com.qinyue.vcommon.http.ErrorInfo;
import com.qinyue.vcommon.http.OnError;
import com.qinyue.vmain.event.LoginEvent;
import com.qinyue.vmain.http.MainHttpWrapper;
import com.rxjava.rxlife.RxLife;

/**
 * @ClassName: LoginMessager
 * @Description:
 * @Author: bodia
 * @Date: 2022/7/9
 */
public class LoginMessager extends BaseMvi<LoginEvent> {
    @Override
    public void input(LoginEvent event) {
        switch (event.eventId) {
            case LoginEvent.LOGING: {
                toLogin(event);
            }
            break;
            case LoginEvent.REGISTER: {
                toRegister(event);
            }
            break;
        }
    }

    private void toRegister(LoginEvent event){
        if (TextUtils.isEmpty(event.param.name)||TextUtils.isEmpty(event.param.pwd)){
            LoginEvent loginEvent = new LoginEvent(BaseEvent.ERROR);
            loginEvent.result.errorInfo = new ErrorInfo(1,"请填写正确的账号或密码");
            setResult(loginEvent);
            return;
        }
        MainHttpWrapper.toRegister(event.param.name,event.param.pwd,String.class)
                .to(RxLife.toMain(mScope))
                .subscribe(s -> {
                    LoginEvent loginEvent = new LoginEvent(LoginEvent.LOGINGSUCCESS);
                    setResult(loginEvent);
                },(OnError)e->{
                    LoginEvent loginEvent = new LoginEvent(BaseEvent.ERROR);
                    loginEvent.result.errorInfo = e;
                    setResult(loginEvent);
                });
    }

    private void toLogin(LoginEvent event){
        if (TextUtils.isEmpty(event.param.name)||TextUtils.isEmpty(event.param.pwd)){
            LoginEvent loginEvent = new LoginEvent(BaseEvent.ERROR);
            loginEvent.result.errorInfo = new ErrorInfo(1,"请填写正确的账号或密码");
            setResult(loginEvent);
            return;
        }
        MainHttpWrapper.toLogin(event.param.name,event.param.pwd,String.class)
                .to(RxLife.toMain(mScope))
                .subscribe(s -> {
                    LoginEvent loginEvent = new LoginEvent(LoginEvent.LOGINGSUCCESS);
                    setResult(loginEvent);
                },(OnError)e->{
                    LoginEvent loginEvent = new LoginEvent(BaseEvent.ERROR);
                    loginEvent.result.errorInfo = e;
                    setResult(loginEvent);
                });
    }
}
