package com.ziyao.devops.rancher.api;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author zhangziyao
 * @since 2021/10/18
 */
@Data
@ToString
public class Containers {
    private Boolean initContainer = false;
    private Integer restartCount = 0;
    private Boolean stdin = true;
    private Boolean stdinOnce = false;
    private Boolean tty = true;
    private String type = "container";
    private Boolean privileged = false;
    private Boolean allowPrivilegeEscalation = false;
    private Boolean readOnly = false;
    private Boolean runAsNonRoot = false;
    private Object namespaceId;
    private String imagePullPolicy = "Always";
    private List<?> environmentFrom = Lists.newArrayList();
    private Resources resources = new Resources();
    private List<?> capAdd = Lists.newArrayList();
    private List<?> capDrop = Lists.newArrayList();
    private String image;
    private List<Ports> ports = Lists.newArrayList();
    private Environment environment = new Environment();
    private Object livenessProbe;
    private String name;
    private List<?> volumeMounts = Lists.newArrayList();
}