package com.eason.devops.rancher.api;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author zhangziyao
 * @date 2021/10/18
 */
@Data
@ToString
public class Annotations {
    @JSONField(name = "cattle.io/timestamp")
    private String _$CattleIoTimestamp12 = LocalDateTime.now().toString();
}
