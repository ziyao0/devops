package com.eason.devops.rancher.service;

import com.eason.devops.rancher.client.BasicAuthInterceptor;
import com.eason.devops.rancher.client.TrustAllCerts;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Rancher.
 *
 * @author zhangziyao
 * @date 2021/10/10
 */
public class Rancher {

    private final Retrofit retrofit;

    private static final Logger LOGGER = Logger.getLogger(Rancher.class.getName());

    /**
     * Instantiates a new Rancher.
     *
     * @param config the config
     */
    public Rancher(final Config config) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(30000L, TimeUnit.SECONDS)
                .addInterceptor(
                        BasicAuthInterceptor.auth(config.getAccessKey(), config.getSecretKey())
                )
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    HttpUrl originalHttpUrl = original.url();
                    HttpUrl.Builder httpUrlBuilder = originalHttpUrl.newBuilder();
                    httpUrlBuilder.addPathSegment("v3");
                    List<String> pathSegments = originalHttpUrl.pathSegments();
                    httpUrlBuilder.setPathSegment(0, "v3");
                    for (int i = 0; i < pathSegments.size(); i++) {
                        httpUrlBuilder.setPathSegment(i + 1, pathSegments.get(i));
                    }
                    HttpUrl url = httpUrlBuilder.build();
                    Request.Builder requestBuilder = original.newBuilder()
                            .url(url);
                    Request request = requestBuilder.addHeader("Accept", "application/json").build();
                    return chain.proceed(request);
                })
                //信任所有https证书
                .hostnameVerifier((s, sslSession) -> true)
                .sslSocketFactory(createSSLSocketFactory(), new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                })
                .protocols(Collections.singletonList(Protocol.HTTP_1_1));

        this.retrofit = new Retrofit.Builder()
                .baseUrl(config.getUrl().toString())
                .client(builder.build())
                .addConverterFactory(JacksonConverterFactory.create(configureObjectMapper()))
                .build();

    }

    //这里是创建一个SSLSocketFactory,提供给上面的 .sslSocketFactory()
    private SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
            LOGGER.log(Level.FINE, "创建证书信任异常！", e);
        }
        return ssfFactory;
    }

    private ObjectMapper configureObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }

    /**
     * Type t.
     *
     * @param <T>     the type parameter
     * @param service the service
     * @return the t
     */
    public <T> T type(Class<T> service) {
        return retrofit.create(service);
    }

    /**
     * The type Config.
     */
    public static class Config {

        private URL url;
        private String accessKey;
        private String secretKey;

        /**
         * Instantiates a new Config.
         *
         * @param url       the url
         * @param accessKey the access key
         * @param secretKey the secret key
         */
        public Config(URL url, String accessKey, String secretKey) {
            this.url = url;
            this.accessKey = accessKey;
            this.secretKey = secretKey;
        }

        /**
         * Gets url.
         *
         * @return the url
         */
        public URL getUrl() {
            return url;
        }

        /**
         * Sets url.
         *
         * @param url the url
         */
        public void setUrl(URL url) {
            this.url = url;
        }

        /**
         * Gets access key.
         *
         * @return the access key
         */
        public String getAccessKey() {
            return accessKey;
        }

        /**
         * Sets access key.
         *
         * @param accessKey the access key
         */
        public void setAccessKey(String accessKey) {
            this.accessKey = accessKey;
        }

        /**
         * Gets secret key.
         *
         * @return the secret key
         */
        public String getSecretKey() {
            return secretKey;
        }

        /**
         * Sets secret key.
         *
         * @param secretKey the secret key
         */
        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }
    }
}
