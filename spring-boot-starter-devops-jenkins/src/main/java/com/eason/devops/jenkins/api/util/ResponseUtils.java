package com.eason.devops.jenkins.api.util;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

/**
 * @author zhangziyao
 */
public final class ResponseUtils {

    /**
     * Utility Class.
     */
    private ResponseUtils() {
        //do nothing
    }


    /**
     * Get Jenkins version from supplied response if any.
     *
     * @param response the response
     * @return the version or empty string
     */
    public static String getJenkinsVersion(final HttpResponse response) {
        final Header[] hdrs = response.getHeaders("X-Jenkins");
        return hdrs.length == 0 ? "" : hdrs[0].getValue();
    }


}
