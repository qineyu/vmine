package com.qinyue.vmine.event;

import com.kunminx.architecture.domain.event.Event;
import com.qinyue.vcommon.base.BaseEvent;
import com.qinyue.vcommon.base.BaseParms;
import com.qinyue.vcommon.base.BaseResult;

import java.util.List;

/**
 * @ClassName: MainEvent
 * @Description:
 * @Author: bodia
 * @Date: 2022/7/8
 */
public class MainEvent extends BaseEvent<MainEvent.Param,MainEvent.Result> {
    public static final int CHAGESKIN = 1;
    public static final int CHAGESKINFINISH = 2;

    public MainEvent(int eventId) {
        this.eventId = eventId;
        this.param   = new Param();
        this.result   = new Result();
    }
    public static class Param extends BaseParms {
        String skinName;
    }

    public static class Result extends BaseResult {
        public String skinMsg;
    }
}
