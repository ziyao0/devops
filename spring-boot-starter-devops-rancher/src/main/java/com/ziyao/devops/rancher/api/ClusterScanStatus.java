package com.ziyao.devops.rancher.api;

import com.ziyao.devops.rancher.base.AbstractType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClusterScanStatus extends AbstractType {

    private CisScanStatus cisScanStatus;

    private List<ClusterScanCondition> conditions;

}
