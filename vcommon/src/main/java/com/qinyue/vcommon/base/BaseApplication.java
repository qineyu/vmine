package com.qinyue.vcommon.base;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.qinyue.vcommon.BuildConfig;
import com.qinyue.vcommon.utils.XUtil;

import skin.support.SkinCompatManager;
import skin.support.app.SkinAppCompatViewInflater;
import skin.support.constraint.app.SkinConstraintViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;
import skin.support.utils.Slog;

/**
 * @ClassName: BaseApplication
 * @Description:
 * @Author: bodia
 * @Date: 2022/7/7
 */
public class BaseApplication extends Application {

    //全局唯一的context
    private static BaseApplication application;

    public static Context getApplication() {
        return application;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        application = this;
        //MultiDexf分包初始化，必须最先初始化
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化阿里路由
        if (BuildConfig.IS_DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
        //初始化工具类
        XUtil.debug(BuildConfig.IS_DEBUG);
        XUtil.init(this);
        //初始化换肤功能
        Slog.DEBUG = BuildConfig.IS_DEBUG;
        SkinCompatManager.withoutActivity(this)
                .addInflater(new SkinAppCompatViewInflater())           // 基础控件换肤初始化
                .addInflater(new SkinMaterialViewInflater())            // material design 控件换肤初始化[可选]
                .addInflater(new SkinConstraintViewInflater())          // ConstraintLayout 控件换肤初始化[可选]
//                .addInflater(new SkinCardViewInflater())                // CardView v7 控件换肤初始化[可选]
                .setSkinWindowBackgroundEnable(true)                   // 关闭windowBackground换肤，默认打开[可选]
                .loadSkin();
    }
}
