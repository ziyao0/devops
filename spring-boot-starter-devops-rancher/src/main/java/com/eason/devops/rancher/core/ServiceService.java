package com.eason.devops.rancher.core;

import com.eason.devops.rancher.api.Metadata;
import com.eason.devops.rancher.api.Service;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.*;

import java.util.Map;

/**
 * rancher服务部署相关 API
 *
 * @author zhangziyao
 * @date 2021/10/13
 */
public interface ServiceService {

    /**
     * 查询rancher服务详情
     *
     * @param clusterCode 集群ID
     * @param namespace   命名空间ID
     * @param instance    实例名称
     * @return 返回服务详情 注意：返回的{@link Service#getLabels()}是没有值的 需要手动赋值
     * @see Service
     */
    @GET("project/{clusterCode}/services/{namespace}:{instance}")
    Call<Metadata> self(@Path("clusterCode") String clusterCode, @Path("namespace") String namespace, @Path("instance") String instance);

    /**
     * 用于调用rancher创建服务
     *
     * @param clusterCode 集群ID
     * @param args0       创建服务所需要的参数，具体格式查看{@link com.eason.devops.rancher.api.Service}
     * @return 返回 {@link Response#isSuccessful()} 为成功
     * @see com.eason.devops.rancher.api.Service
     */
    @POST("project/{clusterCode}/workload")
    Call<ResponseBody> create(@Path("clusterCode") String clusterCode, @Body Object args0);

    /**
     * 修改 rancher 服务
     *
     * @param clusterCode 集群ID
     * @param namespace   命名空间ID
     * @param instance    实例名称
     * @param args0       创建服务所需要的参数，具体格式查看{@link com.eason.devops.rancher.api.Service}
     * @return 返回 {@link Response#isSuccessful()} 为成功
     */
    @PUT("project/{clusterCode}/workloads/deployment:{namespace}:{instance}")
    Call<ResponseBody> update(@Path("clusterCode") String clusterCode, @Path("namespace") String namespace, @Path("instance") String instance, @Body Object args0);

    /**
     * 用于调用rancher删除服务
     *
     * @param clusterCode 集群ID
     * @param namespace   命名空间ID
     * @param instance    实例名称
     * @return 返回 {@link Response#isSuccessful()} 为成功
     */
    @DELETE("project/{clusterCode}/workloads/deployment:{namespace}:{instance}")
    Call<ResponseBody> delete(@Path("clusterCode") String clusterCode, @Path("namespace") String namespace, @Path("instance") String instance);

    /**
     * 重新部署
     *
     * @param clusterCode 集群ID
     * @param namespace   命名空间ID
     * @param instance    实例名称
     * @return 返回 {@link Response#isSuccessful()} 为成功
     */
    @POST("project/{clusterCode}/workloads/deployment:{namespace}:{instance}?action=redeploy")
    Call<ResponseBody> redeploy(@Path("clusterCode") String clusterCode, @Path("namespace") String namespace, @Path("instance") String instance);

    /**
     * 获取历史版本用于回滚
     *
     * @param clusterCode 集群ID
     * @param namespace   命名空间ID
     * @param instance    实例名称
     * @return 返回 {@link Response#isSuccessful()} 为成功
     */
    @GET("project/{clusterCode}/workloads/deployment:{namespace}:{instance}/revisions")
    Call<ResponseBody> revisions(@Path("clusterCode") String clusterCode, @Path("namespace") String namespace, @Path("instance") String instance);

    /**
     * 获取历史版本用于回滚
     *
     * @param clusterCode 集群ID
     * @param namespace   命名空间ID
     * @param instance    实例名称
     * @param args0       需要回滚的 replicaSetId
     * @return 返回 {@link Response#isSuccessful()} 为成功
     */
    @POST("project/{clusterCode}/workloads/deployment:{namespace}:{instance}?action=rollback")
    Call<ResponseBody> rollback(@Path("clusterCode") String clusterCode, @Path("namespace") String namespace, @Path("instance") String instance, @Body Map<String, String> args0);

    /**
     * rancher pods 扩容缩容
     *
     * @param clusterCode 集群ID
     * @param namespace   命名空间ID
     * @param instance    实例名称
     * @param args0       pods数量参数 {"scale":1}
     * @return 返回 {@link Response#isSuccessful()} 为成功
     */
    @PUT("project/{clusterCode}/workloads/deployment:{namespace}:{instance}")
    Call<ResponseBody> updateCapacity(@Path("clusterCode") String clusterCode, @Path("namespace") String namespace, @Path("instance") String instance, @Body Map<String, Integer> args0);
}
