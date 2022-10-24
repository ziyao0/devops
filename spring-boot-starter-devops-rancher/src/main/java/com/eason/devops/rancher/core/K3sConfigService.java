package com.eason.devops.rancher.core;

import com.eason.devops.rancher.api.K3sConfig;
import com.eason.devops.rancher.base.Filters;
import com.eason.devops.rancher.base.TypeCollection;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @author zhangziyao
 * @date 2021/10/13
 */
public interface K3sConfigService {

    @GET("k3sConfig")
    Call<TypeCollection<K3sConfig>> list();

    @GET("k3sConfig")
    Call<TypeCollection<K3sConfig>> list(@QueryMap Filters filters);

    @GET("k3sConfig/{id}")
    Call<K3sConfig> get(@Path("id") String id);

    @POST("k3sConfig")
    Call<K3sConfig> create(@Body K3sConfig k3sConfig);

    @PUT("k3sConfig/{id}")
    Call<K3sConfig> update(@Path("id") String id, @Body K3sConfig k3sConfig);

    @DELETE("k3sConfig/{id}")
    Call<ResponseBody> delete(@Path("id") String id);

}
