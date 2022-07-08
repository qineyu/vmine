package com.qinyue.vcommon.listener;

import androidx.test.services.events.ErrorInfo;

/**
 * @ClassName: OnError
 * @Description:
 * @Author: bodia
 * @Date: 2022/7/8
 */
public interface OnError {
    void onError(ErrorInfo error) throws Exception;
}
