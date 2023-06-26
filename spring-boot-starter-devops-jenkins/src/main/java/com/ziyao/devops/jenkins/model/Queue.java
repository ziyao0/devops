package com.ziyao.devops.jenkins.model;

import java.util.List;

public class Queue
        extends BaseModel {
    private List<String> discoverableItems;

    private List<QueueItem> items;

    public Queue() {
    }

    public List<String> getDiscoverableItems() {
        return discoverableItems;
    }

    public void setDiscoverableItems(List<String> discoverableItems) {
        this.discoverableItems = discoverableItems;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((discoverableItems == null) ? 0 : discoverableItems.hashCode());
        result = prime * result + ((items == null) ? 0 : items.hashCode());
        return result;
    }

    public List<QueueItem> getItems() {
        return items;
    }

    public void setItems(List<QueueItem> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Queue other = (Queue) obj;
        if (discoverableItems == null) {
            if (other.discoverableItems != null)
                return false;
        } else if (!discoverableItems.equals(other.discoverableItems))
            return false;
        if (items == null) {
            if (other.items != null)
                return false;
        } else if (!items.equals(other.items))
            return false;
        return true;
    }

}
