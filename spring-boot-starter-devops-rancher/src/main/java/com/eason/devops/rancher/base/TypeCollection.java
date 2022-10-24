package com.eason.devops.rancher.base;

import java.util.List;

/**
 * @author zhangziyao
 * @date 2021/10/10
 */
public class TypeCollection<T> {

    private List<T> data;

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
