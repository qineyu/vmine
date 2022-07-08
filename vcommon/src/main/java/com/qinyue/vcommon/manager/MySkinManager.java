package com.qinyue.vcommon.manager;

import android.text.TextUtils;

import com.qinyue.vcommon.constants.SkinType;
import com.qinyue.vcommon.utils.SettingSPUtils;

import skin.support.SkinCompatManager;
import skin.support.utils.SkinPreference;

/**
 * @ClassName: MySkinManager
 * @Description:
 * @Author: bodia
 * @Date: 2022/7/7
 */
public class MySkinManager {
    private static MySkinManager instance;

    public static MySkinManager getInstance() {
        synchronized (MySkinManager.class) {
            if (instance == null) {
                instance = new MySkinManager();
            }
        }
        return instance;
    }

    /**
     * 切换皮肤
     *
     * @param name 资源名称
     * @param skinLoaderListener 资源加载监听
     */
    public void chageSkin(String name, SkinCompatManager.SkinLoaderListener skinLoaderListener) {
        if (!TextUtils.isEmpty(name)) {
            //当前是默认皮肤
            if (getSkinName().equals(name)) {
                //当前和选择的是相同皮肤就直接退出
                return;
            }
            if ( name.equals(SkinType.NORMAL.getValue())){
                //如果是默认皮肤
                SkinCompatManager.getInstance().restoreDefaultTheme();
                if (skinLoaderListener!=null){
                    skinLoaderListener.onSuccess();
                }
            }else {
                SkinCompatManager.getInstance().loadSkin(name, skinLoaderListener, SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN);
            }
        }else {
            if (skinLoaderListener!=null){
                skinLoaderListener.onFailed("资源名称不能为空");
            }
        }
    }

    /**
     * 返回当前主题的名称
     * @return
     */
    public String getSkinName(){
        if(TextUtils.isEmpty(SkinPreference.getInstance().getSkinName())){
            return SkinType.NORMAL.getValue();
        }
        return SkinPreference.getInstance().getSkinName();
    }

    /**
     * 修改主题颜色为默认
     */
    public void chageNomral(){
        chageSkin(SkinType.NORMAL.getValue(),null);
    }
    /**
     * 修改主题颜色为夜间
     */
    public void chageNight(){
        chageSkin(SkinType.NIGHT.getValue(),null);
    }
}
