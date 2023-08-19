package com.ziyao.devops.rancher.base;

/**
 * @author zhangziyao
 * @since 2021/10/10
 */
public abstract class AbstractType {

    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
