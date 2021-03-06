package com.yq.networke.api;

import com.yq.networke.bean.ResultBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.OPTIONS;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

import static com.yq.networke.api.Api.GITHUB_DOMAIN_NAME;
import static com.yq.networke.manager.url.RetrofitUrlManager.DOMAIN_NAME_HEADER;

/**
 * @Description: 提供的系列接口
 * @author: <a href="http://www.xiaoyaoyou1212.com">DAWI</a>
 * @date: 2016-12-30 16:42
 */
public interface ApiService {
    @Headers({DOMAIN_NAME_HEADER + GITHUB_DOMAIN_NAME}) //如果不需要多个 BaseUrl ,继续使用初始化时传入 Retrofit 中的默认 BaseUrl ,就不要加上 DOMAIN_NAME_HEADER 这个 Header
    @GET()
    Observable<ResultBean> get(@Url String url, @QueryMap Map<String, Object> maps);


    @Headers({DOMAIN_NAME_HEADER + GITHUB_DOMAIN_NAME})
    @FormUrlEncoded
    @POST()
    Observable<ResultBean> post(@Url() String url, @FieldMap Map<String, Object> maps);


    @Headers({DOMAIN_NAME_HEADER + GITHUB_DOMAIN_NAME})
    @FormUrlEncoded
    @POST()
    Observable<ResultBean> postForm(@Url() String url, @FieldMap Map<String, Object> maps);


    @Headers({DOMAIN_NAME_HEADER + GITHUB_DOMAIN_NAME})
    @POST()
    Observable<ResultBean> postBody(@Url() String url, @Body RequestBody requestBody);


    @Headers({DOMAIN_NAME_HEADER + GITHUB_DOMAIN_NAME})
    @HEAD()
    Observable<ResultBean> head(@Url String url, @QueryMap Map<String, Object> maps);


    @Headers({DOMAIN_NAME_HEADER + GITHUB_DOMAIN_NAME})
    @OPTIONS()
    Observable<ResultBean> options(@Url String url, @QueryMap Map<String, Object> maps);


    @Headers({DOMAIN_NAME_HEADER + GITHUB_DOMAIN_NAME})
    @FormUrlEncoded
    @PUT()
    Observable<ResponseBody> put(@Url() String url, @FieldMap Map<String, Object> maps);


    @Headers({DOMAIN_NAME_HEADER + GITHUB_DOMAIN_NAME})
    @FormUrlEncoded
    @PATCH()
    Observable<ResultBean> patch(@Url() String url, @FieldMap Map<String, Object> maps);


    @Headers({DOMAIN_NAME_HEADER + GITHUB_DOMAIN_NAME})
    @FormUrlEncoded
    @DELETE()
    Observable<ResultBean> delete(@Url() String url, @FieldMap Map<String, Object> maps);


    @Headers({DOMAIN_NAME_HEADER + GITHUB_DOMAIN_NAME})
    @Streaming
    @GET()
    Observable<ResultBean> downFile(@Url() String url, @QueryMap Map<String, Object> maps);


    @Headers({DOMAIN_NAME_HEADER + GITHUB_DOMAIN_NAME})
    @Multipart
    @POST()
    Observable<ResultBean> uploadFiles(@Url() String url, @Part() List<MultipartBody.Part> parts);

}
