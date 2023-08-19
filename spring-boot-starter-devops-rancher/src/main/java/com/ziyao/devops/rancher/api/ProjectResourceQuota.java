package com.ziyao.devops.rancher.api;


import com.ziyao.devops.rancher.base.AbstractType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProjectResourceQuota extends AbstractType {

    private ResourceQuotaLimit limit;

    private ResourceQuotaLimit usedLimit;

}
