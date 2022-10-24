package com.eason.devops.rancher.core;

import com.eason.devops.rancher.api.Action;
import com.eason.devops.rancher.base.Filters;
import com.eason.devops.rancher.base.TypeCollection;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @author zhangziyao
 * @date 2021/10/13
 */
public interface ActionService {

    @GET("action")
    Call<TypeCollection<Action>> list();

    @GET("action")
    Call<TypeCollection<Action>> list(@QueryMap Filters filters);

    @GET("action/{id}")
    Call<Action> get(@Path("id") String id);

    @POST("action")
    Call<Action> create(@Body Action action);

    @PUT("action/{id}")
    Call<Action> update(@Path("id") String id, @Body Action action);

    @DELETE("action/{id}")
    Call<ResponseBody> delete(@Path("id") String id);

}
