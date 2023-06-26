package com.ziyao.devops.rancher.core;

import com.ziyao.devops.rancher.api.MemoryInfo;
import com.ziyao.devops.rancher.base.Filters;
import com.ziyao.devops.rancher.base.TypeCollection;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @author zhangziyao
 * @since 2021/10/13
 */
public interface MemoryInfoService {

    @GET("memoryInfo")
    Call<TypeCollection<MemoryInfo>> list();

    @GET("memoryInfo")
    Call<TypeCollection<MemoryInfo>> list(@QueryMap Filters filters);

    @GET("memoryInfo/{id}")
    Call<MemoryInfo> get(@Path("id") String id);

    @POST("memoryInfo")
    Call<MemoryInfo> create(@Body MemoryInfo memoryInfo);

    @PUT("memoryInfo/{id}")
    Call<MemoryInfo> update(@Path("id") String id, @Body MemoryInfo memoryInfo);

    @DELETE("memoryInfo/{id}")
    Call<ResponseBody> delete(@Path("id") String id);

}
