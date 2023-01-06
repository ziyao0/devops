package com.eason.devops;

import com.eason.devops.api.JenkinsService;
import com.eason.devops.api.RancherService;
import com.eason.devops.core.DefaultJenkinsService;
import com.eason.devops.core.DefaultRancherService;
import com.eason.devops.jenkins.JenkinsAutoConfiguration;
import com.eason.devops.jenkins.core.JenkinsBuilderHelper;
import com.eason.devops.jenkins.core.JenkinsServer;
import com.eason.devops.rancher.RancherAutoConfiguration;
import com.eason.devops.rancher.service.Rancher;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Eason
 * @date 2022/10/24
 */
@Configuration
@AutoConfigureAfter({JenkinsAutoConfiguration.class, RancherAutoConfiguration.class})
public class DevOpsAutoConfiguration {

    @Bean
    @ConditionalOnBean({JenkinsServer.class, JenkinsBuilderHelper.class})
    public JenkinsService jenkinsService(JenkinsServer jenkinsServer
            , JenkinsBuilderHelper jenkinsBuilderHelper) {
        return new DefaultJenkinsService(jenkinsServer, jenkinsBuilderHelper);
    }

    @Bean
    @ConditionalOnBean(Rancher.class)
    public RancherService rancherService(Rancher rancher) {
        return new DefaultRancherService(rancher);
    }
}
