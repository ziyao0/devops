package com.ziyao.devops.rancher.api;

import lombok.Data;

/**
 * @author zhangziyao
 * @since 2021/10/18
 */
@Data
public class Sort {
    private String order;
    private String reverse;
    private Links links;
}
