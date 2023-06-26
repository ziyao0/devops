package com.ziyao.devops;

import com.ziyao.devops.api.JenkinsService;
import com.ziyao.devops.api.RancherService;
import com.ziyao.devops.core.DefaultJenkinsService;
import com.ziyao.devops.core.DefaultRancherService;
import com.ziyao.devops.jenkins.core.JenkinsBuilderHelper;
import com.ziyao.devops.jenkins.core.JenkinsServer;
import com.ziyao.devops.rancher.service.Rancher;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ziyao zhang
 * @since 2022/10/24
 */
@Configuration
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
