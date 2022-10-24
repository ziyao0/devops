package com.eason.devops.jenkins.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "hudson.model.ParametersDefinitionProperty")
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)
public class ParametersDefinitionProperty {

    @XmlElement(name = "parameterDefinitions")
    private ParameterDefinitions pd;

    public ParametersDefinitionProperty() {
    }

    public ParametersDefinitionProperty(ParameterDefinitions pd) {
        this.pd = pd;
    }

    public ParameterDefinitions getPd() {
        return pd;
    }

    public void setPd(ParameterDefinitions pd) {
        this.pd = pd;
    }
}
