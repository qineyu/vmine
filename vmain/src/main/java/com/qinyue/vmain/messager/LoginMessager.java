package com.qinyue.vmain.messager;

import com.kunminx.architecture.domain.dispatch.MviDispatcher;
import com.qinyue.vmain.event.LoginEvent;

/**
 * @ClassName: LoginMessager
 * @Description:
 * @Author: bodia
 * @Date: 2022/7/9
 */
public class LoginMessager extends MviDispatcher<LoginEvent> {
    @Override
    public void input(LoginEvent event) {
        switch (event.eventId) {
            case LoginEvent.LOGING: {
            }
            break;
        }
    }

    private void toRegister(){

    }
}
