package com.ziyao.devops.rancher.core;

import com.ziyao.devops.rancher.api.Info;
import com.ziyao.devops.rancher.base.Filters;
import com.ziyao.devops.rancher.base.TypeCollection;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @author zhangziyao
 * @since 2021/10/13
 */
public interface InfoService {

    @GET("info")
    Call<TypeCollection<Info>> list();

    @GET("info")
    Call<TypeCollection<Info>> list(@QueryMap Filters filters);

    @GET("info/{id}")
    Call<Info> get(@Path("id") String id);

    @POST("info")
    Call<Info> create(@Body Info info);

    @PUT("info/{id}")
    Call<Info> update(@Path("id") String id, @Body Info info);

    @DELETE("info/{id}")
    Call<ResponseBody> delete(@Path("id") String id);

}
