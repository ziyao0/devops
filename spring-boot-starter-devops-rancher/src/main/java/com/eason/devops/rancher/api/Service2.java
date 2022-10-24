package com.eason.devops.rancher.api;

import java.util.List;

/**
 * @author zhangziyao
 * @date 2021/10/18
 */

public class Service2 {
    private String type;
    private Links links;
    private CreateTypes createTypes;
    private Actions actions;
    private Pagination pagination;
    private Sort sort;
    private Filters filters;
    private String resourceType;
    private List<Metadata> data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public CreateTypes getCreateTypes() {
        return createTypes;
    }

    public void setCreateTypes(CreateTypes createTypes) {
        this.createTypes = createTypes;
    }

    public Actions getActions() {
        return actions;
    }

    public void setActions(Actions actions) {
        this.actions = actions;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public Filters getFilters() {
        return filters;
    }

    public void setFilters(Filters filters) {
        this.filters = filters;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public List<Metadata> getData() {
        return data;
    }

    public void setData(List<Metadata> data) {
        this.data = data;
    }
}
