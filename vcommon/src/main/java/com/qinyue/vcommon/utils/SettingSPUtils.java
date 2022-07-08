package com.qinyue.vcommon.utils;

import android.content.Context;
import android.text.TextUtils;

import com.qinyue.vcommon.base.BaseApplication;
import com.qinyue.vcommon.base.BaseSPUtil;
import com.qinyue.vcommon.constants.SkinType;

/**
 * SharedPreferences管理工具基类
 *
 * @author xuexiang
 * @since 2018/11/27 下午5:16
 */
public class SettingSPUtils extends BaseSPUtil {

    private static volatile SettingSPUtils sInstance = null;

    private SettingSPUtils(Context context) {
        super(context);
    }

    private String SKIN = "SKIN_TYPE";

    /**
     * 获取单例
     *
     * @return
     */
    public static SettingSPUtils getInstance() {
        if (sInstance == null) {
            synchronized (SettingSPUtils.class) {
                if (sInstance == null) {
                    sInstance = new SettingSPUtils(BaseApplication.getApplication());
                }
            }
        }
        return sInstance;
    }

    public void setSKIN(String value){
        putString(SKIN,value);
    }
    public String getSKIN(){
       return getString(SKIN, SkinType.NORMAL.getValue());
    }
}
