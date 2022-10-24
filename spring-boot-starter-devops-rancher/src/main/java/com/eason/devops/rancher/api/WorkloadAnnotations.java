package com.eason.devops.rancher.api;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author zhangziyao
 * @date 2021/10/18
 */
@Data
public class WorkloadAnnotations {
    @JSONField(name = "deployment.kubernetes.io/desired-replicas")
    private String _$DeploymentKubernetesIoDesiredreplicas257;
    @JSONField(name = "deployment.kubernetes.io/max-replicas")
    private String _$DeploymentKubernetesIoMaxreplicas20;
    @JSONField(name = "deployment.kubernetes.io/revision")
    private String _$DeploymentKubernetesIoRevision330;
    @JSONField(name = "field.cattle.io/creatorId")
    private String _$FieldCattleIoCreatorId153;
}
