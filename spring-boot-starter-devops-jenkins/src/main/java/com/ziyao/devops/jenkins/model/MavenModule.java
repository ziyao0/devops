package com.ziyao.devops.jenkins.model;

import java.util.List;

public class MavenModule extends BaseModel {

    private List<MavenModuleRecord> moduleRecords;

    public List<MavenModuleRecord> getModuleRecords() {
        return moduleRecords;
    }

}
