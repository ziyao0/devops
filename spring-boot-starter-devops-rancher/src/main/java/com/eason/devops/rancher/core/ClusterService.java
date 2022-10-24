package com.eason.devops.rancher.core;

import com.eason.devops.rancher.api.Cluster;
import com.eason.devops.rancher.base.Filters;
import com.eason.devops.rancher.base.TypeCollection;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @author zhangziyao
 * @date 2021/10/13
 */
public interface ClusterService {

    @GET("clusters")
    Call<TypeCollection<Cluster>> list();

    @GET("clusters")
    Call<TypeCollection<Cluster>> list(@QueryMap Filters filters);

    @GET("clusters/{id}")
    Call<Cluster> get(@Path("id") String id);

    @POST("clusters")
    Call<Cluster> create(@Body Cluster cluster);

    @PUT("clusters/{id}")
    Call<Cluster> update(@Path("id") String id, @Body Cluster cluster);

    @DELETE("clusters/{id}")
    Call<ResponseBody> delete(@Path("id") String id);

}
