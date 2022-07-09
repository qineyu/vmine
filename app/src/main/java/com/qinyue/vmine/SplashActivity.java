package com.qinyue.vmine;

import com.alibaba.android.arouter.launcher.ARouter;
import com.qinyue.vcommon.base.BaseActivity;
import com.qinyue.vcommon.utils.StatusBarUtils;
import com.qinyue.vmine.databinding.ActivitySplashBinding;
/**
 * @ClassName: SplashActivity
 * @Description:
 * @Author: bodia
 * @Date: 2022/7/8
 */
public class SplashActivity extends BaseActivity<ActivitySplashBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onInitViewModel() {

    }

    @Override
    protected void onInitView() {
        dataBind.mainview.postDelayed(new Runnable() {
            @Override
            public void run() {
                ARouter.getInstance().build("/app/main").navigation();
                finish();
            }
        },2000);
    }

    @Override
    protected void onCreateBefore() {
        StatusBarUtils.fullScreen(this);
    }
}
