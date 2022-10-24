package com.eason.devops.api;

import com.eason.devops.rancher.api.Metadata;
import com.eason.devops.rancher.api.Service;

import java.io.IOException;

/**
 * @author Eason
 * @date 2022/10/24
 */
public interface RancherService {

    /**
     * 获取服务详情
     *
     * @param clusterCode 集群ID
     * @param namespace   命名空间ID
     * @param instance    实例ID
     * @return 返回服务详情 {@see Service}
     * @throws IOException 异常信息
     */
    default Metadata selfService(String clusterCode, String namespace, String instance) throws IOException {
        return null;
    }

    /**
     * 创建服务
     *
     * @param service  基础服务参数 {@link com.eason.devops.rancher.api.Service}
     * @param instance 实例ID
     * @param image    镜像
     * @return 返回 {@link Boolean#TRUE} 创建成功
     * @throws IOException 异常信息
     */
    default boolean createService(Service service, String instance, String image) throws IOException {
        return false;
    }

    /**
     * 创建服务
     *
     * @param metadata 基础服务参数 {@link Service}
     * @param instance 实例ID
     * @param image    镜像
     * @return 返回 {@link Boolean#TRUE} 创建成功
     * @throws IOException 异常信息
     */
    default boolean createService(Metadata metadata, String instance, String image) throws IOException {
        return false;
    }

    /**
     * 创建服务
     *
     * @param clusterCode 集群ID
     * @param namespace   命名空间ID
     * @param instance    实例ID
     * @param image       镜像
     * @param javaArgs    参数
     * @return 返回 {@link Boolean#TRUE} 创建成功
     * @throws IOException 异常信息
     */
    default boolean createService(String clusterCode, String namespace, String instance, String image, String javaArgs) throws IOException {
        return false;
    }

    /**
     * 创建服务
     *
     * @param clusterCode 集群ID
     * @param namespace   命名空间ID
     * @param instance    实例ID
     * @param image       镜像
     * @param javaArgs    参数
     * @return 返回 {@link Boolean#TRUE} 创建成功
     * @throws IOException 异常信息
     */
    default boolean createService(String clusterCode, String namespace, String instance,
                                  String image, String javaArgs, String javaOpts) throws IOException {
        return false;
    }

    /**
     * 修改服务
     *
     * @param clusterCode 集群ID
     * @param namespace   命名空间ID
     * @param instance    实例ID
     * @param image       镜像
     * @param javaArgs    参数
     * @return 返回 {@link Boolean#TRUE} 创建成功
     * @throws IOException 异常信息
     */
    default boolean updateService(String clusterCode, String namespace, String instance,
                                  String image, String javaArgs, String javaOpts) throws IOException {
        return false;
    }

    /**
     * 修改服务
     *
     * @param clusterCode 集群ID
     * @param namespace   命名空间ID
     * @param instance    实例ID
     * @param image       镜像
     * @return 返回 {@link Boolean#TRUE} 创建成功
     * @throws IOException 异常信息
     */
    default boolean updateService(String clusterCode, String namespace, String instance, String image) throws IOException {
        return false;
    }

    /**
     * 重新发布
     *
     * @param clusterCode 集群ID
     * @param namespace   命名空间
     * @param instance    实例名称
     * @return 返回发布结果
     * @throws IOException 异常信息
     */
    default boolean redeployService(String clusterCode, String namespace, String instance) throws IOException {
        return false;
    }

    /**
     * 删除 rancher 服务
     *
     * @param clusterCode 集群ID
     * @param namespace   命名空间ID
     * @param instance    实例ID
     * @return 返回 {@link Boolean#TRUE} 删除成功
     * @throws IOException 异常
     */
    default boolean removeService(String clusterCode, String namespace, String instance) throws IOException {
        return false;
    }

    /**
     * 扩缩 pod
     *
     * @param clusterCode 集群ID
     * @param namespace   命名空间ID
     * @param instance    实例ID
     * @param scale       pod 数量
     * @return 返回 {@link Boolean#TRUE} 操作成功
     * @throws IOException 异常
     */
    default boolean changeCapacity(String clusterCode, String namespace, String instance, Integer scale) throws IOException {
        return false;
    }

}
