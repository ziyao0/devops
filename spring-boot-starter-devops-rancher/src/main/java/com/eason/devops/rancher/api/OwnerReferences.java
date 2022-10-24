package com.eason.devops.rancher.api;

import lombok.Data;

/**
 * @author zhangziyao
 * @date 2021/10/18
 */
@Data
public class OwnerReferences {
    private String apiVersion;
    private Boolean blockOwnerDeletion;
    private Boolean controller;
    private String kind;
    private String name;
    private String type;
    private String uid;
}
