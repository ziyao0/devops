package com.eason.devops.rancher.core;

import com.eason.devops.rancher.api.KubeletService;
import com.eason.devops.rancher.base.Filters;
import com.eason.devops.rancher.base.TypeCollection;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @author zhangziyao
 * @date 2021/10/13
 */
public interface KubeletServiceService {

    @GET("kubeletService")
    Call<TypeCollection<KubeletService>> list();

    @GET("kubeletService")
    Call<TypeCollection<KubeletService>> list(@QueryMap Filters filters);

    @GET("kubeletService/{id}")
    Call<KubeletService> get(@Path("id") String id);

    @POST("kubeletService")
    Call<KubeletService> create(@Body KubeletService kubeletService);

    @PUT("kubeletService/{id}")
    Call<KubeletService> update(@Path("id") String id, @Body KubeletService kubeletService);

    @DELETE("kubeletService/{id}")
    Call<ResponseBody> delete(@Path("id") String id);

}
