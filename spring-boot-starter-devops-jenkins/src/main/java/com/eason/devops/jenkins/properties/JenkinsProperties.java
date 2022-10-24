package com.eason.devops.jenkins.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Eason
 * @date 2022/10/18
 */
@ConfigurationProperties("com.eason.jenkins")
public class JenkinsProperties {

    /**
     * jenkins 访问地址
     * <p>
     * 例如：<a href="http://localhost:22015">Jenkins Home</a>
     */
    private String baseUri;
    /**
     * Jenkins 访问租户
     * <p>
     * 例：<code>jenkins</code>
     */
    private String accessKey;
    /**
     * Jenkins 访问凭据
     * <p>
     * 例：<code>jenkins</code>
     */
    private String secretKey;

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
