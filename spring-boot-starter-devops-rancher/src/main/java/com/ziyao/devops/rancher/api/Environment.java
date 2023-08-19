package com.ziyao.devops.rancher.api;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

/**
 * @author zhangziyao
 * @since 2021/10/18
 */
@Data
@ToString
public class Environment {
    @JSONField(name = "TZ")
    private String tz;
    @JSONField(name = "JAVA_OPTS")
    private String javaOpts;
    @JSONField(name = "JAVA_ARGS")
    private String javaArgs;
}
