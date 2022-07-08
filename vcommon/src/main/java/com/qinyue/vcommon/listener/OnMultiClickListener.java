package com.qinyue.vcommon.listener;

import android.view.View;

/**
 * @类名 onMultiClickListener
 * @作者 bodia
 * @时间 11/11/21 2:35 PM
 * @描述 view的自定义点击间隔
 */
public abstract class OnMultiClickListener implements View.OnClickListener {

    private static final int MIN_CLICK_TIME = 500;
    private static long lastClickTime;

    public abstract void onMultiClick(View view);

    @Override
    public void onClick(View v) {
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime-lastClickTime)>=MIN_CLICK_TIME){
            lastClickTime = curClickTime;
            onMultiClick(v);
        }
    }
}
