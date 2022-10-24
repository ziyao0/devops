package com.eason.devops.rancher.core;

import com.eason.devops.rancher.api.ClusterScan;
import com.eason.devops.rancher.base.Filters;
import com.eason.devops.rancher.base.TypeCollection;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @author zhangziyao
 * @date 2021/10/13
 */
public interface ClusterScanService {

    @GET("clusterScan")
    Call<TypeCollection<ClusterScan>> list();

    @GET("clusterScan")
    Call<TypeCollection<ClusterScan>> list(@QueryMap Filters filters);

    @GET("clusterScan/{id}")
    Call<ClusterScan> get(@Path("id") String id);

    @POST("clusterScan")
    Call<ClusterScan> create(@Body ClusterScan clusterScan);

    @PUT("clusterScan/{id}")
    Call<ClusterScan> update(@Path("id") String id, @Body ClusterScan clusterScan);

    @DELETE("clusterScan/{id}")
    Call<ResponseBody> delete(@Path("id") String id);

}
