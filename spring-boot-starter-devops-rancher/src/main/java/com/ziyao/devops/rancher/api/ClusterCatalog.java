package com.ziyao.devops.rancher.api;

import com.ziyao.devops.rancher.base.AbstractType;
import com.ziyao.devops.rancher.ext.DnsLabel;
import com.ziyao.devops.rancher.ext.Password;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClusterCatalog extends AbstractType {

    private Map<String, Object> annotations;

    private String branch;

    private String clusterId;

    private String commit;

    private String created;

    private String creatorId;

    private String description;

    private String helmVersion;

    private String kind;

    private Map<String, Object> labels;

    private String lastRefreshTimestamp;

    private DnsLabel name;

    private String namespaceId;

    private Password password;

    private String removed;

    private String state;

    private String transitioning;

    private String transitioningMessage;

    private String url;

    private String username;

    private String uuid;

}
