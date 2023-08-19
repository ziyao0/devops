package com.ziyao.devops.rancher.api;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author zhangziyao
 * @since 2021/10/18
 */
@Data
public class Selector {
    @JSONField(name = "matchLabels")
    private MatchLabels matchLabels = new MatchLabels();
    @JSONField(name = "type")
    private String type = "/v3/project/schemas/labelSelector";
}
