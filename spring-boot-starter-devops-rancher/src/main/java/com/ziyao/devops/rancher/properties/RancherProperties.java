package com.ziyao.devops.rancher.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ziyao zhang
 * @since 2022/10/19
 */
@ConfigurationProperties("com.ziyao.rancher")
public class RancherProperties {
    /**
     * rancher 访问地址
     * <p>
     * 例如：<a href="https://www.rancher.com">Rancher Home</a>
     */
    private String baseUri;
    /**
     * rancher 访问租户
     * <p>
     * 例：<code>admin</code>
     */
    private String accessKey;
    /**
     * rancher 访问凭据
     * <p>
     * 例：<code>admin</code>
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
