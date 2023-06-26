package com.ziyao.devops.rancher.core;

import com.ziyao.devops.rancher.api.AppCondition;
import com.ziyao.devops.rancher.base.Filters;
import com.ziyao.devops.rancher.base.TypeCollection;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @author zhangziyao
 * @since 2021/10/13
 */
public interface AppConditionService {

    @GET("appCondition")
    Call<TypeCollection<AppCondition>> list();

    @GET("appCondition")
    Call<TypeCollection<AppCondition>> list(@QueryMap Filters filters);

    @GET("appCondition/{id}")
    Call<AppCondition> get(@Path("id") String id);

    @POST("appCondition")
    Call<AppCondition> create(@Body AppCondition appCondition);

    @PUT("appCondition/{id}")
    Call<AppCondition> update(@Path("id") String id, @Body AppCondition appCondition);

    @DELETE("appCondition/{id}")
    Call<ResponseBody> delete(@Path("id") String id);

}
