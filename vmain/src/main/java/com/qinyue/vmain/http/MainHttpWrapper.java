package com.qinyue.vmain.http;

import io.reactivex.rxjava3.core.Observable;
import rxhttp.RxHttp;

/**
 * @ClassName: MainHttpWapper
 * @Description:
 * @Author: bodia
 * @Date: 2022/7/9
 */
public class MainHttpWrapper {
    public static final String register = "user/register";
    public static final String login = "user/login";
    public static final String logout = "user/logout/json";

    /**
     *  登录
     * @param name
     * @param pwd
     * @param <T>
     * @return
     */
    public static<T> Observable<T> toLogin(String name, String pwd, Class<T> t){
       return RxHttp.postForm(login)
                .add("username",name)
                .add("password",pwd)
                .asResponse(t);
    }
    /**
     *  注册
     * @param name
     * @param pwd
     * @param <T>
     * @return
     */
    public static<T> Observable<T> toRegister(String name, String pwd, Class<T> t){
        return RxHttp.postForm(register)
                .add("username",name)
                .add("repassword",pwd)
                .add("password",pwd)
                .asResponse(t);
    }
}
