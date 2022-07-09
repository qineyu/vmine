package com.qinyue.vmain.navigation;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @ClassName: MainNavigation
 * @Description:
 * @Author: bodia
 * @Date: 2022/7/9
 */
public class MainNavigation {
    /**
     * 去动态主页
     */
    public static void toLogin(){
       ARouter.getInstance().build(MainNavigationPath.MAIN_LOGIN).navigation();
    }
}
