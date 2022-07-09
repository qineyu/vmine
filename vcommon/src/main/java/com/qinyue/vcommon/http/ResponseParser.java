package com.qinyue.vcommon.http;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;
import rxhttp.wrapper.annotation.Parser;
import rxhttp.wrapper.entity.ParameterizedTypeImpl;
import rxhttp.wrapper.exception.ParseException;
import rxhttp.wrapper.parse.TypeParser;
import rxhttp.wrapper.utils.Converter;

/**
 * @类名 ResponseParser
 * @作者 bodia
 * @时间 11/9/21 2:33 PM
 * @描述 自定义网络数据解析器 并且已经自定义了错误现实内容
 */
@Parser(name = "Response",wrappers = {List.class, PageList.class})
public class ResponseParser<T> extends TypeParser<T> {
    protected ResponseParser(){
        super();
    };
    public ResponseParser(Type type){
        super(type);
    }
    @Override
    public T onParse(@NotNull Response response) throws IOException {
        com.qinyue.vcommon.http.Response<T> data = Converter.convertTo(response, com.qinyue.vcommon.http.Response.class, types);
//        if (data.getCode()==ReturnCode.TOKEN_ERROR){
//            XUtil.logout();
//            XUtil.getActivityLifecycleHelper().finishAllActivity();
//            ARounterWrapper.toLoginPhoneActivity(false,0);
//        }
        T t = data.getData(); //获取data字段
        if ((t == null||"{}".equals(t)) && types[0] == String.class) {
            /*
             * 考虑到有些时候服务端会返回：{"errorCode":0,"errorMsg":"关注成功"}  类似没有data的数据
             * 此时code正确，但是data字段为空，直接返回data的话，会报空指针错误，
             * 所以，判断泛型为String类型时，重新赋值，并确保赋值不为null
             */
            t = (T) data.getErrorMsg();
        }else if (t == null){
            try {
                if (  ((ParameterizedTypeImpl)types[0]).getRawType()==List.class){
                    t = (T)new ArrayList<>();
                }else if (((ParameterizedTypeImpl)types[0]).getRawType()== PageList.class){
                    t = (T)new PageList<T>();
                }
            }catch (Exception e){
                throw new ParseException(String.valueOf(data.getErrorCode()), ReturnCode.getErrorMsg(data.getErrorCode(),data.getErrorMsg()), response);
            }
        }
//        //还需要判断一次当前请求是不是直播间请求
        if ((!ReturnCode.isSuccess(data.getErrorCode()))&&response.request()!=null) {//code不等于200，说明数据不正确，抛出异常
            throw new ParseException(String.valueOf(data.getErrorCode()), ReturnCode.getErrorMsg(data.getErrorCode(),data.getErrorMsg()), response);
        }
        return t;
    }
}
