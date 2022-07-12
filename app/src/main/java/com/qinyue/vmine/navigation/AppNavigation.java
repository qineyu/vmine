package com.qinyue.vmine.navigation;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @ClassName: MainNavigation
 * @Description:
 * @Author: bodia
 * @Date: 2022/7/9
 */
public class AppNavigation {
    /**
     * 去主页
     */
    public static void toApp(){
       ARouter.getInstance().build(AppNavigationPath.APP_MAIN).navigation();
    }
}
