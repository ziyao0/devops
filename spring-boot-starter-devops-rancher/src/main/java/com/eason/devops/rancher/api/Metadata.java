package com.eason.devops.rancher.api;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author zhangziyao
 * @date 2021/10/18
 */
@lombok.Data
public class Metadata {
    private Boolean hostIPC = false;
    private String projectId;
    private Boolean hostNetwork = false;
    private Boolean hostPID = false;
    private Boolean paused = false;
    private String type = "workload";
    private String namespaceId;
    private Integer scale = 1;
    private String dnsPolicy = "ClusterFirst";
    private String restartPolicy = "Always";
    private Labels labels = new Labels();
    private List<Containers> containers = Lists.newArrayList(new Containers());
    private Scheduling scheduling = new Scheduling();
    private DeploymentConfig deploymentConfig = new DeploymentConfig();
    private String name;
    private Annotations annotations = new Annotations();
    private List<?> volumes = Lists.newArrayList();
}
