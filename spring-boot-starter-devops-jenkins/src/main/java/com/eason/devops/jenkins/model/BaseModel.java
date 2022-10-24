package com.eason.devops.jenkins.model;

import com.eason.devops.jenkins.api.JenkinsHttpConnection;

/**
 * The base model.
 */
public class BaseModel {

    /**
     * The class.
     */
    private String _class;


    /**
     * Get the class.
     *
     * @return class
     */
    public String get_class() {
        return _class;
    }

    protected JenkinsHttpConnection client;


    /**
     * Get the HTTP client.
     *
     * @return client
     */
    public JenkinsHttpConnection getClient() {
        return client;
    }

    /**
     * Set the HTTP client.
     *
     * @param client {@link JenkinsHttpConnection}.
     */
    public void setClient(final JenkinsHttpConnection client) {
        this.client = client;
    }
}
