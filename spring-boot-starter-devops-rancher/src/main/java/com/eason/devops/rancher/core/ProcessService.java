package com.eason.devops.rancher.core;

import com.eason.devops.rancher.base.Filters;
import com.eason.devops.rancher.base.TypeCollection;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @author zhangziyao
 * @date 2021/10/13
 */
public interface ProcessService {

    @GET("process")
    Call<TypeCollection<Process>> list();

    @GET("process")
    Call<TypeCollection<Process>> list(@QueryMap Filters filters);

    @GET("process/{id}")
    Call<Process> get(@Path("id") String id);

    @POST("process")
    Call<Process> create(@Body Process process);

    @PUT("process/{id}")
    Call<Process> update(@Path("id") String id, @Body Process process);

    @DELETE("process/{id}")
    Call<ResponseBody> delete(@Path("id") String id);

}
