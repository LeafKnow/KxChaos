package com.yq.networke.manager;

import android.support.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.yq.base.app.KitBaseApp;
import com.yq.base.common.string.StringUtils;
import com.yq.networke.manager.url.RetrofitUrlManager;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * ClassName: RetrofitManager<p>
 * Fuction: Retrofit请求管理类<p>
 * CreateDate:2016/2/13 20:34<p>
 * UpdateUser:<p>
 * UpdateDate:<p>
 */
public enum RetrofitManager {
    INSTANCE;
    //设缓存有效期为两天
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置，头部Cache-Control设为maxd-age=0时则不会使用缓存而请求服务器
    private static final String CACHE_CONTROL_NETWORK = "max-age=0";

    private static volatile OkHttpClient mOkHttpClient;
//    public static final String baseUrl = "http://api.chetubao.com/ACarAdvertistingApi/index.php/";
//    public static final String baseUrl = "http://42.159.145.140/eecoreApi/api/index/service/";
    public static final String baseUrl = "http://api.chetubao.com/api/index/service/";

    private Retrofit mRetrofit;
    public Retrofit net() {
        if (mRetrofit == null) {
            initOkHttpClient();
            mRetrofit = new Retrofit.Builder().baseUrl(baseUrl).client(mOkHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
    // 配置OkHttpClient
    private void initOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                if (mOkHttpClient == null) {
                    //   LogUtils.e("初始化mOkHttpClient");
                    // 因为BaseUrl不同所以这里Retrofit不为静态，但是OkHttpClient配置是一样的,静态创建一次即可
                    // 指定缓存路径,缓存大小100Mb
                    Cache cache = new Cache(new File(KitBaseApp.getContext().getCacheDir(), "HttpCache"),
                            1024 * 1024 * 100);
                    OkHttpClient.Builder okHttpBuilder = RetrofitUrlManager.getInstance().with(new OkHttpClient.Builder()); //RetrofitUrlManager 初始化;
//                    okHttpBuilder.addNetworkInterceptor(new StethoInterceptor());// 浏览器查看网络请求拦截器
//                    okHttpBuilder.addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//                        @Override
//                        public void log(String message) {
//                            LogUtils.i("OkHttp", message);
//                        }
//                    }).setLevel(HttpLoggingInterceptor.Level.BODY));//网络和日志拦截
                    okHttpBuilder
                            .cache(cache)
                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)//网络读写缓存拦截器
                            .addInterceptor(mTokenInterceptor)//token拦截器
                            .addInterceptor(mRewriteCacheControlInterceptor)//添加读写缓存拦截器
                            .addInterceptor(mLoggingInterceptor)// 设置请求消息拦截器
                            .retryOnConnectionFailure(true)//尝试进行重新连接
                            .connectTimeout(OkHttpHandle.TIME_OUT, TimeUnit.SECONDS)// 设置连接时间
                            .readTimeout(OkHttpHandle.TIME_OUT, TimeUnit.SECONDS)//设置读写时间
                            .writeTimeout(OkHttpHandle.TIME_OUT, TimeUnit.SECONDS);//设置请求超时
//                            .cookieJar(new CookiesManager());//cookie自动管理类
                    mOkHttpClient = okHttpBuilder.build();

                }

            }
        }
    }

    private Interceptor mTokenInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            String authorization = getNativeToken();//获取本地的缓存token
            if (StringUtils.isEmpty(authorization)) {
                return chain.proceed(originalRequest);
            }
            Request authorised = originalRequest.newBuilder()
                    .header("token", authorization)
                    .build();
            return chain.proceed(authorised);
        }
    };

    /**
     * 后取本地存储的Token
     *
     * @return
     */
    public String getNativeToken() {
        String authorization = null;
//        if (SPUserInfo.getIsLogin(KitBaseApp.getContext())) {
//            if (!TextUtils.isEmpty(SPUserInfo.getToken(KitBaseApp.getContext()))) {
//                authorization = SPUserInfo.getToken(KitBaseApp.getContext());
//            }
//        }
        return authorization;
    }


    private String mCookies = "";
    // 云端响应头拦截器，用来配置缓存策略
    private Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkUtils.isConnected()) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                LogUtils.e("no network");
            }
            Response originalResponse = chain.proceed(request);

            mCookies = originalResponse.header("Set-Cookie");
            String authorization = getNativeToken();
            if (NetworkUtils.isConnected()) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                if (mCookies != null) {
                    return originalResponse.newBuilder()
                            .header("Cookie", mCookies)
                            .header("Cache-Control", cacheControl)
                            .header("token", getNativeToken())
                            .removeHeader("Pragma").build();
                } else {
                    if (!StringUtils.isEmpty(authorization)) {
                        return originalResponse.newBuilder()
                                .header("Cache-Control", cacheControl)
                                .header("token", authorization)
                                .removeHeader("Pragma").build();
                    } else {
                        return originalResponse.newBuilder()
                                .header("Cache-Control", cacheControl)
                                .removeHeader("Pragma").build();
                    }
                }
            } else {
                if (mCookies != null) {
                    return originalResponse.newBuilder()
                            .header("Cookie", mCookies)
                            .header("Cache-Control", "public, only-if-cached," + CACHE_STALE_SEC)
                            .header("token", authorization)
                            .removeHeader("Pragma").build();
                } else {
                    if (!StringUtils.isEmpty(authorization)) {
                        return originalResponse.newBuilder()
                                .header("Cache-Control", "public, only-if-cached," + CACHE_STALE_SEC)
                                .header("token", authorization)
                                .removeHeader("Pragma").build();
                    } else {
                        return originalResponse.newBuilder()
                                .header("Cache-Control", "public, only-if-cached," + CACHE_STALE_SEC)
                                .removeHeader("Pragma").build();
                    }
                }
            }
        }

    };

    // 打印返回的json数据拦截器
    public Interceptor mLoggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            final Request request = chain.request();

            final Response response = chain.proceed(request);
            LogUtils.i(request.url());
            final ResponseBody responseBody = response.body();
            final long contentLength = responseBody.contentLength();

            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(charset);
                } catch (UnsupportedCharsetException e) {
                    LogUtils.e("");
                    LogUtils.e("Couldn't decode the response body; charset is likely malformed.");
                    return response;
                }
            }

            if (contentLength != 0) {
                LogUtils.v("--------------------------------------------开始打印返回数据----------------------------------------------------");
                try {
                    LogUtils.json(buffer.clone().readString(charset));
                } catch (Exception e) {
                    LogUtils.e(e);
                }
                LogUtils.v("--------------------------------------------结束打印返回数据----------------------------------------------------");
            }
            //检测token失效的情况
//            if (response shows expired token){//根据和服务端的约定判断token过期
//
//                //取出本地的refreshToken
//                String refreshToken = "sssgr122222222";
//
//                // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求
//                ApiService service = ServiceManager.getService(ApiService.class);
//                Call<String> call = service.refreshToken(refreshToken);
//
//                //要用retrofit的同步方式
//                String newToken = call.execute().body();
//
//
//                // create a new request and modify it accordingly using the new token
//                Request newRequest = request.newBuilder().header("token", newToken)
//                        .build();
//
//                // retry the request
//
//                originalResponse.body().close();
//                return chain.proceed(newRequest);
//            }
            return response;
        }
    };

    /**
     * 根据网络状况获取缓存的策略
     *
     * @return
     */
    @NonNull
    public String getCacheControl() {
        return NetworkUtils.isConnected() ? CACHE_CONTROL_NETWORK : CACHE_CONTROL_CACHE;
    }


}
