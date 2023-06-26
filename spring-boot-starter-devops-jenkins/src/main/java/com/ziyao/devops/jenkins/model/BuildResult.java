package com.ziyao.devops.jenkins.model;

public enum BuildResult {
    FAILURE, UNSTABLE, REBUILDING, BUILDING,
    /**
     * This means a job was already running and has been aborted.
     */
    ABORTED,
    /**
     *
     */
    SUCCESS,
    /**
     * ?
     */
    UNKNOWN,
    /**
     * This is returned if a job has never been built.
     */
    NOT_BUILT,
    /**
     * This will be the result of a job in cases where it has been cancelled
     * during the time in the queue.
     */
    CANCELLED
}
