package com.ziyao.devops.jenkins.model;

import java.util.List;

/**
 * @author zhangziyao
 */
public class PluginManager extends BaseModel {
    private List<Plugin> plugins;

    public List<Plugin> getPlugins() {
        return plugins;
    }

    public void setPlugins(List<Plugin> plugins) {
        this.plugins = plugins;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((plugins == null) ? 0 : plugins.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PluginManager other = (PluginManager) obj;
        if (plugins == null) {
            if (other.plugins != null)
                return false;
        } else if (!plugins.equals(other.plugins))
            return false;
        return true;
    }

}
