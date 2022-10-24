package com.eason.devops.jenkins.model;

import java.util.List;

public class QueueItemActions extends BaseModel {
    private List<CauseAction> causes;

    public List<CauseAction> getCauses() {
        return causes;
    }

    public void setCauses(List<CauseAction> causes) {
        this.causes = causes;
    }

}
