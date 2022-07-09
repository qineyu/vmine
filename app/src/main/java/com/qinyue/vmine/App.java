package com.qinyue.vmine;

import com.qinyue.vcommon.base.BaseApplication;

/**
 * @ClassName: App
 * @Description:
 * @Author: bodia
 * @Date: 2022/7/7
 */
public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
