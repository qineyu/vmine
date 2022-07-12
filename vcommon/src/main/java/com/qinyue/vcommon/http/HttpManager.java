package com.qinyue.vcommon.http;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import com.qinyue.vcommon.utils.AppUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import rxhttp.RxHttpPlugins;
import rxhttp.wrapper.cahce.CacheMode;
import rxhttp.wrapper.callback.Function;
import rxhttp.wrapper.param.Method;
import rxhttp.wrapper.param.Param;
import rxhttp.wrapper.ssl.HttpsUtils;

/**
 * 创建人:qinyue
 * 创建日期:2022/7/9
 * 描述:网络请求管理工具
 **/
public class HttpManager {
    /**
     * 连接超时时间(秒)
     */
    private final long CONNECT_TIME_OUT = 2 * 60;
    /**
     * 读取超时时间(秒)
     */
    private final long REED_TIME_OUT = 2 * 60;
    /**
     * 写入超时时间(秒)
     */
    private final long WRITE_TIME_OUT = 2 * 60;
    /**
     * 缓存文件大小
     */
    private final long CACHE_SIZE = 10 * 1024 * 1024;
    /**
     * 缓存时间(毫秒)
     */
    private final long CACHE_TIME = 30 * 60 * 1000;

    private static volatile HttpManager httpManager;

    public static HttpManager getInstance() {
        if (httpManager == null) {
            synchronized (HttpManager.class) {
                if (httpManager == null) {
                    httpManager = new HttpManager();
                }
            }
        }
        return httpManager;
    }

    /**
     * 初始化http
     */
    public void initHttp(Context context, boolean isDebug) {
        //设置debug模式，默认为false，设置为true后，发请求，过滤"RxHttp"能看到请求日志

        //设置缓存目录为：Android/data/{app包名目录}/cache/RxHttpCache
        File cacheDir = new File(context.getExternalCacheDir(), "RxHttpCache");
        RxHttpPlugins.init(getHttpClient())      //自定义OkHttpClient对象
                .setDebug(isDebug)                //是否开启调试模式，开启后，logcat过滤RxHttp，即可看到整个请求流程日志
                .setCache(cacheDir, CACHE_SIZE, CacheMode.ONLY_NETWORK, CACHE_TIME)  //配置缓存目录，最大size及缓存模式
                .setExcludeCacheKeys("currentTime","login","sendSms") ;  //设置一些key，不参与cacheKey的组拼
//                .setOnParamAssembly(new Function<Param<?>, Param<?>>() {
//                    @Override
//                    public Param<?> apply(Param<?> p) throws Exception {
//                        //主线程执行
//                        Method method = p.getMethod();
//                        if (method.isGet()) {     //可根据请求类型添加不同的参数
//                        } else if (method.isPost()) {
//                        }
//                        return p.addHeader("platform", "Android," + Build.VERSION.RELEASE)//添加公共参数
//                                .addHeader("id", AppUtils.getPackageName())
//                                .addHeader("version", AppUtils.getAppVersionName())
//                                .addHeader("buildtime", AppUtils.getBuildTime(context))
//                                .addHeader("channel", TextUtils.isEmpty(AppUtils.getChannel(context))?"appStore":AppUtils.getChannel(context)); //添加公共请求头
//                    }
//                });
//                .setResultDecoder(Function)       //设置数据解密/解码器，非必须
//                .setConverter(IConverter)         //设置全局的转换器，非必须
//                .setOnParamAssembly(Function);    //设置公共参数/请求头回调
    }

    /**
     * 获取http连接配置
     *
     * @return
     */
    public OkHttpClient getHttpClient() {
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory();
        return new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(REED_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager) //添加信任证书
                .hostnameVerifier((hostname, session) -> true) //忽略host验证
                .build();
    }

}
