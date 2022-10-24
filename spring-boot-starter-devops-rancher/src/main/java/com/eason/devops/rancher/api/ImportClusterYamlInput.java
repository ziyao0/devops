package com.eason.devops.rancher.api;

import com.eason.devops.rancher.base.AbstractType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ImportClusterYamlInput extends AbstractType {

    private String defaultNamespace;

    private String namespace;

    private String projectId;

    private String yaml;
}
