package com.qinyue.vmain.event;

import com.qinyue.vcommon.base.BaseEvent;
import com.qinyue.vcommon.base.BaseParms;
import com.qinyue.vcommon.base.BaseResult;
import com.qinyue.vcommon.base.BaseUserBean;

/**
 * @ClassName: LoginEvent
 * @Description:
 * @Author: bodia
 * @Date: 2022/7/9
 */
public class LoginEvent extends BaseEvent<LoginEvent.Param, LoginEvent.Result> {
    public static final int LOGING = 1;
    public static final int LOGINGSUCCESS = 2;
    public static final int REGISTER = 3;

    public LoginEvent(int eventId) {
        this.eventId = eventId;
        this.param   = new Param();
        this.result   = new Result();
    }
    public static class Param extends BaseParms {
       public String name,pwd;
    }

    public static class Result extends BaseResult {
       public BaseUserBean userBean;
    }
}
