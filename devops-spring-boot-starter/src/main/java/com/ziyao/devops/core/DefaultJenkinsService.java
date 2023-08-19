package com.ziyao.devops.core;

import com.ziyao.devops.api.JenkinsService;
import com.ziyao.devops.jenkins.core.JenkinsBuilderHelper;
import com.ziyao.devops.jenkins.core.JenkinsServer;
import com.ziyao.devops.jenkins.model.BuildWithDetails;
import com.ziyao.devops.jenkins.model.JobWithDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * @author ziyao zhang
 * @since 2022/10/24
 */
public class DefaultJenkinsService implements JenkinsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultJenkinsService.class);
    /**
     * jenkins server
     */
    private final JenkinsServer jenkinsServer;
    /**
     * jenkins build utils
     */
    private final JenkinsBuilderHelper jenkinsBuilderHelper;

    public DefaultJenkinsService(JenkinsServer jenkinsServer, JenkinsBuilderHelper jenkinsBuilderHelper) {
        this.jenkinsServer = jenkinsServer;
        this.jenkinsBuilderHelper = jenkinsBuilderHelper;
    }

    /**
     * 获取jenkins任务详情
     *
     * @param jobName 工作名称
     * @return 返回工作详情信息
     * @throws IOException 异常
     */
    @Override
    public JobWithDetails getJob(String jobName) throws IOException {

        return jenkinsServer.getJob(jobName);
    }

    /**
     * 构建jenkins任务并等待任务完成返回结果
     *
     * @param jobName 工作名称
     * @return 返回构建结果详情
     * @throws IOException          IO 异常
     * @throws InterruptedException 中断异常
     */
    @Override
    public BuildWithDetails build(String jobName) throws IOException, InterruptedException {
        LOGGER.info("触发Jenkins构建任务，jobName：{}", jobName);
        return jenkinsBuilderHelper.builderJobAndWaitUntilFinished(jobName);
    }

    /**
     * 构建jenkins任务并等待任务完成返回结果
     *
     * @param jobName 工作名称
     * @param params  附加参数
     * @return 返回构建结果详情
     * @throws IOException          IO 异常
     * @throws InterruptedException 中断异常
     */
    @Override
    public BuildWithDetails build(String jobName, Map<String, String> params) throws IOException, InterruptedException {
        LOGGER.info("触发Jenkins构建任务，jobName：{},job参数：{}", jobName, params);
        return jenkinsBuilderHelper.builderJobAndWaitUntilFinished(jobName, params);
    }

    public JenkinsServer getJenkinsServer() {
        return jenkinsServer;
    }

    public JenkinsBuilderHelper getJenkinsBuilderHelper() {
        return jenkinsBuilderHelper;
    }
}
