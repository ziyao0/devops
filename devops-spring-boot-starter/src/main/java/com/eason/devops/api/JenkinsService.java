package com.eason.devops.api;

import com.eason.devops.jenkins.model.BuildWithDetails;
import com.eason.devops.jenkins.model.JobWithDetails;

import java.io.IOException;
import java.util.Map;

/**
 * @author Eason
 * @date 2022/10/24
 */
public interface JenkinsService {
    /**
     * 获取jenkins任务详情
     *
     * @param jobName 工作名称
     * @return 返回工作详情信息
     * @throws IOException 异常
     */
    default JobWithDetails getJob(String jobName) throws IOException {
        return null;
    }

    /**
     * 构建jenkins任务并等待任务完成返回结果
     *
     * @param jobName 工作名称
     * @return 返回构建结果详情
     * @throws IOException          IO 异常
     * @throws InterruptedException 中断异常
     */
   default BuildWithDetails build(String jobName) throws IOException, InterruptedException{
        return null;
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
  default   BuildWithDetails build(String jobName, Map<String, String> params) throws IOException, InterruptedException{
        return null;
    }
}
