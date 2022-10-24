package com.eason.devops.rancher.api;


import com.eason.devops.rancher.base.AbstractType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceQuotaLimit extends AbstractType {

    private String configMaps;

    private String limitsCpu;

    private String limitsMemory;

    private String persistentVolumeClaims;

    private String pods;

    private String replicationControllers;

    private String requestsCpu;

    private String requestsMemory;

    private String requestsStorage;

    private String secrets;

    private String services;

    private String servicesLoadBalancers;

    private String servicesNodePorts;

}
