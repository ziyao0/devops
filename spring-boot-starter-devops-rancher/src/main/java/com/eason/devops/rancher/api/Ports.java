package com.eason.devops.rancher.api;

import lombok.Data;

/**
 * @author zhangziyao
 * @date 2021/10/18
 */
@Data
public class Ports {
    private Integer containerPort;
    private String dnsName;
    private Integer hostPort;
    private String kind;
    private String name;
    private String protocol;
    private Integer sourcePort;
    private String type;
}
