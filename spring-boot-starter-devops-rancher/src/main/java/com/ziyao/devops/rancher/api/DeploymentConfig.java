package com.ziyao.devops.rancher.api;

import lombok.Data;

/**
 * @author zhangziyao
 * @since 2021/10/18
 */
@Data
public class DeploymentConfig {
    private Integer minReadySeconds = 0;
    private String type = "deploymentConfig";
    private Integer revisionHistoryLimit = 10;
    private String strategy = "RollingUpdate";
    private Integer maxSurge = 1;
    private Integer maxUnavailable = 0;
}
