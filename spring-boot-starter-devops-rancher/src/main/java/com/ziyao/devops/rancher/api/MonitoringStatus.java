package com.ziyao.devops.rancher.api;

import com.ziyao.devops.rancher.base.AbstractType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class MonitoringStatus extends AbstractType {

    private List<MonitoringCondition> conditions;

    private String grafanaEndpoint;
}
