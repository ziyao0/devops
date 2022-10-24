package com.eason.devops.rancher.api;

import com.eason.devops.rancher.base.AbstractType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContainerResourceLimit extends AbstractType {

    private String limitsCpu;

    private String limitsMemory;

    private String requestsCpu;

    private String requestsMemory;
}
