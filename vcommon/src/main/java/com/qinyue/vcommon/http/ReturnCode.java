package com.qinyue.vcommon.http;

/**
 * @类名 ReturnCode
 * @作者 bodia
 * @时间 11/9/21 3:03 PM
 * @描述 网络请求返回码
 */
public class ReturnCode {
    /**
     * 正常返回code
     */
    public static final int SUCCESS = 200;
    /**
     * 服务器错误
     */
    public static final int SERVICE_ERROR = 500;
    /**
     * 请求错误
     */
    public static final int REQUEST_ERROR = 2002;
    /**
     * 验证码错误
     */
    public static final int VSCODE_ERROR = 2003;
    /**
     * 参数错误
     */
    public static final int REQUEST_PARAMETER = 0;
    /**
     * 用户token失效
     */
    public static final int TOKEN_ERROR = 5000;

    /**
     * 获取错误提示信息
     * @param code  错误码
     * @return  返回内容
     */
    public static String getErrorMsg(int code,String msg) {
        switch (code) {
            case SUCCESS:
                return "成功";
            case SERVICE_ERROR:
                return msg;
            case REQUEST_ERROR:
                return msg;
            case VSCODE_ERROR:
                return msg;
            case TOKEN_ERROR:
                return "token失效,请重新登录";
            case REQUEST_PARAMETER:
                return "参数错误";
            default:
                return msg;
        }
    }

    /**
     * 是否请求成功
     * @param code  返回码
     * @return true成功 false失败
     */
    public static boolean isSuccess(int code){
        return code==SUCCESS;
    }
}
