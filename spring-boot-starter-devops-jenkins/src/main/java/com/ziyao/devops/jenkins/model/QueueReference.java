package com.ziyao.devops.jenkins.model;

public class QueueReference extends BaseModel {

    private String queueItem;

    public QueueReference(String location) {
        queueItem = location;
    }

    public String getQueueItemUrlPart() {
        return queueItem;
    }

}
