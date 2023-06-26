package com.ziyao.devops.core;

import com.alibaba.fastjson.JSON;
import com.ziyao.devops.api.RancherService;
import com.ziyao.devops.rancher.api.Containers;
import com.ziyao.devops.rancher.api.Metadata;
import com.ziyao.devops.rancher.api.Service;
import com.ziyao.devops.rancher.api.Service2;
import com.ziyao.devops.rancher.core.ServiceService;
import com.ziyao.devops.rancher.service.Rancher;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import okhttp3.ResponseBody;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import retrofit2.Response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @author ziyao zhang
 * @since 2022/10/24
 */
public class DefaultRancherService implements RancherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRancherService.class);
    /**
     * deployment {@link com.ziyao.devops.rancher.api.Service.LabelsDTO#get_$WorkloadSelector}
     */

    private final Rancher rancher;

    public DefaultRancherService(Rancher rancher) {
        this.rancher = rancher;
    }

    /**
     * 获取服务详情
     *
     * @param clusterCode 集群ID
     * @param namespace   命名空间ID
     * @param instance    实例ID
     * @return 返回服务详情 {@see Service}
     * @throws IOException 异常信息
     */
    public Metadata selfService(String clusterCode, String namespace, String instance) throws IOException {
        Response<ResponseBody> response = rancher.type(ServiceService.class).revisions(clusterCode, namespace, instance).execute();
        if (response.isSuccessful()) {
            assert response.body() != null;
            List<Metadata> metadataList = JSON.parseObject(IOUtils.toString(response.body().byteStream(), StandardCharsets.UTF_8), Service2.class).getData();
            LOGGER.info("rancher--selfService:{}", instance);
            return metadataList.get(metadataList.size() - 1);
        }
        throw new NullPointerException("RancherApi#selfService(" + clusterCode + ",namespace," + instance + ") get service is null");
    }

    /**
     * 创建服务
     *
     * @param service  基础服务参数 {@link com.ziyao.devops.rancher.api.Service}
     * @param instance 实例ID
     * @param image    镜像
     * @return 返回 {@link Boolean#TRUE} 创建成功
     * @throws IOException 异常信息
     */
    public boolean createService(Service service, String instance, String image) throws IOException {
        // 构建 Service
        Service.ContainersDTO containersDTO = service.getContainers().get(0);
        containersDTO.setImage(image);
        containersDTO.setName(instance);
        service.setContainers(Lists.newArrayList(containersDTO));
        service.setName(instance);
        // call rancher
        LOGGER.info("rancher--create:{}", instance);
        return rancher.type(ServiceService.class).create(service.getProjectId(), JSON.toJSON(service)).execute().isSuccessful();
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
    public boolean createService(Metadata metadata, String instance, String image) throws IOException {
        Metadata metadata1 = new Metadata();
        // 构建 Service
        Containers containers = metadata.getContainers().get(0);
        containers.setImage(image);
        containers.setName(instance);
        metadata1.setContainers(Lists.newArrayList(containers));
        metadata1.setName(instance);
        metadata1.setNamespaceId(metadata.getNamespaceId());
        // call rancher
        LOGGER.info("rancher--create:{}", instance);
        return rancher.type(ServiceService.class).create(metadata.getProjectId(), JSON.toJSON(metadata1)).execute().isSuccessful();
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
    public boolean createService(String clusterCode, String namespace, String instance, String image, String javaArgs) throws IOException {
        // 构建 Service
        Object service = mergeService(namespace, instance, image, javaArgs, null);
        // call rancher
        LOGGER.info("rancher--create:{}", JSON.toJSONString(service));
        return rancher.type(ServiceService.class).create(clusterCode, JSON.toJSON(service)).execute().isSuccessful();
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
    public boolean createService(String clusterCode, String namespace, String instance,
                                 String image, String javaArgs, String javaOpts) throws IOException {
        // 构建 Service
        Object service = mergeService(namespace, instance, image, javaArgs, javaOpts);
        // call rancher
        LOGGER.info("rancher--create:{}", JSON.toJSONString(service));
        return rancher.type(ServiceService.class).create(clusterCode, JSON.toJSON(service)).execute().isSuccessful();
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
    public boolean updateService(String clusterCode, String namespace, String instance,
                                 String image, String javaArgs, String javaOpts) throws IOException {
        // 构建 Service
        Object service = mergeService(namespace, instance, image, javaArgs, javaOpts);
        // call rancher
        LOGGER.info("rancher--update:{}", JSON.toJSONString(service));
        return rancher.type(ServiceService.class).create(clusterCode, JSON.toJSON(service)).execute().isSuccessful();
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
    public boolean updateService(String clusterCode, String namespace, String instance, String image) throws IOException {
        // 构建 Service
        Metadata metadata = selfService(clusterCode, namespace, instance);
        // 填充镜像
        Containers containers = metadata.getContainers().get(0);
        containers.setImage(image);
        metadata.setContainers(Lists.newArrayList(containers));
        if (metadata.getScale() == null || metadata.getScale() == 0) {
            metadata.setScale(1);
        }
        LOGGER.info("rancher--update:{}", instance);
        // call rancher
        return rancher.type(ServiceService.class).update(
                metadata.getProjectId(), metadata.getNamespaceId(), instance, JSON.toJSON(metadata)).execute().isSuccessful();
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
    public boolean redeployService(String clusterCode, String namespace, String instance) throws IOException {
        LOGGER.info("rancher--redeploy:{},{},{}", clusterCode, namespace, instance);
        return rancher.type(ServiceService.class)
                .redeploy(clusterCode, namespace, instance).execute().isSuccessful();
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
    public boolean removeService(String clusterCode, String namespace, String instance) throws IOException {
        // call rancher
        LOGGER.info("removeService：{}，{}，{}", clusterCode, namespace, instance);
        return rancher.type(ServiceService.class).delete(clusterCode, namespace, instance).execute().isSuccessful();
    }

    /**
     * 扩缩 pod
     *
     * @param clusterCode 集群ID
     * @param namespace   命名空间ID
     * @param instance    实例ID
     * @param scale       pod 数量
     * @return 返回 {@link Boolean#TRUE} 删除成功
     * @throws IOException 异常
     */
    public boolean changeCapacity(String clusterCode, String namespace, String instance, Integer scale) throws IOException {
        Map<String, Integer> scaleMap = Maps.newHashMap();
        scaleMap.put("scale", scale);
        // call rancher
        return rancher.type(ServiceService.class)
                .updateCapacity(clusterCode, namespace, instance, scaleMap).execute().isSuccessful();
    }

    /**
     * 构建Service参数
     *
     * @param instance  实例ID
     * @param image     镜像
     * @param namespace 命名空间ID
     * @param javaArgs  参数
     * @return 返回 {@link Service} 的JSONObject
     */
    private Object mergeService(String namespace, String instance, String image, String javaArgs, String javaOpts) {
        Service service = new Service();
        service.setName(instance);
        Service.ContainersDTO containersDTO = new Service.ContainersDTO();
        containersDTO.setName(instance);
        containersDTO.setImage(image);
        containersDTO.setNamespaceId(namespace);
        Service.ContainersDTO.EnvironmentDTO environmentDTO = new Service.ContainersDTO.EnvironmentDTO();
        environmentDTO.setJavaArgs(javaArgs);
        if (!StringUtils.isEmpty(javaOpts)) {
            environmentDTO.setJavaOpts(javaOpts);
        }
        containersDTO.setEnvironment(environmentDTO);
        service.setContainers(Lists.newArrayList(containersDTO));
        return JSON.toJSON(service);
    }

    public Rancher getRancher() {
        return rancher;
    }
}
