package com.eason.devops.rancher.api;

import lombok.Data;

/**
 * @author zhangziyao
 * @date 2021/10/18
 */
@Data
public class Sort {
    private String order;
    private String reverse;
    private Links links;
}
