package com.eason.devops.rancher.api;

import com.eason.devops.rancher.base.AbstractType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class KubeletService extends AbstractType {

    private String clusterDnsServer;

    private String clusterDomain;

    private Map<String, Object> extraArgs;

    private List<String> extraBinds;

    private List<String> extraEnv;

    private Boolean failSwapOn;

    private Boolean generateServingCertificate;

    private String image;

    private String infraContainerImage;
}
