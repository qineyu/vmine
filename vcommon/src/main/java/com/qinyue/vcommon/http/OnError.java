package com.qinyue.vcommon.http;

import java.io.IOException;
import java.net.SocketException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import rxhttp.wrapper.exception.ParseException;

/**
 * @ClassName: OnError
 * @Description:
 * @Author: bodia
 * @Date: 2022/7/8
 */
public interface OnError extends Consumer<Throwable> {

    @Override
    default void accept(Throwable throwable) throws Exception {
        if (throwable instanceof IOException &&!(throwable instanceof ParseException)){
            //拦截主动取消的http错误异常
            return;
        }else if (throwable instanceof SocketException){
            //拦截主动取消的下载错误异常
            return;
        }
        AndroidSchedulers.mainThread().scheduleDirect(new Runnable() {
            @Override
            public void run() {
                try {
                    onError(new ErrorInfo(throwable));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    void onError(ErrorInfo error) throws Exception;
}
