package com.qinyue.vcommon.base;

import static android.content.pm.PackageInstaller.SessionInfo.INVALID_ID;
import static skin.support.widget.SkinCompatHelper.checkResourceId;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.SkinAppCompatDelegateImpl;
import androidx.core.view.LayoutInflaterCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

import com.kunminx.architecture.ui.scope.ViewModelScope;
import com.qinyue.vcommon.manager.MySkinManager;
import com.qinyue.vcommon.utils.ACache;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCompatDelegate;
import skin.support.content.res.SkinCompatThemeUtils;
import skin.support.content.res.SkinCompatVectorResources;
import skin.support.observe.SkinObservable;
import skin.support.observe.SkinObserver;

/**
 * @ClassName: BaseActivity
 * @Description:
 * @Author: bodia
 * @Date: 2022/7/7
 */
public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity implements SkinObserver {
    private final ViewModelScope mViewModelScope = new ViewModelScope();
    protected T dataBind;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBind = DataBindingUtil.setContentView(this,getLayoutId());
        onInitViewModel();
        onInitView();
        onInitData();
        onOutput();
        onInput();
    }

    /**
     * 初始化控件
     */
    protected abstract int getLayoutId();

    @Override
    protected void onResume() {
        super.onResume();
        SkinCompatManager.getInstance().addObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinCompatManager.getInstance().deleteObserver(this);
    }


    @NonNull
    @Override
    public AppCompatDelegate getDelegate() {
        return SkinAppCompatDelegateImpl.get(this, this);
    }

    protected abstract void onInitViewModel();

    protected abstract void onInitView();

    protected void onInitData() {
    }

    protected void onOutput() {
    }

    protected void onInput() {
    }

    protected <T extends ViewModel> T getActivityScopeViewModel(@NonNull Class<T> modelClass) {
        return mViewModelScope.getActivityScopeViewModel(this, modelClass);
    }

    protected <T extends ViewModel> T getApplicationScopeViewModel(@NonNull Class<T> modelClass) {
        return mViewModelScope.getApplicationScopeViewModel(modelClass);
    }

    @Override
    public void updateSkin(SkinObservable observable, Object o) {
    }

    /**
     * 深色模式监听
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int systemTheme = newConfig.uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (systemTheme) {
            case Configuration.UI_MODE_NIGHT_NO: {//非深色模式
                MySkinManager.getInstance().chageNomral();
            }
            break;
            case Configuration.UI_MODE_NIGHT_YES: {//深色模式
                MySkinManager.getInstance().chageNight();
            }
            break;
        }
    }
}
