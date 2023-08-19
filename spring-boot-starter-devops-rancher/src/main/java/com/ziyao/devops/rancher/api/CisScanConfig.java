package com.ziyao.devops.rancher.api;

import com.ziyao.devops.rancher.base.AbstractType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class CisScanConfig extends AbstractType {

    private Boolean debugMaster;

    private Boolean debugWorker;

    private String overrideBenchmarkVersion;

    private List<String> overrideSkip;

    private String profile;

}
