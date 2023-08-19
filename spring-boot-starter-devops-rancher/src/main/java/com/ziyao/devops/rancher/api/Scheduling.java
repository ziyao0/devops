package com.ziyao.devops.rancher.api;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangziyao
 * @since 2021/10/18
 */
@Data

public class Scheduling {

    private Node node = new Node();

    @NoArgsConstructor
    @Data
    public static class Node {
    }

}
