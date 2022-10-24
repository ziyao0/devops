package com.eason.devops.rancher.api;

import com.eason.devops.rancher.base.AbstractType;
import com.eason.devops.rancher.ext.Password;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ImportedConfig extends AbstractType {

    private Password kubeConfig;

}
