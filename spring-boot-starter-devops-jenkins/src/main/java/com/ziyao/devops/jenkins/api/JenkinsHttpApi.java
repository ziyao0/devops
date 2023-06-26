package com.ziyao.devops.jenkins.api;

import com.ziyao.devops.jenkins.api.util.EncodingUtils;
import com.ziyao.devops.jenkins.api.util.RequestReleasingInputStream;
import com.ziyao.devops.jenkins.api.util.ResponseUtils;
import com.ziyao.devops.jenkins.api.util.UrlUtils;
import com.ziyao.devops.jenkins.api.validator.HttpResponseValidator;
import com.ziyao.devops.jenkins.model.BaseModel;
import com.ziyao.devops.jenkins.model.Crumb;
import com.ziyao.devops.jenkins.model.ExtractHeader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.io.ByteStreams;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static org.apache.commons.lang.StringUtils.isNotBlank;

public class JenkinsHttpApi implements JenkinsHttpConnection {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private URI uri;
    private CloseableHttpClient client;
    private HttpContext localContext;
    private HttpResponseValidator httpResponseValidator;
    // private HttpResponseContentExtractor contentExtractor;

    private ObjectMapper mapper;
    private String context;

    private String jenkinsVersion;

    public final static String EMPTY_VERSION = "UNKNOWN";

    /**
     * Create an unauthenticated Jenkins HTTP client
     *
     * @param uri    Location of the jenkins server (ex. http://localhost:8080)
     * @param client Configured CloseableHttpClient to be used
     */
    public JenkinsHttpApi(URI uri, CloseableHttpClient client) {
        this.context = uri.getPath();
        if (!context.endsWith("/")) {
            context += "/";
        }
        this.uri = uri;
        this.mapper = getDefaultMapper();
        this.client = client;
        this.httpResponseValidator = new HttpResponseValidator();
        // this.contentExtractor = new HttpResponseContentExtractor();
        this.jenkinsVersion = EMPTY_VERSION;
        LOGGER.debug("uri={}", uri.toString());
    }

    /**
     * Create an unauthenticated Jenkins HTTP client
     *
     * @param uri     Location of the jenkins server (ex. http://localhost:8080)
     * @param builder Configured HttpClientBuilder to be used
     */
    public JenkinsHttpApi(URI uri, HttpClientBuilder builder) {
        this(uri, builder.build());
    }

    /**
     * Create an unauthenticated Jenkins HTTP client
     *
     * @param uri Location of the jenkins server (ex. http://localhost:8080)
     */
    public JenkinsHttpApi(URI uri) {
        this(uri, HttpClientBuilder.create());
    }

    /**
     * Create an authenticated Jenkins HTTP client
     *
     * @param uri      Location of the jenkins server (ex. http://localhost:8080)
     * @param username Username to use when connecting
     * @param password Password or auth token to use when connecting
     */
    public JenkinsHttpApi(URI uri, String username, String password) {
        this(uri, HttpClientBuilder.create(), username, password);
    }

    /**
     * Create an authenticated Jenkins HTTP client
     *
     * @param uri      Location of the jenkins server (ex. http://localhost:8080)
     * @param builder  Configured HttpClientBuilder to be used
     * @param username Username to use when connecting
     * @param password Password or auth token to use when connecting
     */
    public JenkinsHttpApi(URI uri, HttpClientBuilder builder, String username, String password) {
        this(uri, addAuthentication(builder, uri, username, password));
        if (isNotBlank(username)) {
            localContext = new BasicHttpContext();
            localContext.setAttribute("preemptive-auth", new BasicScheme());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends BaseModel> T get(String path, Class<T> cls) throws IOException {
        HttpGet getMethod = new HttpGet(UrlUtils.toJsonApiUri(uri, context, path));

        HttpResponse response = client.execute(getMethod, localContext);
        jenkinsVersion = ResponseUtils.getJenkinsVersion(response);
        try {
            httpResponseValidator.validateResponse(response);
            return objectFromResponse(cls, response);
        } finally {
            EntityUtils.consume(response.getEntity());
            releaseConnection(getMethod);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String get(String path) throws IOException {
        HttpGet getMethod = new HttpGet(UrlUtils.toJsonApiUri(uri, context, path));
        HttpResponse response = client.execute(getMethod, localContext);
        jenkinsVersion = ResponseUtils.getJenkinsVersion(response);
        LOGGER.debug("get({}), version={}, responseCode={}", path, this.jenkinsVersion,
                response.getStatusLine().getStatusCode());
        try {
            httpResponseValidator.validateResponse(response);
            return IOUtils.toString(response.getEntity().getContent());
        } finally {
            EntityUtils.consume(response.getEntity());
            releaseConnection(getMethod);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends BaseModel> T getQuietly(String path, Class<T> cls) {
        T value;
        try {
            value = get(path, cls);
            return value;
        } catch (IOException e) {
            LOGGER.debug("getQuietly({}, {})", path, cls.getName(), e);
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream getFile(URI path) throws IOException {
        HttpGet getMethod = new HttpGet(path);
        HttpResponse response = client.execute(getMethod, localContext);
        jenkinsVersion = ResponseUtils.getJenkinsVersion(response);
        httpResponseValidator.validateResponse(response);
        return new RequestReleasingInputStream(response.getEntity().getContent(), getMethod);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R extends BaseModel, D> R post(String path, D data, Class<R> cls) throws IOException {
        return post(path, data, cls, null, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R extends BaseModel, D> R post(String path, D data, Class<R> cls, boolean crumbFlag) throws IOException {
        return post(path, data, cls, null, crumbFlag);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R extends BaseModel, D> R post(String path, D data, Class<R> cls, Map<String, File> fileParams, boolean crumbFlag) throws IOException {
        HttpPost request = new HttpPost(UrlUtils.toJsonApiUri(uri, context, path));
        if (crumbFlag == true) {
            Crumb crumb = getQuietly("/crumbIssuer", Crumb.class);
            if (crumb != null) {
                request.addHeader(new BasicHeader(crumb.getCrumbRequestField(), crumb.getCrumb()));
            }
        }

        if (data != null) {
            String value = mapper.writeValueAsString(data);
            StringEntity stringEntity = new StringEntity(value, ContentType.APPLICATION_JSON);
            request.setEntity(stringEntity);
        }

        // Prepare file parameters
        if (fileParams != null && !(fileParams.isEmpty())) {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            for (Map.Entry<String, File> entry : fileParams.entrySet()) {
                FileBody fileBody = new FileBody(entry.getValue());
                builder.addPart(entry.getKey(), fileBody);
            }

            HttpEntity entity = builder.build();
            request.setEntity(entity);
        }

        HttpResponse response = client.execute(request, localContext);
        jenkinsVersion = ResponseUtils.getJenkinsVersion(response);

        try {
            httpResponseValidator.validateResponse(response);

            if (cls != null) {
                R responseObject;
                if (cls.equals(ExtractHeader.class)) {
                    ExtractHeader location = new ExtractHeader();
                    location.setLocation(response.getFirstHeader("Location").getValue());
                    responseObject = (R) location;
                } else {
                    responseObject = objectFromResponse(cls, response);
                }
                return responseObject;
            } else {
                return null;
            }
        } finally {
            EntityUtils.consume(response.getEntity());
            releaseConnection(request);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void post_form(String path, Map<String, String> data, boolean crumbFlag) throws IOException {
        HttpPost request;
        if (data != null) {
            // helpful here
            List<String> queryParams = Lists.newArrayList();
            for (String param : data.keySet()) {
                queryParams.add(param + "=" + EncodingUtils.encodeParam(data.get(param)));
            }

            queryParams.add("json=" + EncodingUtils.encodeParam(JSONObject.fromObject(data).toString()));
            String value = mapper.writeValueAsString(data);
            StringEntity stringEntity = new StringEntity(value, ContentType.APPLICATION_FORM_URLENCODED);
            request = new HttpPost(UrlUtils.toNoApiUri(uri, context, path) + StringUtils.join(queryParams, "&"));
            request.setEntity(stringEntity);
        } else {
            request = new HttpPost(UrlUtils.toNoApiUri(uri, context, path));
        }

        if (crumbFlag) {
            Crumb crumb = get("/crumbIssuer", Crumb.class);
            if (crumb != null) {
                request.addHeader(new BasicHeader(crumb.getCrumbRequestField(), crumb.getCrumb()));
            }
        }

        HttpResponse response = client.execute(request, localContext);
        jenkinsVersion = ResponseUtils.getJenkinsVersion(response);

        try {
            httpResponseValidator.validateResponse(response);
        } finally {
            EntityUtils.consume(response.getEntity());
            releaseConnection(request);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpResponse post_form_with_result(String path, List<NameValuePair> data, boolean crumbFlag) throws IOException {
        HttpPost request;
        if (data != null) {
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(data);
            request = new HttpPost(UrlUtils.toNoApiUri(uri, context, path));
            request.setEntity(urlEncodedFormEntity);
        } else {
            request = new HttpPost(UrlUtils.toNoApiUri(uri, context, path));
        }

        if (crumbFlag == true) {
            Crumb crumb = get("/crumbIssuer", Crumb.class);
            if (crumb != null) {
                request.addHeader(new BasicHeader(crumb.getCrumbRequestField(), crumb.getCrumb()));
            }
        }
        HttpResponse response = client.execute(request, localContext);
        jenkinsVersion = ResponseUtils.getJenkinsVersion(response);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String post_xml(String path, String xml_data) throws IOException {
        return post_xml(path, xml_data, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String post_xml(String path, String xml_data, boolean crumbFlag) throws IOException {
        HttpPost request = new HttpPost(UrlUtils.toJsonApiUri(uri, context, path));
        if (crumbFlag) {
            Crumb crumb = getQuietly("/crumbIssuer", Crumb.class);
            if (crumb != null) {
                request.addHeader(new BasicHeader(crumb.getCrumbRequestField(), crumb.getCrumb()));
            }
        }

        if (xml_data != null) {
            request.setEntity(new StringEntity(xml_data, ContentType.create("text/xml", "utf-8")));
        }
        HttpResponse response = client.execute(request, localContext);
        jenkinsVersion = ResponseUtils.getJenkinsVersion(response);
        try {
            httpResponseValidator.validateResponse(response);
            return IOUtils.toString(response.getEntity().getContent());
        } finally {
            EntityUtils.consume(response.getEntity());
            releaseConnection(request);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String post_text(String path, String textData, boolean crumbFlag) throws IOException {
        return post_text(path, textData, ContentType.DEFAULT_TEXT, crumbFlag);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String post_text(String path, String textData, ContentType contentType, boolean crumbFlag)
            throws IOException {
        HttpPost request = new HttpPost(UrlUtils.toJsonApiUri(uri, context, path));
        if (crumbFlag == true) {
            Crumb crumb = get("/crumbIssuer", Crumb.class);
            if (crumb != null) {
                request.addHeader(new BasicHeader(crumb.getCrumbRequestField(), crumb.getCrumb()));
            }
        }

        if (textData != null) {
            request.setEntity(new StringEntity(textData, contentType));
        }
        HttpResponse response = client.execute(request, localContext);
        jenkinsVersion = ResponseUtils.getJenkinsVersion(response);
        try {
            httpResponseValidator.validateResponse(response);
            return IOUtils.toString(response.getEntity().getContent());
        } finally {
            EntityUtils.consume(response.getEntity());
            releaseConnection(request);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void post(String path) throws IOException {
        post(path, null, null, null, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void post(String path, boolean crumbFlag) throws IOException {
        post(path, null, null, null, crumbFlag);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getJenkinsVersion() {
        return this.jenkinsVersion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isJenkinsVersionSet() {
        return !EMPTY_VERSION.equals(this.jenkinsVersion);
    }

    /**
     * Closes underlying resources. Any I/O errors whilst closing are logged
     * with debug log level Closed instances should no longer be used Closing an
     * already closed instance has no side effects
     */
    @Override
    public void close() {
        try {
            client.close();
        } catch (final IOException ex) {
            LOGGER.debug("I/O exception closing client", ex);
        }
    }


    /**
     * Add authentication to supplied builder.
     *
     * @param builder  the builder to configure
     * @param uri      the server URI
     * @param username the username
     * @param password the password
     * @return the passed in builder
     */
    protected static HttpClientBuilder addAuthentication(final HttpClientBuilder builder,
                                                         final URI uri, final String username,
                                                         String password) {
        if (isNotBlank(username)) {
            CredentialsProvider provider = new BasicCredentialsProvider();
            AuthScope scope = new AuthScope(uri.getHost(), uri.getPort(), "realm");
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
            provider.setCredentials(scope, credentials);
            builder.setDefaultCredentialsProvider(provider);

            builder.addInterceptorFirst(new PreemptiveAuth());
        }
        return builder;
    }


    /**
     * Get the local context.
     *
     * @return context
     */
    protected HttpContext getLocalContext() {
        return localContext;
    }


    /**
     * Set the local context.
     *
     * @param localContext the context
     */
    protected void setLocalContext(final HttpContext localContext) {
        this.localContext = localContext;
    }


    private <T extends BaseModel> T objectFromResponse(Class<T> cls, HttpResponse response) throws IOException {
        InputStream content = response.getEntity().getContent();
        byte[] bytes = ByteStreams.toByteArray(content);
        T result = mapper.readValue(bytes, cls);
        // : original:
        // T result = mapper.readValue(content, cls);
        result.setClient(this);
        return result;
    }

    private ObjectMapper getDefaultMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }

    private void releaseConnection(HttpRequestBase httpRequestBase) {
        httpRequestBase.releaseConnection();
    }

}
