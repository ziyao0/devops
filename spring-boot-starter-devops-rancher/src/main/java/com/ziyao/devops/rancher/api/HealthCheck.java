package com.ziyao.devops.rancher.api;

import com.ziyao.devops.rancher.base.AbstractType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HealthCheck extends AbstractType {

    private String url;

}
