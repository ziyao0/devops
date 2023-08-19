package com.ziyao.devops.jenkins.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "parameterDefinitions")
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)
public class ParameterDefinitions {

    @XmlElement(name = "hudson.model.StringParameterDefinition")
    List<StringParameterDefinition> stringParams;

    public ParameterDefinitions() {
        stringParams = new ArrayList<StringParameterDefinition>();
    }

    public ParameterDefinitions(List<StringParameterDefinition> stringParams) {
        this.stringParams = stringParams;
    }

    public List<StringParameterDefinition> getStringParams() {
        return stringParams;
    }

    public void setStringParams(List<StringParameterDefinition> stringParams) {
        this.stringParams = stringParams;
    }

    public void addParam(StringParameterDefinition spd) {
        stringParams.add(spd);
    }
}
