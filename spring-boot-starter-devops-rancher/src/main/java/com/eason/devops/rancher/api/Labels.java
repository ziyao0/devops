package com.eason.devops.rancher.api;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author zhangziyao
 * @date 2021/10/18
 */
@Data
public class Labels {

    @JSONField(name = "workload.user.cattle.io/workloadselector")
    private String _$WorkloadSelector;
}
