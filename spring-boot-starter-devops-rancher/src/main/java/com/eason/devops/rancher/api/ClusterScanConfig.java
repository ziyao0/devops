package com.eason.devops.rancher.api;

import com.eason.devops.rancher.base.AbstractType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClusterScanConfig extends AbstractType {

    private CisScanConfig cisScanConfig;
}
