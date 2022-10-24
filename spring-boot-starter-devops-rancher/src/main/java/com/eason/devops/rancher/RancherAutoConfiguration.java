package com.eason.devops.rancher;

import com.eason.devops.rancher.properties.RancherProperties;
import com.eason.devops.rancher.service.Rancher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Eason
 * @date 2022/10/19
 */
@Configuration
@Import(RancherProperties.class)
public class RancherAutoConfiguration {

    @Bean
    public Rancher rancher(RancherProperties rancherProperties) throws MalformedURLException {
        Rancher.Config config = new Rancher.Config(
                new URL(rancherProperties.getBaseUri()), rancherProperties.getAccessKey(), rancherProperties.getSecretKey());
        return new Rancher(config);
    }
}
