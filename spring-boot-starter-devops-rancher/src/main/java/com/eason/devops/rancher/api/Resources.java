package com.eason.devops.rancher.api;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangziyao
 * @date 2021/10/18
 */
@Data
public class Resources {
    private Requests requests = new Requests();
    private Limits limits = new Limits();

    @NoArgsConstructor
    @Data
    public static class Requests {
    }

    @NoArgsConstructor
    @Data
    public static class Limits {
    }
}
