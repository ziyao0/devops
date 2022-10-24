package com.eason.devops.rancher.api;

import com.eason.devops.rancher.base.AbstractType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CisScanStatus extends AbstractType {

    private Integer fail;

    private Integer notApplicable;

    private Integer pass;

    private Integer skip;

    private Integer total;
}
