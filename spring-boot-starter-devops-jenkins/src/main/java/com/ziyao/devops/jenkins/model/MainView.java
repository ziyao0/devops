package com.ziyao.devops.jenkins.model;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

public class MainView extends BaseModel {

    private List<Job> jobs;
    private List<View> views;

    /* default constructor needed for Jackson */
    public MainView() {
        this(Lists.<Job>newArrayList(), Lists.<View>newArrayList());
    }

    public MainView(List<Job> jobs, List<View> views) {
        this.jobs = jobs;
        this.views = views;
    }

    public MainView(Job... jobs) {
        this(Arrays.asList(jobs), Lists.<View>newArrayList());
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<View> getViews() {
        return views;
    }

    public void setViews(List<View> views) {
        this.views = views;
    }
}
