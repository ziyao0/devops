package com.eason.devops.rancher.api;

import lombok.Data;

/**
 * @author zhangziyao
 * @date 2021/10/18
 */
@Data
public class ReplicaSetStatus {
    private Integer availableReplicas;
    private Integer observedGeneration;
    private Integer fullyLabeledReplicas;
    private Integer readyReplicas;
    private Integer replicas;
    private String type;
}
