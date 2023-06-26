package com.ziyao.devops.rancher.api;


import com.ziyao.devops.rancher.base.AbstractType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OwnerReference extends AbstractType {

    private String apiVersion;

    private Boolean blockOwnerDeletion;

    private Boolean controller;

    private String kind;

    private String name;

    private String uid;
}
