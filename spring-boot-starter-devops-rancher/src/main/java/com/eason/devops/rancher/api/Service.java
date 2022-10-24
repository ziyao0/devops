package com.eason.devops.rancher.api;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhangziyao
 * @date 2021/10/13
 */
@NoArgsConstructor
@Data
public class Service {
    @JSONField(name = "projectId")
    private String projectId;
    @JSONField(name = "hostIPC")
    private Boolean hostIPC = false;
    @JSONField(name = "hostNetwork")
    private Boolean hostNetwork = false;
    @JSONField(name = "hostPID")
    private Boolean hostPID = false;
    @JSONField(name = "paused")
    private Boolean paused = false;
    @JSONField(name = "type")
    private String type = "workload";
    @JSONField(name = "namespaceId")
    private String namespaceId = "default";
    @JSONField(name = "scale")
    private Integer scale = 1;
    @JSONField(name = "dnsPolicy")
    private String dnsPolicy = "ClusterFirst";
    @JSONField(name = "restartPolicy")
    private String restartPolicy = "Always";
    @JSONField(name = "labels")
    private LabelsDTO labels = new LabelsDTO();
    @JSONField(name = "containers")
    private List<ContainersDTO> containers = Lists.newArrayList(new ContainersDTO());
    @JSONField(name = "scheduling")
    private SchedulingDTO scheduling = new SchedulingDTO();
    @JSONField(name = "deploymentConfig")
    private DeploymentConfigDTO deploymentConfig = new DeploymentConfigDTO();
    @JSONField(name = "name")
    private String name = "devops-cloud";
    @JSONField(name = "annotations")
    private AnnotationsDTO annotations = new AnnotationsDTO();
    @JSONField(name = "volumes")
    private List<?> volumes = Lists.newArrayList();

    @NoArgsConstructor
    @Data
    public static class LabelsDTO {
        @JSONField(name = "workload.user.cattle.io/workloadselector")
        private String _$WorkloadSelector;
    }

    @NoArgsConstructor
    @Data
    public static class SchedulingDTO {
        @JSONField(name = "node")
        private NodeDTO node = new NodeDTO();

        @NoArgsConstructor
        @Data
        public static class NodeDTO {
        }
    }

    @NoArgsConstructor
    @Data
    public static class DeploymentConfigDTO {
        @JSONField(name = "minReadySeconds")
        private Integer minReadySeconds = 0;
        @JSONField(name = "type")
        private String type = "deploymentConfig";
        @JSONField(name = "revisionHistoryLimit")
        private Integer revisionHistoryLimit = 10;
        @JSONField(name = "strategy")
        private String strategy = "RollingUpdate";
        @JSONField(name = "maxSurge")
        private Integer maxSurge = 1;
        @JSONField(name = "maxUnavailable")
        private Integer maxUnavailable = 0;
    }

    @NoArgsConstructor
    @Data
    public static class AnnotationsDTO {
        @JSONField(name = "cattle.io/timestamp")
        private String _$CattleIoTimestamp294 = LocalDateTime.now().toString();
    }

    @NoArgsConstructor
    @Data
    public static class ContainersDTO {
        @JSONField(name = "initContainer")
        private Boolean initContainer = false;
        @JSONField(name = "restartCount")
        private Integer restartCount = 0;
        @JSONField(name = "stdin")
        private Boolean stdin = true;
        @JSONField(name = "stdinOnce")
        private Boolean stdinOnce = false;
        @JSONField(name = "tty")
        private Boolean tty = true;
        @JSONField(name = "type")
        private String type = "container";
        @JSONField(name = "privileged")
        private Boolean privileged = false;
        @JSONField(name = "allowPrivilegeEscalation")
        private Boolean allowPrivilegeEscalation = false;
        @JSONField(name = "readOnly")
        private Boolean readOnly = false;
        @JSONField(name = "runAsNonRoot")
        private Boolean runAsNonRoot = false;
        @JSONField(name = "namespaceId")
        private Object namespaceId;
        @JSONField(name = "imagePullPolicy")
        private String imagePullPolicy = "Always";
        @JSONField(name = "environmentFrom")
        private List<?> environmentFrom = Lists.newArrayList();
        @JSONField(name = "resources")
        private ResourcesDTO resources = new ResourcesDTO();
        @JSONField(name = "capAdd")
        private List<?> capAdd = Lists.newArrayList();
        @JSONField(name = "capDrop")
        private List<?> capDrop = Lists.newArrayList();
        @JSONField(name = "image")
        private String image = "devops/devops-cloud:latest";
        @JSONField(name = "ports")
        private List<?> ports = Lists.newArrayList();
        @JSONField(name = "environment")
        private EnvironmentDTO environment = new EnvironmentDTO();
        @JSONField(name = "livenessProbe")
        private Object livenessProbe;
        @JSONField(name = "name")
        private String name = "devops-cloud";
        @JSONField(name = "volumeMounts")
        private List<?> volumeMounts = Lists.newArrayList();

        @NoArgsConstructor
        @Data
        public static class ResourcesDTO {
            @JSONField(name = "requests")
            private RequestsDTO requests = new RequestsDTO();
            @JSONField(name = "limits")
            private LimitsDTO limits = new LimitsDTO();

            @NoArgsConstructor
            @Data
            public static class RequestsDTO {
            }

            @NoArgsConstructor
            @Data
            public static class LimitsDTO {
            }
        }

        @NoArgsConstructor
        @Data
        public static class EnvironmentDTO {
            @JSONField(name = "TZ")
            private String tz = "Asia/Shanghai";
            @JSONField(name = "JAVA_OPTS")
            private String javaOpts = "-Xms512m -Xmx2048m";
            @JSONField(name = "JAVA_ARGS")
            private String javaArgs = "";
        }
    }
}
