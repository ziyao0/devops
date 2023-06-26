package com.ziyao.devops.rancher.api;

import com.ziyao.devops.rancher.base.AbstractType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AppCondition extends AbstractType {

    private String lastTransitionTime;

    private String lastUpdateTime;

    private String message;

    private String reason;

    private String status;

    private String type;
}
