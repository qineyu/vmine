package com.qinyue.vmine.messager;

import com.kunminx.architecture.domain.dispatch.MviDispatcher;
import com.qinyue.vcommon.constants.SkinType;
import com.qinyue.vcommon.http.ErrorInfo;
import com.qinyue.vcommon.manager.MySkinManager;
import com.qinyue.vmine.event.MainEvent;

/**
 * @ClassName: MainMessager
 * @Description:
 * @Author: bodia
 * @Date: 2022/7/8
 */
public class MainMessager extends MviDispatcher<MainEvent> {
    @Override
    public void input(MainEvent event) {
       switch (event.eventId){
           case MainEvent.CHAGESKIN:{
               if (MySkinManager.getInstance().getSkinName().equals(SkinType.NIGHT.getValue())){
                   MySkinManager.getInstance().chageNomral();
                   MainEvent mainEvent = new MainEvent(MainEvent.CHAGESKINFINISH);
                   mainEvent.result.skinMsg = "默认模式";
                   sendResult(mainEvent);
               }else {
                   MySkinManager.getInstance().chageNight();
                   MainEvent mainEvent = new MainEvent(MainEvent.ERROR);
                   mainEvent.result.errorInfo= new ErrorInfo(new Throwable("夜间模式"));
                   sendResult(mainEvent);
               }
           }break;
       }
    }
}
