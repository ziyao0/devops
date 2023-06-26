package com.ziyao.devops.rancher.api;

import lombok.Data;

/**
 * @author zhangziyao
 * @since 2021/10/18
 */
@Data
public class Links {
    private String self;
    private String uuid;
    private String remove;
    private String update;
    private String yaml;
}
