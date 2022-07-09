package com.qinyue.vmine;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Handler;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.qinyue.vcommon.base.BaseActivity;
import com.qinyue.vcommon.listener.OnMultiClickListener;
import com.qinyue.vcommon.logger.Logger;
import com.qinyue.vcommon.utils.GlideUtils;
import com.qinyue.vcommon.utils.StatusBarUtils;
import com.qinyue.vcommon.views.ArcProgressStackView;
import com.qinyue.vmain.navigation.MainNavigation;
import com.qinyue.vmine.databinding.ActivitySplashBinding;

import java.util.ArrayList;

/**
 * @ClassName: SplashActivity
 * @Description:
 * @Author: bodia
 * @Date: 2022/7/8
 */
public class SplashActivity extends BaseActivity<ActivitySplashBinding> {

    private Handler mHandler;
    //展示时间
    private int mTime = 3500;
    private float nowTime = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onInitViewModel() {

    }

    @Override
    protected void onInitView() {
        mHandler = new Handler(getMainLooper());
        GlideUtils.loadImage(dataBind.img,"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fa-ssl.duitang.com%2Fuploads%2Fitem%2F201811%2F16%2F20181116144143_mbgii.thumb.700_0.jpg&refer=http%3A%2F%2Fa-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1659946419&t=dc667c4485dafeb332310877d042c4d6");
        dataBind.progressView.setShadowColor(Color.argb(200, 0, 0, 0));
        dataBind.progressView.setAnimationDuration(2000);
        dataBind.progressView.setSweepAngle(360);

        final int[] colors = new int[1];
        final int[] bgColors = new int[1];
        for (int i = 0; i < 1; i++) {
            colors[i] = Color.parseColor("#ffa50a");
            bgColors[i] =  Color.parseColor("#64ffa50a");
        }
        final ArrayList<ArcProgressStackView.Model> models = new ArrayList<>();
        models.add(new ArcProgressStackView.Model("STRATEGY", 100, bgColors[0], colors[0]));
        dataBind.progressView.setModels(models);
    }

    @Override
    protected void onCreateBefore() {
        StatusBarUtils.fullScreen(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler!=null){
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    protected void onInput() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                nowTime = nowTime+30;
                if (nowTime>=mTime){
                    dataBind.progressView.getModels().get(0).setProgress(0);
                    dataBind.progressView.invalidate();
                    return;
                }
                mHandler.postDelayed(this,30);
                dataBind.progressView.getModels().get(0).setProgress(100-Math.round(nowTime / mTime * 100));
                dataBind.progressView.invalidate();
            }
        });
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MainNavigation.toLogin();
                finish();
            }
        },mTime);
        dataBind.timeBut.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View view) {
                mHandler.removeCallbacksAndMessages(null);
                MainNavigation.toLogin();
                finish();
            }
        });
    }
}
