package com.eason.devops.jenkins.model;

import java.util.List;

/**
 * This class is only needed to get all builds in
 * {@link MavenJobWithDetails#getAllBuilds()}.
 *
 * @author zhangziyao
 * <p>
 * NOTE: This class is not part of any public API
 */
class AllMavenBuilds extends BaseModel {
    private List<MavenBuild> allBuilds;

    public AllMavenBuilds() {
    }

    public List<MavenBuild> getAllBuilds() {
        return this.allBuilds;
    }
}