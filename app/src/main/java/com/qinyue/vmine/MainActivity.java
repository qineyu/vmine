package com.qinyue.vmine;

import android.view.View;

import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qinyue.vcommon.base.BaseActivity;
import com.qinyue.vcommon.listener.OnMultiClickListener;
import com.qinyue.vcommon.manager.MySkinManager;
import com.qinyue.vcommon.utils.ToastUtils;
import com.qinyue.vmine.databinding.ActivityMainBinding;
import com.qinyue.vmine.event.MainEvent;
import com.qinyue.vmine.messager.MainMessager;
@Route(path = "/app/main")
public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private MainMessager mainMessager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitViewModel() {
        mainMessager = getActivityScopeViewModel(MainMessager.class);
    }

    @Override
    protected void onInitView() {

    }

    @Override
    protected void onInput() {
        dataBind.titleTv.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View view) {
                mainMessager.input(new MainEvent(MainEvent.CHAGESKIN));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        MySkinManager.getInstance().chageForSystemNight();
    }

    @Override
    protected void onOutput() {
        mainMessager.output(this, new Observer<MainEvent>() {
            @Override
            public void onChanged(MainEvent mainEvent) {
                switch (mainEvent.eventId) {
                    case MainEvent.ERROR: {
                        ToastUtils.toast(mainEvent.result.errorInfo.errorMessage);
                    }
                    break;
                    case MainEvent.CHAGESKINFINISH: {
                        ToastUtils.toast(mainEvent.result.skinMsg);
                    }
                    break;
                }
            }
        });
    }
}