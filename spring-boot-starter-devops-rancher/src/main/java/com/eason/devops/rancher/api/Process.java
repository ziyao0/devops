package com.eason.devops.rancher.api;

import com.eason.devops.rancher.base.AbstractType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class Process extends AbstractType {

    private List<String> args;

    private List<String> binds;

    private List<String> command;

    private List<String> env;

    private HealthCheck healthCheck;

    private String image;

    private String imageRegistryAuthConfig;

    private Map<String, Object> labels;

    private String name;

    private String networkMode;

    private String pidMode;

    private Boolean privileged;

    private List<String> publish;

    private String restartPolicy;

    private String user;

    private List<String> volumesFrom;

}
