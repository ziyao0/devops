package com.ziyao.devops.rancher.api;

import com.ziyao.devops.rancher.base.AbstractType;
import com.ziyao.devops.rancher.ext.DnsLabel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class Cluster extends AbstractType {

    private Map<String, Object> agentFeatures;

    private String agentImage;

    private String agentImageOverride;

    private Map<String, Object> allocatable;

    private Map<String, Object> annotations;


    private String apiEndpoint;

    private Boolean appliedEnableNetworkPolicy;

    private String appliedPodSecurityPolicyTemplateId;


    private String authImage;

    private String caCert;

    private Map<String, Object> capacity;

    private Map<String, Object> certificatesExpiration;

    private String clusterTemplateId;

    private String clusterTemplateRevisionId;

    private String created;

    private String creatorId;

    private String currentCisRunName;

    private String defaultClusterRoleForProjectMembers;

    private String defaultPodSecurityPolicyTemplateId;

    private String description;

    private String desiredAgentImage;

    private String desiredAuthImage;

    private String dockerRootDir;

    private String driver;

    private Boolean enableClusterAlerting;

    private Boolean enableClusterMonitoring;

    private Boolean enableNetworkPolicy;


    private ImportedConfig importedConfig;

    private Boolean internal;

    private Boolean istioEnabled;

    private K3sConfig k3sConfig;

    private Map<String, Object> labels;

    private Map<String, Object> limits;

    private DnsLabel name;

    private Integer nodeCount;

    private Integer nodeVersion;

    private String removed;

    private Map<String, Object> requested;

    private String state;

    private String transitioning;

    private String transitioningMessage;

    private String uuid;

    private Info version;

    private Boolean windowsPreferedCluster;
}
