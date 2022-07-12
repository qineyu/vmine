package com.qinyue.vcommon.base;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.kunminx.architecture.domain.dispatch.MviDispatcher;
import com.qinyue.vcommon.utils.ToastUtils;
import com.rxjava.rxlife.BaseScope;

/**
 * @ClassName: BaseMviDispatcher
 * @Description:
 * @Author: bodia
 * @Date: 2022/7/12
 */
public abstract class BaseMvi<T extends BaseEvent> extends MviDispatcher<T> {
    protected BaseScope mScope;

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        mScope = new BaseScope(owner);
        super.onCreate(owner);
    }

    public void setResult(@NonNull T event){
        if (event.eventId==BaseEvent.ERROR){
            ToastUtils.toast(((BaseResult)event.result).errorInfo.getErrorMsg());
            return;
        }
        sendResult(event);
    }

}
