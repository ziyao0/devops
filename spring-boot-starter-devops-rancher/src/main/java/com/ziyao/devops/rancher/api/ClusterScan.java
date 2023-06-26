package com.ziyao.devops.rancher.api;

import com.ziyao.devops.rancher.base.AbstractType;
import com.ziyao.devops.rancher.ext.DnsLabel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClusterScan extends AbstractType {

    private Map<String, Object> annotations;

    private String clusterId;

    private String created;

    private String creatorId;

    private Map<String, Object> labels;

    private DnsLabel name;

    private String namespaceId;

    private List<OwnerReference> ownerReferences;

    private String removed;

    private String runType;

    private ClusterScanConfig scanConfig;

    private String scanType;

    private String state;

    private ClusterScanStatus status;

    private String transitioning;

    private String transitioningMessage;

    private String uuid;

}
