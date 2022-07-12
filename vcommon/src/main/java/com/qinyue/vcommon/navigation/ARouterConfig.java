package com.qinyue.vcommon.navigation;

import com.alibaba.android.arouter.launcher.ARouter;
import com.qinyue.vcommon.utils.ToastUtils;

/**
 * @ClassName: ARouterConfig
 * @Description:
 * @Author: bodia
 * @Date: 2022/7/12
 */
public class ARouterConfig {
    /**
     * app的服务接口地址
     */
    public static final String APP_SERVICE = "/app/service";

    /**
     * h获取app的服务接口
     * @return
     */
    public static AppService getAppService(){
        Object navigation = ARouter.getInstance().build(APP_SERVICE).navigation();
        if (navigation!=null&&navigation instanceof AppService){
            return(AppService)navigation ;
        }else {
            throw new NullPointerException("未找到该服务");
        }
    }
}
