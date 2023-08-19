package com.ziyao.devops.jenkins.model;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.escape.Escaper;
import com.google.common.net.UrlEscapers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.join;

public class Job extends BaseModel {

    private String name;
    private String url;
    private String fullName;

    public Job() {
    }

    public Job(String name, String url) {
        this();
        this.name = name;
        this.url = url;
        this.fullName = null;
    }

    public Job(String name, String url, String fullName) {
        this();
        this.name = name;
        this.url = url;
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getFullName() {
        return fullName;
    }

    public JobWithDetails details() throws IOException {
        return client.get(url, JobWithDetails.class);
    }

    /**
     * Get a file from workspace.
     *
     * @param fileName The name of the file to download from workspace. You can
     *                 also access files which are in sub folders of the workspace.
     * @return The string which contains the content of the file.
     * @throws IOException in case of an error.
     */
    public String getFileFromWorkspace(String fileName) throws IOException {
        InputStream is = client.getFile(URI.create(url + "/ws/" + fileName));
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }

    /**
     * Trigger a build without parameters
     *
     * @return {@link QueueReference} for further analysis of the queued build.
     * @throws IOException in case of an error.
     */
    public QueueReference build() throws IOException {
        ExtractHeader location = client.post(url + "build", null, ExtractHeader.class, false);
        return new QueueReference(location.getLocation());

    }

    /**
     * Trigger a build with crumbFlag.
     *
     * @param crumbFlag true or false.
     * @return {@link QueueReference} for further analysis of the queued build.
     * @throws IOException in case of an error.
     */
    public QueueReference build(boolean crumbFlag) throws IOException {
        ExtractHeader location = client.post(url + "build", null, ExtractHeader.class, crumbFlag);
        return new QueueReference(location.getLocation());
    }

    /**
     * Trigger a parameterized build with string parameters only
     *
     * @param params the job parameters
     * @return {@link QueueReference} for further analysis of the queued build.
     * @throws IOException in case of an error.
     */
    public QueueReference build(Map<String, String> params) throws IOException {
        return build(params, null, false);
    }

    /**
     * Trigger a parameterized build with string parameters only
     *
     * @param params    the job parameters
     * @param crumbFlag true or false.
     * @return {@link QueueReference} for further analysis of the queued build.
     * @throws IOException in case of an error.
     */
    public QueueReference build(Map<String, String> params, boolean crumbFlag) throws IOException {
        return build(params, null, crumbFlag);
    }

    /**
     * Trigger a parameterized build with file parameters
     *
     * @param params     the job parameters
     * @param fileParams the job file parameters
     * @return {@link QueueReference} for further analysis of the queued build.
     * @throws IOException in case of an error.
     */
    public QueueReference build(Map<String, String> params, Map<String, File> fileParams) throws IOException {
        return build(params, fileParams, false);
    }

    /**
     * Trigger a parameterized build with file parameters and crumbFlag
     *
     * @param params     the job parameters
     * @param fileParams the job file parameters
     * @param crumbFlag  determines whether crumb flag is used
     * @return {@link QueueReference} for further analysis of the queued build.
     * @throws IOException in case of an error.
     */
    public QueueReference build(Map<String, String> params, Map<String, File> fileParams, boolean crumbFlag) throws IOException {
        String qs = join(Collections2.transform(params.entrySet(), new MapEntryToQueryStringPair()), "&");
        ExtractHeader location = client.post(url + "buildWithParameters?" + qs, null, ExtractHeader.class, fileParams, crumbFlag);
        return new QueueReference(location.getLocation());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Job job = (Job) o;

        if (name != null ? !name.equals(job.name) : job.name != null)
            return false;
        if (url != null ? !url.equals(job.url) : job.url != null)
            return false;
        if (fullName != null ? !fullName.equals(job.fullName) : job.fullName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0) + (fullName != null ? fullName.hashCode() : 0);
        return result;
    }

    private static class MapEntryToQueryStringPair implements Function<Map.Entry<String, String>, String> {
        @Override
        public String apply(Map.Entry<String, String> entry) {
            Escaper escaper = UrlEscapers.urlFormParameterEscaper();
            return escaper.escape(entry.getKey()) + "=" + escaper.escape(entry.getValue());
        }
    }
}
