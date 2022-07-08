package com.qinyue.vcommon.base;

import com.kunminx.architecture.domain.event.Event;

/**
 * @ClassName: BaseEvent
 * @Description:
 * @Author: bodia
 * @Date: 2022/7/8
 */
public class BaseEvent<T extends BaseParms,P extends BaseResult> extends Event<T,P> {
    public static final int ERROR = -100;
}
