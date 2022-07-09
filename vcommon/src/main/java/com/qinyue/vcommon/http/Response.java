package com.qinyue.vcommon.http;

/**
 * @类名 Response
 * @作者 bodia
 * @时间 11/9/21 10:31 AM
 * @描述 网络请求数据response
 */
public class Response<T>{
    private int code;
    private String desc;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return desc;
    }

    public void setMsg(String msg) {
        this.desc = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", msg='" + desc + '\'' +
                ", data=" + data +
                '}';
    }
}
