package com.ziyao.devops.rancher.api;

import com.ziyao.devops.rancher.base.AbstractType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class Project extends AbstractType {

    private Map<String, Object> annotations;

    private String clusterId;

    private List<ProjectCondition> conditions;

    private ContainerResourceLimit containerDefaultResourceLimit;

    private String created;

    private String creatorId;

    private String description;

    private Boolean enableProjectMonitoring;

    private Map<String, Object> labels;

    private MonitoringStatus monitoringStatus;

    private String name;

    private NamespaceResourceQuota namespaceDefaultResourceQuota;

    private String namespaceId;

    private List<OwnerReference> ownerReferences;

    private String podSecurityPolicyTemplateId;

    private String removed;

    private ProjectResourceQuota resourceQuota;

    private String state;

    private String transitioning;

    private String transitioningMessage;

    private String uuid;
}
