package com.eason.devops.rancher.api;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author zhangziyao
 * @date 2021/10/18
 */
@Data
public class WorkloadLabels {
    //    @JSONField(name = "pod-template-hash")
//    private String podtemplatehash;
    @JSONField(name = "workload.user.cattle.io/workloadselector")
    private String _$WorkloadUserCattleIoWorkloadselector76;
}
