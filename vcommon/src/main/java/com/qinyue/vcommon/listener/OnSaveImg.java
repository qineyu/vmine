package com.qinyue.vcommon.listener;

/**
 * @ClassName: OnSaveImg
 * @Description:
 * @Author: bodia
 * @Date: 2022/6/11
 */
public interface OnSaveImg {
    void OnSuccess(String url,String path);
    void onFalied(String url);
}
