package com.eason.devops.jenkins.model;

import java.util.List;

/**
 * This class is only needed to get all builds in
 * {@link JobWithDetails#getAllBuilds()}.
 *
 * @author zhangziyao
 * <p>
 * NOTE: This class is not part of any public API
 */
class AllBuilds extends BaseModel {
    private List<Build> allBuilds;

    public AllBuilds() {
    }

    public List<Build> getAllBuilds() {
        return this.allBuilds;
    }
}