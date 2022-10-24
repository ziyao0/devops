package com.eason.devops.jenkins;

import com.eason.devops.jenkins.core.JenkinsBuilderHelper;
import com.eason.devops.jenkins.core.JenkinsServer;
import com.eason.devops.jenkins.properties.JenkinsProperties;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author lajos
 * @date 2022/9/27 16:24
 */
@Configuration
@Import(JenkinsProperties.class)
@AutoConfigureBefore(JenkinsProperties.class)
public class JenkinsAutoConfiguration {

    @Bean
    @ConditionalOnBean(JenkinsProperties.class)
    public JenkinsServer jenkinsServer(JenkinsProperties jenkinsProperties) throws URISyntaxException {
        return new JenkinsServer(new URI(jenkinsProperties.getBaseUri()),
                jenkinsProperties.getAccessKey(), jenkinsProperties.getSecretKey());
    }

    @Bean
    @ConditionalOnBean(JenkinsServer.class)
    public JenkinsBuilderHelper jenkinsTriggerHelper(JenkinsServer server) {
        return new JenkinsBuilderHelper(server, 30000L);
    }
}
