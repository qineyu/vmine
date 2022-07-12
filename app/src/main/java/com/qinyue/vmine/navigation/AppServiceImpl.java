package com.qinyue.vmine.navigation;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qinyue.vcommon.navigation.ARouterConfig;
import com.qinyue.vcommon.navigation.AppService;

/**
 * @ClassName: AppServiceImpl
 * @Description:
 * @Author: bodia
 * @Date: 2022/7/12
 */
@Route(path = ARouterConfig.APP_SERVICE)
public class AppServiceImpl implements AppService {
    @Override
    public void toApp() {
        AppNavigation.toApp();
    }

    @Override
    public void init(Context context) {

    }
}
