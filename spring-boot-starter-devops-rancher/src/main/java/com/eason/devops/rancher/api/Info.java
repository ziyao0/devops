package com.eason.devops.rancher.api;

import com.eason.devops.rancher.base.AbstractType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Info extends AbstractType {

    private String buildDate;

    private String compiler;

    private String gitCommit;

    private String gitTreeState;

    private String gitVersion;

    private String goVersion;

    private String major;

    private String minor;

    private String platform;
}
