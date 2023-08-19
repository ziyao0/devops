package com.ziyao.devops.rancher.api;

import com.ziyao.devops.rancher.base.AbstractType;
import com.ziyao.devops.rancher.ext.Password;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ImportedConfig extends AbstractType {

    private Password kubeConfig;

}
