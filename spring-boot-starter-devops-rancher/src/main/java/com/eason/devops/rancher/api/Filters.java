package com.eason.devops.rancher.api;

import lombok.Data;
import lombok.ToString;

/**
 * @author zhangziyao
 * @date 2021/10/18
 */
@Data
@ToString
public class Filters {
    private String created;
    private String creatorId;
    private String name;
    private String namespaceId;
    private String removed;
    private String uuid;
}
